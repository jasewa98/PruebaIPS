package com.ips.pruebatecnicaips.repository;

import com.ips.pruebatecnicaips.model.Caja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CajaRepository extends JpaRepository<Caja, Long> {
    List<Caja> findByNumeroCajaAndFecha(Integer numeroCaja, LocalDate fecha);
    List<Caja> findByFecha(LocalDate fecha);
}
