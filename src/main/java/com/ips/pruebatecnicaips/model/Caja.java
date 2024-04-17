package com.ips.pruebatecnicaips.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cajas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Caja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_caja")
    private Integer numeroCaja;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "descripcion")
    private String descripcion;


}
