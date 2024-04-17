package com.ips.pruebatecnicaips.service.impl;

import com.ips.pruebatecnicaips.exception.CajaException;
import com.ips.pruebatecnicaips.model.Caja;
import com.ips.pruebatecnicaips.repository.CajaRepository;
import com.ips.pruebatecnicaips.service.CajaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CajaServiceImpl implements CajaService {
    @Autowired
    private CajaRepository cajaRepository;

    public BigDecimal obtenerSaldoPorCajaYFecha(Integer numeroCaja, LocalDate fecha) {
        List<Caja> registrosExistentes = cajaRepository.findByNumeroCajaAndFecha(numeroCaja, fecha);

        if (registrosExistentes.size() > 1) {
            throw new CajaException("Se encontraron registros duplicados para el número de caja " +
                    numeroCaja + " y fecha " + fecha + ".");
        }

        if (registrosExistentes.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return registrosExistentes.get(0).getSaldo();
    }

    public BigDecimal obtenerSaldoTotalEnFecha(LocalDate fecha) {
        List<Caja> registros = cajaRepository.findByFecha(fecha);
        BigDecimal saldoTotal = registros.stream()
                .map(Caja::getSaldo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return saldoTotal;
    }
}
