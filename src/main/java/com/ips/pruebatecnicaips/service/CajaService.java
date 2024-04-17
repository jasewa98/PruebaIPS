package com.ips.pruebatecnicaips.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public interface CajaService {

    BigDecimal obtenerSaldoPorCajaYFecha(Integer numeroCaja, LocalDate fecha);

    BigDecimal obtenerSaldoTotalEnFecha(LocalDate fecha);
}
