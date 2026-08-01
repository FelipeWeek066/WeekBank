package com.weeklab.weekbank.repositories;


import com.weeklab.weekbank.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByNameContaining(){
        List<User> res = userRepository.findByNameContaining("fel");
        User user = (User)userRepository.findByName("felipe").get();
        Assertions.assertFalse(res.isEmpty());
        Assertions.assertTrue(res.contains(user));
    }
}
