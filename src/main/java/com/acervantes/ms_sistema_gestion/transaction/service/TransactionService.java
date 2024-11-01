package com.acervantes.ms_sistema_gestion.transaction.service;

import com.acervantes.ms_sistema_gestion.transaction.usecase.TransactionUseCase;
import com.acervantes.ms_sistema_gestion.transaction.entity.Transaction;



import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {


    private final TransactionUseCase transactionUseCase;


    public TransactionService(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    public List<Transaction> getAllTransactions() {
        return transactionUseCase.getAllTransactions();
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionUseCase.createTransaction(transaction);
    }

    public Optional<Transaction> getTransactionById(Long id){
        return transactionUseCase.getTransactionById(id);
    }
}