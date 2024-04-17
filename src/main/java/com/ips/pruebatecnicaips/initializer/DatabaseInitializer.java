package com.ips.pruebatecnicaips.initializer;

import com.ips.pruebatecnicaips.model.Caja;
import com.ips.pruebatecnicaips.repository.CajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private CajaRepository cajaRepository;

    @Override
    public void run(String... args) {

        List<Caja> cajas = new ArrayList<>();

        cajas.add(new Caja(null, 1, LocalDate.of(2023, 1, 1), new BigDecimal("1000"), "desc 1"));
        cajas.add(new Caja(null, 1, LocalDate.of(2023, 1, 15), new BigDecimal("1250"), "desc 2"));
        cajas.add(new Caja(null, 2, LocalDate.of(2023, 1, 10), new BigDecimal("1130"), "desc 3"));
        cajas.add(new Caja(null, 3, LocalDate.of(2023, 1, 20), new BigDecimal("980"), "desc 4"));
        cajas.add(new Caja(null, 3, LocalDate.of(2023, 1, 25), new BigDecimal("825"), "desc 5"));

        // Añadir más registros para el mismo día y otros días
        /*
        cajas.add(new Caja(null, 1, LocalDate.of(2023, 1, 20), new BigDecimal("1100"), "desc 6"));
        cajas.add(new Caja(null, 2, LocalDate.of(2023, 1, 20), new BigDecimal("1150"), "desc 7"));
        cajas.add(new Caja(null, 3, LocalDate.of(2023, 1, 20), new BigDecimal("1000"), "desc 8"));
        cajas.add(new Caja(null, 4, LocalDate.of(2023, 1, 15), new BigDecimal("1200"), "desc 9"));
        cajas.add(new Caja(null, 4, LocalDate.of(2023, 1, 20), new BigDecimal("950"), "desc 10"));
        cajas.add(new Caja(null, 5, LocalDate.of(2023, 1, 10), new BigDecimal("1050"), "desc 11"));
        cajas.add(new Caja(null, 5, LocalDate.of(2023, 1, 20), new BigDecimal("990"), "desc 12"));
        cajas.add(new Caja(null, 6, LocalDate.of(2023, 1, 25), new BigDecimal("930"), "desc 13"));

         */

        cajaRepository.saveAll(cajas);
    }
}
