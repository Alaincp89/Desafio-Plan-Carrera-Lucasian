package com.acervantes.ms_sistema_gestion.transaction.usecase;

import com.acervantes.ms_sistema_gestion.transaction.entity.Transaction;
import com.acervantes.ms_sistema_gestion.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionUseCase {
    private final TransactionRepository repository;

    public TransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return repository.findById(id);
    }
}