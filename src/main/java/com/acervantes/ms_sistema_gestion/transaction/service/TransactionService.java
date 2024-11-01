package com.acervantes.ms_sistema_gestion.transaction.service;

import com.acervantes.ms_sistema_gestion.transaction.usecase.TransactionUseCase;
import com.acervantes.ms_sistema_gestion.transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // Método para obtener transacciones completadas
    public List<Transaction> getCompletedTransactions() {
        return transactionUseCase.getAllTransactions().stream()
                .filter(transaction -> "completed".equals(transaction.getStatusTransaccion()))
                .collect(Collectors.toList());
    }

    // Método para calcular el valor total de todas las transacciones
    public Double getTotalValueOfTransactions() {
        return transactionUseCase.getAllTransactions().stream()
                .map(Transaction::getValorTransaccion)
                .reduce(0.0, Double::sum);
    }

    // Método para ordenar transacciones por fecha en orden descendente
    public List<Transaction> getTransactionsSortedByDate() {
        return transactionUseCase.getAllTransactions().stream()
                .sorted(Comparator.comparing(Transaction::getFechaTransaccion).reversed())
                .collect(Collectors.toList());
    }
}
