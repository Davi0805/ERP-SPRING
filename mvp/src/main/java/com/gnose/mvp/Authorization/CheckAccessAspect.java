package com.gnose.mvp.Authorization;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.DTO.CompanyPermissionDTO;
import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import com.gnose.mvp.Core.Application.Impl.RedisServiceImpl;
import com.gnose.mvp.Documents_Module.Adapter.DTO.DocumentDTO;
import com.gnose.mvp.Exceptions.BadRequestException;
import com.gnose.mvp.Exceptions.UnauthorizedException;
import com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound.ImportOrderDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class CheckAccessAspect {

    private final RedisServiceImpl redisService;

    public CheckAccessAspect(RedisServiceImpl redisService) {
        this.redisService = redisService;
    }

    @Around("@annotation(com.gnose.mvp.Authorization.CheckAccess)")
    public Object checkAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            HttpServletRequest request = (HttpServletRequest) RequestContextHolder.currentRequestAttributes()
                    .resolveReference(RequestAttributes.REFERENCE_REQUEST);

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new UnauthorizedException("Missing or invalid Authorization header");
            }

            String token = authHeader.replace("Bearer ", "");
            SessionRedisDTO session = redisService.getSession(token);
            if (session == null) {
                throw new UnauthorizedException("Invalid session");
            }

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            CheckAccess checkAccess = method.getAnnotation(CheckAccess.class);

            String requiredPermission = checkAccess.permission();
            String companyIdExpression = checkAccess.companyId();

            // wildcard case means when u need to check or use the auth data on controller
            if ("*".equals(companyIdExpression)) {
                List<CompanyPermissionDTO> authorizedCompanies = session.getCompanyPermission().stream()
                        .filter(company -> company.getPermissions().contains(requiredPermission))
                        .toList();

                if (authorizedCompanies.isEmpty()) {
                    throw new UnauthorizedException("Access denied: insufficient permissions");
                }

                // store authorization data in request attributes
                request.setAttribute("authorizedCompanies", authorizedCompanies);
                return joinPoint.proceed();
            }

            // if the companyId is not wildcard, we need to resolve it from the method arguments
            Long companyId = resolveCompanyId(companyIdExpression, joinPoint);
            if (companyId == null) {
                throw new UnauthorizedException("Company ID not found");
            }

            boolean hasAccess = session.getCompanyPermission().stream()
                    .filter(company -> company.getCompanyId().equals(companyId))
                    .flatMap(company -> company.getPermissions().stream())
                    .anyMatch(permission -> permission.equals(requiredPermission));

            if (!hasAccess) {
                throw new UnauthorizedException("Access denied: insufficient permissions");
            }

            request.setAttribute("companyId", companyId);
            return joinPoint.proceed();

        } catch (UnauthorizedException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    private Long resolveCompanyId(String expression, ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];

            // Check if the argument matches the expression (e.g., path variable)
            if (parameterNames[i].equals(expression) && arg instanceof Long) {
                return (Long) arg;
            }

            // todo: search later how to make a more custom and scalable way to resolve the companyId
            if (arg instanceof ContainerJpaEntity) {
                ContainerJpaEntity container = (ContainerJpaEntity) arg;
                return container.getCompanyId();
            } else if (arg instanceof DocumentDTO) {
                DocumentDTO dto = (DocumentDTO) arg;
                return dto.getCompanyId();
            } else if (arg instanceof ImportOrderDTO) {
                ImportOrderDTO dto = (ImportOrderDTO) arg;
                return dto.getCompanyId();
            } else if (arg instanceof Map) {
                Map<String, Object> body = (Map<String, Object>) arg;
                if (body.containsKey("companyId")) {
                    return Long.valueOf(body.get("companyId").toString());
                }
            }
        }

        throw new BadRequestException("Unable to resolve companyId");
    }
}