package com.example.springboot.controllers;

import com.example.springboot.dtos.UserRequestDTO;
import com.example.springboot.models.UserModel;
import com.example.springboot.services.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        UserModel userSaved = userService.saveUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userSaved);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        ResponseEntity<Object> response = userService.getOneUser(id);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserRequestDTO userRequestDTO){
        ResponseEntity<Object> response = userService.updateUser(id, userRequestDTO);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        ResponseEntity<Object> response = userService.deleteUser(id);
        return response;
    }


}
