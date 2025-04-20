package com.gnose.mvp.Core.Adapter.inbound.Rest.Specific;

import com.gnose.mvp.Core.Adapter.inbound.DTO.Specific.LoginDTO;
import com.gnose.mvp.Core.Adapter.outbound.DTO.UserPermissionsDTO;
import com.gnose.mvp.Core.Application.Impl.JwtServiceImpl;
import com.gnose.mvp.Core.Application.Impl.RedisServiceImpl;
import com.gnose.mvp.Core.Application.UseCases.IAuthService;
import com.gnose.mvp.Core.Application.UseCases.IUserService;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final IUserService userService;
    private final IAuthService authService;
    private final JwtServiceImpl jwtServiceImpl;
    private final RedisServiceImpl redisServiceImpl;

    @Autowired
    public LoginController(IUserService userService,
                           IAuthService authService,
                           JwtServiceImpl jwtServiceImpl,
                           RedisServiceImpl redisServiceImpl)
    {
        this.authService = authService;
        this.userService = userService;
        this.jwtServiceImpl = jwtServiceImpl;
        this.redisServiceImpl = redisServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> Login(@RequestBody LoginDTO dto)
    {
        try {
            UserJpaEntity user = userService.findByEmail(dto.getEmail());
            List<UserPermissionsDTO> permissions = authService.getPermissions(user.getId());
            String jwt = jwtServiceImpl.generateToken(user.getId());
            redisServiceImpl.saveSession(jwt, permissions);

            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }



}
