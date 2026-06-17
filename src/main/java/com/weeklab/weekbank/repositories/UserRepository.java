package com.weeklab.weekbank.repositories;

import com.weeklab.weekbank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByName(String name);
    boolean existsByName(String name);

}
