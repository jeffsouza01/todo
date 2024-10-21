package com.porto.todo.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/new")
    public ResponseEntity create(@RequestBody UserModel user){
        UserModel userValid = userRepository.findByUsername(user.getUsername());

        if (userValid != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        var passCrypt = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(passCrypt);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
