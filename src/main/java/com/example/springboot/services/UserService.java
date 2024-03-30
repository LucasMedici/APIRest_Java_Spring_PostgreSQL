package com.example.springboot.services;


import com.example.springboot.controllers.UserController;
import com.example.springboot.dtos.UserRequestDTO;
import com.example.springboot.exceptions.users.UserNotFoundException;
import com.example.springboot.models.UserModel;
import com.example.springboot.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel saveUser(UserRequestDTO userRequestDTO){
        var user = new UserModel();
        BeanUtils.copyProperties(userRequestDTO, user);
        return userRepository.save(user);
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        if(!users.isEmpty()){
            for (UserModel user:users){
                UUID id = user.getId();
                Link selfLink = linkTo(methodOn(UserController.class).getOneUser(id)).withSelfRel();
                user.add(selfLink);
            }
        }
        return users;
    }

    public ResponseEntity<Object> getOneUser(UUID id){
        UserModel user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Link usersLink = linkTo(methodOn(UserController.class).getAllUsers()).withRel("ALL Users");
        user.add(usersLink);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    public ResponseEntity<Object> updateUser(UUID id, UserRequestDTO userRequestDTO){
        UserModel user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        BeanUtils.copyProperties(userRequestDTO, user);
        return ResponseEntity.status(HttpStatus.FOUND).body(userRepository.save(user));
    }

    public ResponseEntity<Object> deleteUser(UUID id){
        UserModel user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body("User deleted sucessfully");

    }


    public Optional<UserModel> findByEmail(String email) {
        Optional<UserModel> user = userRepository.findByEmail(email);
        System.out.println(user);
        return user;
    }
}
