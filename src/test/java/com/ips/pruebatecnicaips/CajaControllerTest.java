package com.ips.pruebatecnicaips;

import com.ips.pruebatecnicaips.controller.CajaController;
import com.ips.pruebatecnicaips.service.CajaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CajaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CajaService cajaService;

    @InjectMocks
    private CajaController cajaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cajaController).build();
    }

    @Test
    public void testObtenerSaldoPorCajaYFecha() throws Exception {
        Integer numeroCaja = 1;
        LocalDate fecha = LocalDate.of(2023, 1, 1);
        BigDecimal expectedSaldo = new BigDecimal("1000");

        when(cajaService.obtenerSaldoPorCajaYFecha(numeroCaja, fecha)).thenReturn(expectedSaldo);

        mockMvc.perform(get("/api/cajas/saldo")
                        .param("numeroCaja", numeroCaja.toString())
                        .param("fecha", fecha.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedSaldo.toString()));
    }

    /**
     * Solo pongo una vez el Test porque el ControllerAdvice es global
     * asi que ocurrirá con todos los controladores actuales y futuros.
     */
    @Test
    public void testFechaInvalida() throws Exception {
        mockMvc.perform(get("/api/cajas/saldo")
                        .param("numeroCaja", "1")
                        .param("fecha", "invalid-date"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testObtenerSaldoTotalEnFecha() throws Exception {
        LocalDate fecha = LocalDate.of(2023, 1, 20);
        BigDecimal expectedSaldoTotal = new BigDecimal("5000");

        when(cajaService.obtenerSaldoTotalEnFecha(fecha)).thenReturn(expectedSaldoTotal);

        mockMvc.perform(get("/api/cajas/saldo-total")
                        .param("fecha", fecha.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedSaldoTotal.toString()));
    }


}
