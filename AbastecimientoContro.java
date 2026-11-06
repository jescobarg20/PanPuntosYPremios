
package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.ElAjusteInvDTO;
import com.mycompany.pan.api.modelos.MovimientoInventario;
import com.mycompany.pan.api.servicios.AbastecimientoServ;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/abastecimientos")
public class AbastecimientoContro {

    private final AbastecimientoServ abastecimientoServ;

    public AbastecimientoContro(
            AbastecimientoServ abastecimientoServ) {
        this.abastecimientoServ = abastecimientoServ;
    }

    @PostMapping("/ajustes")
    public ResponseEntity<?> registrarAjuste(
            @RequestBody ElAjusteInvDTO dto) {

        try {
            MovimientoInventario movimiento =
                    abastecimientoServ.registrarAjuste(dto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(movimiento);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
