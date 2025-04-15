package com.gnose.mvp.Core.Adapter;

import com.gnose.mvp.Core.Models.UserJpaEntity;
import com.gnose.mvp.Core.Repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserJpaRepository userRepository;

    @GetMapping
    public List<UserJpaEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public UserJpaEntity createUser(@RequestBody UserJpaEntity user) {
        return userRepository.save(user);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}