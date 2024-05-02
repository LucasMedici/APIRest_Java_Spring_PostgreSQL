package com.example.springboot.services;

import com.example.springboot.dtos.UserRequestDTO;
import com.example.springboot.models.UserModel;
import com.example.springboot.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static  org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
class UserServiceTest {

    @Mock // criando a dependencia fake (basicamente copia a original e deixa vazia, a gente especifica oq tem que retornar no teste)
    private UserRepository userRepository;

    @Autowired
    EntityManager entityManager;


    //Iniciando os mocks do mockito antes de iniciar os testes
    @BeforeEach
    void Setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should save a new user in DB")
    //verificando se o repositorio foi chamado alguma vez, ou seja, se um usuario foi salvo no DB
    void saveUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("Teste", "teste@gmail.com", "123");
        UserModel user = new UserModel();
        BeanUtils.copyProperties(userRequestDTO, user);

        userRepository.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Should return all users in DB")
    void getAllUsers() {
        List<UserModel> list = userRepository.findAll();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Shoud return one user find by a valid UUID in DB")
    void getOneUserCase1() {
        UUID validUUID = UUID.randomUUID();
        UserModel user = new UserModel();

        when(userRepository.findById(validUUID)).thenReturn(Optional.of(user));

        Optional<UserModel> userOptional = userRepository.findById(validUUID);

        verify(userRepository, times(1)).findById(any());
        assertThat(userOptional.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should NOT return one user find by a INVALID UUID in DB")
    void getOneUserCase2(){
        UUID invalidUUID = UUID.randomUUID();
        when(userRepository.findById(invalidUUID)).thenReturn(Optional.empty());

        Optional<UserModel> userOptional = userRepository.findById(invalidUUID);

        verify(userRepository, times(1)).findById(any());
        assertThat(userOptional.isEmpty()).isTrue();

    }

    @Test
    @DisplayName("Should return a updated user in DB")
    void updateUser() {
        UUID uuidValid = UUID.randomUUID();
        String newName = "Andre";
        String newEmail = "andre@gmail.com";
        String newPassword = "123";

        UserModel user1 = new UserModel(uuidValid, "Lucas", "lucas@gmail.com", "123");
        UserRequestDTO userRequestDTO = new UserRequestDTO(newName, newEmail, newPassword);
        BeanUtils.copyProperties(userRequestDTO, user1);

        when(userRepository.save(user1)).thenReturn(user1);

        UserModel userUpdated = userRepository.save(user1);
        assertThat(userUpdated.getId()).isEqualTo(uuidValid);
        assertThat(userUpdated.getName()).isEqualTo(newName);
        assertThat(userUpdated.getEmail()).isEqualTo(newEmail);
        assertThat(userUpdated.getPassword()).isEqualTo(newPassword);
    }



    @Test
    @DisplayName("Should delete a user by UUID valid")
    void deleteUserCase1() {
        UUID validUUID = UUID.randomUUID();
        UserModel user = new UserModel();

        when(userRepository.findById(validUUID)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(validUUID);

        Optional<UserModel> userFinded = userRepository.findById(validUUID);
        assertThat(userFinded.isPresent()).isTrue();

        userRepository.deleteById(validUUID);
        verify(userRepository, times(1)).deleteById(validUUID);
    }


    @Test
    @DisplayName("Should NOT delete a user by UUID INVALID")
    void deleteUserCase2() {
        UUID invalidUUID = UUID.randomUUID();

        when(userRepository.findById(invalidUUID)).thenReturn(Optional.empty());
        doNothing().when(userRepository).deleteById(invalidUUID);

        Optional<UserModel> userFinded = userRepository.findById(invalidUUID);
        assertThat(userFinded.isEmpty()).isTrue();

        verify(userRepository, times(0)).deleteById(invalidUUID);
    }
}