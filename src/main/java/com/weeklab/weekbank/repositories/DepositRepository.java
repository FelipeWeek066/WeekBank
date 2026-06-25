package com.weeklab.weekbank.repositories;

import com.weeklab.weekbank.entities.Deposit;
import com.weeklab.weekbank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepositRepository extends JpaRepository<Deposit, UUID> {
    List<Deposit> findByPayerOrPayee(User payer, User payee);
    boolean existsByPayer(User payer);
    boolean existsByPayee(User payee);
}
