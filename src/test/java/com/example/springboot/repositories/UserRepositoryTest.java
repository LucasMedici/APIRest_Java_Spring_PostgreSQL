package com.example.springboot.repositories;

import com.example.springboot.dtos.RegisterDTO;
import com.example.springboot.dtos.UserRequestDTO;
import com.example.springboot.models.UserModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Optional;


import static  org.assertj.core.api.Assertions.*;


@DataJpaTest // Dizendo que metódos JPA serão testados
@ActiveProfiles("test") // Especificando pro Spring que o application-TEST é o que tem que ser utilziado pra conectar com um banco
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findByNameCase1() {
        String name = "Lucas";
        UserRequestDTO userRequestDTO = new UserRequestDTO("Lucas", "lucas@gmail.com", "123");
        this.createUser(userRequestDTO);

        Optional<UserModel> foundedUser = this.userRepository.findByName(name);

        assertThat(foundedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should NOT get User from DB when user not exists")
    void findByNameCase2() {
        String name = "Lucas";
        Optional<UserModel> foundedUser = this.userRepository.findByName(name);
        assertThat(foundedUser.isEmpty()).isTrue();
    }





    // Gerando um metódo pra criar usuários pro teste
    private UserModel createUser(UserRequestDTO userRequestDTO) {
        UserModel user = new UserModel(userRequestDTO);
        this.entityManager.persist(user);
        return user;

    }
}