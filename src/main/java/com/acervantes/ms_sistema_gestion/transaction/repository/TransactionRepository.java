package com.acervantes.ms_sistema_gestion.transaction.repository;

import com.acervantes.ms_sistema_gestion.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);
}