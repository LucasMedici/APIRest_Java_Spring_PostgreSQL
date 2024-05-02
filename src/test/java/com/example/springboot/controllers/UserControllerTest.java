package com.example.springboot.controllers;

import com.example.springboot.dtos.UserRequestDTO;
import com.example.springboot.models.UserModel;
import com.example.springboot.services.UserService;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static  org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Shoud have a new user")
    void saveUser() {
        UUID uuid = UUID.randomUUID();
        UserRequestDTO userRequestDTO = new UserRequestDTO("Lucas", "lucas@gmail.com", "123");
        UserModel userSaved = new UserModel(uuid, userRequestDTO.name(), userRequestDTO.email(), userRequestDTO.password());

        when(userService.saveUser(userRequestDTO)).thenReturn(userSaved);

        ResponseEntity<UserModel> response = userController.saveUser(userRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userSaved, response.getBody());

    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getOneUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}