package com.gnose.mvp.Core.Adapter.inbound.Rest.Specific;

import com.gnose.mvp.Core.Adapter.inbound.DTO.Specific.LoginDTO;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.DTO.UserPermissionsDTO;
import com.gnose.mvp.Core.Application.AuthService;
import com.gnose.mvp.Core.Application.JwtService;
import com.gnose.mvp.Core.Application.RedisService;
import com.gnose.mvp.Core.Application.UserService;
import com.gnose.mvp.Core.Models.UserJpaEntity;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.LockInfo;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UserService userService;
    private final AuthService authService;
    private final JwtService jwtService;
    private final RedisService redisService;

    @Autowired
    public LoginController(UserService userService,
                           AuthService authService,
                           JwtService jwtService,
                           RedisService redisService)
    {
        this.authService = authService;
        this.userService = userService;
        this.jwtService = jwtService;
        this.redisService = redisService;
    }

    @PostMapping
    public ResponseEntity<?> Login(@RequestBody LoginDTO dto)
    {
        try {
            UserJpaEntity user = userService.findByEmail(dto.getEmail());
            List<UserPermissionsDTO> permissions = authService.getPermissions(user.getId());
            String jwt = jwtService.generateToken(user.getId());
            redisService.saveSession(jwt, permissions);

            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }



}
