package com.example.springboot.controllers;

import com.example.springboot.dtos.LoginDTO;
import com.example.springboot.dtos.RegisterDTO;
import com.example.springboot.dtos.UserRequestDTO;
import com.example.springboot.exceptions.users.UserCredentialsInvalid;
import com.example.springboot.models.UserModel;
import com.example.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO loginDTO){
        Optional<UserModel> user = userService.findByEmail(loginDTO.email());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access invalid");
        }
        if(loginDTO.password().equals(user.get().getPassword())){
            return ResponseEntity.status(HttpStatus.OK).body("autorizado");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access invalid");

    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRequestDTO userRequestDTO){
        Optional<UserModel> user = userService.findByEmail(userRequestDTO.email());
        if(user.isEmpty()){
            userService.saveUser(userRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Sucessfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
    }

}
