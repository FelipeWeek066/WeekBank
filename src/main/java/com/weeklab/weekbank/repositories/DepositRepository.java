package com.weeklab.weekbank.repositories;

import com.weeklab.weekbank.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepositRepository extends JpaRepository<Deposit, UUID> {
}
