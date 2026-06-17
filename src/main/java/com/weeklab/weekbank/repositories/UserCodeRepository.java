package com.weeklab.weekbank.repositories;

import com.weeklab.weekbank.entities.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCodeRepository extends JpaRepository<UserCode, UUID> {
    Optional<UserCode> findByCode(String code);

    Optional<UserCode> findByName(String name);

    boolean existsByName(String name);
    boolean existsByCode(String code);
}
