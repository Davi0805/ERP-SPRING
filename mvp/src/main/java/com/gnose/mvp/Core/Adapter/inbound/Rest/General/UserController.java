package com.gnose.mvp.Core.Adapter.inbound.Rest.General;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateUserDTO;
import com.gnose.mvp.Core.Application.UseCases.IUserService;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserJpaEntity> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}