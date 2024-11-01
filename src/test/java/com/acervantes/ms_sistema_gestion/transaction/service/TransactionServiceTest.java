package com.acervantes.ms_sistema_gestion.transaction.service;

import com.acervantes.ms_sistema_gestion.transaction.entity.Transaction;
import com.acervantes.ms_sistema_gestion.transaction.usecase.TransactionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionUseCase transactionUseCase;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        // Configuración
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setValorTransaccion(100.0);
        transaction1.setTipoMoneda("USD");
        transaction1.setTipoTransaccion("compra");
        transaction1.setMetodoPago("tarjeta de credito");
        transaction1.setDescripcion("Compra de productos electrónicos");
        transaction1.setStatusTransaccion("completed");


        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setValorTransaccion(200.0);
        transaction2.setTipoMoneda("USD");
        transaction2.setTipoTransaccion("compra");
        transaction2.setMetodoPago("tarjeta de credito");
        transaction2.setDescripcion("Compra de ropa");
        transaction2.setStatusTransaccion("completed");

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);
        when(transactionUseCase.getAllTransactions()).thenReturn(transactions);

        List<Transaction> result = transactionService.getAllTransactions();

        assertEquals(2, result.size());
        assertEquals(100.0, result.get(0).getValorTransaccion());
        assertEquals("Compra de productos electrónicos", result.get(0).getDescripcion());
        verify(transactionUseCase, times(1)).getAllTransactions();
    }

    @Test
    void testCreateTransaction() {

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setIdUsuario(12345L);
        transaction.setIdComercio(67890L);
        transaction.setValorTransaccion(150.0);
        transaction.setTipoMoneda("USD");
        transaction.setTipoTransaccion("compra");
        transaction.setMetodoPago("tarjeta de credito");
        transaction.setDescripcion("Compra de productos electrónicos");
        transaction.setStatusTransaccion("completed");

        when(transactionUseCase.createTransaction(transaction)).thenReturn(transaction);

        Transaction result = transactionService.createTransaction(transaction);

        assertEquals(1L, result.getId());
        assertEquals(12345L, result.getIdUsuario());
        assertEquals(150.0, result.getValorTransaccion());
        assertEquals("USD", result.getTipoMoneda());
        assertEquals("compra", result.getTipoTransaccion());
        assertEquals("Compra de productos electrónicos", result.getDescripcion());
        verify(transactionUseCase, times(1)).createTransaction(transaction);
    }
}
