package com.Kario.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Kario.Models.User;
import com.Kario.Models.Enums.Role;
import com.Kario.Repositories.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUserByEmail() {

        // create and store the user
        User user = User.builder()
        .name("pedro")
        .email("pedro@gmail.com")
        .password("pass123")
        .role(Role.USER)
        .build();
        
        userRepository.save(user);

        // Find by email
        Optional<User> found = userRepository.findByEmail("pedro@gmail.com");

        assertThat(found).isPresent();
        assertEquals("pedro", found.get().getName());
        assertEquals(Role.USER, found.get().getRole());

    }


    
}
