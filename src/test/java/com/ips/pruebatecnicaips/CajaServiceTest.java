package com.ips.pruebatecnicaips;

import com.ips.pruebatecnicaips.exception.CajaException;
import com.ips.pruebatecnicaips.model.Caja;
import com.ips.pruebatecnicaips.repository.CajaRepository;
import com.ips.pruebatecnicaips.service.impl.CajaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CajaServiceTest {

    @Mock
    private CajaRepository cajaRepository;

    @InjectMocks
    private CajaServiceImpl cajaService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void obtenerSaldoPorCajaYFechaTest() {
        Integer numeroCaja = 1;
        LocalDate fecha = LocalDate.of(2023, 1, 1);
        Caja registro = new Caja(1L, numeroCaja, fecha, new BigDecimal("1000"), "desc 1");

        when(cajaRepository.findByNumeroCajaAndFecha(numeroCaja, fecha)).thenReturn(Collections.singletonList(registro));

        BigDecimal saldoTotal = cajaService.obtenerSaldoPorCajaYFecha(numeroCaja, fecha);

        assertEquals(new BigDecimal("1000"), saldoTotal);

        verify(cajaRepository).findByNumeroCajaAndFecha(numeroCaja, fecha);
    }

    @Test
    void obtenerSaldoPorCajaYFechaSinRegistrosTest() {
        Integer numeroCaja = 2;
        LocalDate fecha = LocalDate.of(2023, 1, 10);
        when(cajaRepository.findByNumeroCajaAndFecha(numeroCaja, fecha)).thenReturn(Collections.emptyList());

        BigDecimal saldoTotal = cajaService.obtenerSaldoPorCajaYFecha(numeroCaja, fecha);

        assertEquals(BigDecimal.ZERO, saldoTotal);
        verify(cajaRepository).findByNumeroCajaAndFecha(numeroCaja, fecha);
    }

    @Test
    void obtenerSaldoPorCajaYFechaConRegistrosDuplicadosTest() {
        Integer numeroCaja = 1;
        LocalDate fecha = LocalDate.of(2023, 1, 1);
        Caja registro1 = new Caja(1L, numeroCaja, fecha, new BigDecimal("1000"), "desc 1");
        Caja registro2 = new Caja(2L, numeroCaja, fecha, new BigDecimal("1250"), "desc 2");
        when(cajaRepository.findByNumeroCajaAndFecha(numeroCaja, fecha)).thenReturn(Arrays.asList(registro1, registro2));

        assertThrows(CajaException.class, () -> {
            cajaService.obtenerSaldoPorCajaYFecha(numeroCaja, fecha);
        });

        verify(cajaRepository).findByNumeroCajaAndFecha(numeroCaja, fecha);
    }

    @Test
    void obtenerSaldoTotalEnFechaTest() {
        LocalDate fecha = LocalDate.of(2023, 1, 20);
        List<Caja> registros = Arrays.asList(
                new Caja(1L, 1, fecha, new BigDecimal("1000"), "desc 6"),
                new Caja(2L, 2, fecha, new BigDecimal("1150"), "desc 7"),
                new Caja(3L, 3, fecha, new BigDecimal("1000"), "desc 8")
        );

        when(cajaRepository.findByFecha(fecha)).thenReturn(registros);

        BigDecimal saldoTotal = cajaService.obtenerSaldoTotalEnFecha(fecha);

        assertEquals(new BigDecimal("3150"), saldoTotal);

        verify(cajaRepository).findByFecha(fecha);
    }
}
