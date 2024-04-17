package com.ips.pruebatecnicaips.controller;

import com.ips.pruebatecnicaips.service.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/cajas")
public class CajaController {
    @Autowired
    private CajaService cajaService;

    @GetMapping("/saldo")
    public ResponseEntity<?> obtenerSaldoPorCajaYFecha(@RequestParam Integer numeroCaja, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        BigDecimal saldo = cajaService.obtenerSaldoPorCajaYFecha(numeroCaja, fecha);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/saldo-total")
    public ResponseEntity<?> obtenerSaldoTotalEnFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        BigDecimal saldoTotal = cajaService.obtenerSaldoTotalEnFecha(fecha);
        return ResponseEntity.ok(saldoTotal);
    }
}
