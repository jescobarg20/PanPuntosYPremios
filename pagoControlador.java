package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.Pago;
import com.mycompany.pan.api.modelos.efectivoPagoDTO;
import com.mycompany.pan.api.servicios.PagoServicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class pagoControlador {

    private final PagoServicio pagoServicio;

    public pagoControlador(PagoServicio pagoServicio) {
        this.pagoServicio = pagoServicio;
    }

    @PostMapping("/efectivo")
    public ResponseEntity<?> pagarEnEfectivo(
            @RequestBody efectivoPagoDTO datos) {

        try {
            Pago pago = pagoServicio.pagarEnEfectivo(datos);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pago);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}