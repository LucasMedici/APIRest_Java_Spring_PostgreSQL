package com.example.springboot.controllers;

import com.example.springboot.dtos.LoginDTO;
import com.example.springboot.dtos.RegisterDTO;
import com.example.springboot.infra.security.TokenService;
import com.example.springboot.models.UserModel;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {


  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(token+" "+auth.getName());
    }



    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO){
        if(this.userRepository.findByEmail(registerDTO.email()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserModel user = new UserModel(registerDTO.name(), registerDTO.email(), encryptedPassword, registerDTO.role());
        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
