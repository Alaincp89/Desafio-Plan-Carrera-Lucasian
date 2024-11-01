package com.acervantes.ms_sistema_gestion.transaction.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;

    private Long idComercio;

    private Double valorTransaccion;

    @Column(name = "currency")
    private String tipoMoneda;

    private String tipoTransaccion;
    private String metodoPago;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
    private LocalDateTime fechaTransaccion;

    private String descripcion;
    private String statusTransaccion;

}
