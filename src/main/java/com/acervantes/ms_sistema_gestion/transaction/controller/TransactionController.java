package com.acervantes.ms_sistema_gestion.transaction.controller;

import com.acervantes.ms_sistema_gestion.transaction.entity.Transaction;
import com.acervantes.ms_sistema_gestion.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        log.info("*********************************************************");
        log.info("********* INICIA MICROSERVICIO TRANSACCIONES ************");
        log.info("*********************************************************");

        log.info("Iniciando obtención de todas las transacciones");
        List<Transaction> transactions = transactionService.getAllTransactions();
        log.info("Transacciones obtenidas exitosamente. Total: {}", transactions.size());
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        log.info("Iniciando creación de una nueva transacción: {}", transaction);
        try {
            Transaction createdTransaction = transactionService.createTransaction(transaction);
            log.info("Transacción creada exitosamente con ID: {}", createdTransaction.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
        } catch (Exception e) {
            log.error("Error al crear la transacción: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> getTransactionById(@PathVariable Long id) {
        log.info("Iniciando búsqueda de transacción con ID: {}", id);
        try {
            Optional<Transaction> transaction = transactionService.getTransactionById(id);
            if (transaction.isPresent()) {
                log.info("Transacción encontrada: {}", transaction.get());
                return ResponseEntity.ok(transaction);
            } else {
                log.warn("Transacción con ID {} no encontrada", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Error al buscar la transacción con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
