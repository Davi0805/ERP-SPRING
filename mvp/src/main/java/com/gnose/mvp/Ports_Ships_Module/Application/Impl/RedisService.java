package com.gnose.mvp.Ports_Ships_Module.Application.Impl;

import com.gnose.mvp.Core.Adapter.outbound.DTO.CompanyPermissionDTO;
import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import com.gnose.mvp.Core.Adapter.outbound.DTO.UserPermissionsDTO;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.SessionRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RedisService {

    private final SessionRedisRepository repository;

    @Autowired
    public RedisService(SessionRedisRepository repository)
    {
        this.repository = repository;
    }

    public void saveSession(String jwt, List<UserPermissionsDTO> permissions) {
        SessionRedisDTO entity = new SessionRedisDTO();
        entity.setToken(jwt);

        // Group permissions by company ID
        Map<Long, List<String>> tempMap = new HashMap<>();
        for(UserPermissionsDTO permission : permissions) {
            if (!tempMap.containsKey(permission.getCompany_id())) {
                tempMap.put(permission.getCompany_id(), new ArrayList<>());
            }
            tempMap.get(permission.getCompany_id()).add(permission.getPermission_code());
        }

        // Convert map to list of CompanyPermission objects
        List<CompanyPermissionDTO> companyPermissions = new ArrayList<>();
        for (Map.Entry<Long, List<String>> entry : tempMap.entrySet()) {
            companyPermissions.add(new CompanyPermissionDTO(entry.getKey(), entry.getValue()));
        }

        entity.setCompanyPermission(companyPermissions);
        repository.save(entity);
    }

    public SessionRedisDTO getSession(String jwt)
    {
        SessionRedisDTO session = repository.findById(jwt).orElseThrow(() -> new RuntimeException("Session not found!"));

        System.out.println("TOKEN = " + session.getToken());
        List<CompanyPermissionDTO> permissions = session.getCompanyPermission();

       for (CompanyPermissionDTO perm : permissions)
       {
           System.out.println("Company_id = " + perm.getCompanyId() + " | Permissoes = " + perm.getPermissions());
       }

        return session;
    }



}
