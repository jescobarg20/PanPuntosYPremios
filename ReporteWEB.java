
package com.mycompany.pan.api.vistas;

import com.mycompany.pan.api.servicios.InventarioServicios;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporteWEB {

    private final InventarioServicios inventarioServicios;

    public ReporteWEB(InventarioServicios inventarioServicios) {
        this.inventarioServicios = inventarioServicios;
    }

    @GetMapping("/reportes/inventario/pdf")
    public ResponseEntity<byte[]> descargarReporteInventario()
            throws IOException {

        byte[] pdf = inventarioServicios.generarReportePDF();

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=reporte-inventario.pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
    
    @GetMapping("/reportes")
    public String mostrarReportes() {
        return "reportes";
    }
}
