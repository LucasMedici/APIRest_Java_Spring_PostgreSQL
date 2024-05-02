package com.example.springboot.services;


import com.example.springboot.controllers.UserController;
import com.example.springboot.dtos.UserRequestDTO;
import com.example.springboot.exceptions.users.UserCredentialsInvalid;
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
        this.userRepository.save(user);
        return user;
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

    public Optional<UserModel> getOneUser(UUID id){
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException();
        }
        Link usersLink = linkTo(methodOn(UserController.class).getAllUsers()).withRel("ALL Users");
        user.get().add(usersLink);
        return user;
    }

    public UserModel updateUser(UUID id, UserRequestDTO userRequestDTO){
        UserModel user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        BeanUtils.copyProperties(userRequestDTO, user);
        this.userRepository.save(user);
        return user;
    }

    public Optional<UserModel> deleteUser(UUID id){
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        this.userRepository.deleteById(id);
        return user;

    }

}
