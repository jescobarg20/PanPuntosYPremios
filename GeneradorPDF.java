
package com.mycompany.pan.api.servicios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import org.springframework.stereotype.Service;

@Service
public class GeneradorPDF {

    public byte[] generarReporteInventario(
            List<String[]> datos,
            int totalProductos,
            int totalUnidades,
            List<String[]> movimientos) throws IOException {

        try (PDDocument documento = new PDDocument();
             ByteArrayOutputStream salida = new ByteArrayOutputStream()) {

            PDPage pagina = new PDPage(PDRectangle.A4);
            documento.addPage(pagina);

            PDType1Font fuente = new PDType1Font(
                    Standard14Fonts.FontName.HELVETICA
            );

            PDType1Font fuenteNegrita = new PDType1Font(
                    Standard14Fonts.FontName.HELVETICA_BOLD
            );

            try (PDPageContentStream contenido =
                    new PDPageContentStream(documento, pagina)) {

                float margen = 50;
                float y = 790;

                escribirTexto(contenido, fuenteNegrita, 16,
                        margen, y, "Reporte de inventarios");

                y -= 30;

                String fecha = LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                );

                escribirTexto(contenido, fuente, 10,
                        margen, y, "Fecha de creación: " + fecha);

                y -= 40;

                escribirTexto(contenido, fuenteNegrita, 12,
                        margen, y, "INVENTARIO ACTUAL");

                y -= 25;

                escribirTexto(contenido, fuenteNegrita, 9,
                        margen, y, "ID");

                escribirTexto(contenido, fuenteNegrita, 9,
                        95, y, "PRODUCTO");

                escribirTexto(contenido, fuenteNegrita, 9,
                        300, y, "PRECIO");

                escribirTexto(contenido, fuenteNegrita, 9,
                        370, y, "TIPO");

                escribirTexto(contenido, fuenteNegrita, 9,
                        445, y, "UNIDADES");

                y -= 20;

                for (String[] fila : datos) {

                    if (y < 100) {
                        break;
                    }

                    escribirTexto(contenido, fuente, 8,
                            margen, y, fila[0]);

                    escribirTexto(contenido, fuente, 8,
                            95, y, fila[1]);

                    escribirTexto(contenido, fuente, 8,
                            300, y, fila[2]);

                    escribirTexto(contenido, fuente, 8,
                            370, y, fila[3]);

                    escribirTexto(contenido, fuente, 8,
                            445, y, fila[4]);

                    y -= 16;
                }

                y -= 20;

                escribirTexto(contenido, fuenteNegrita, 9,
                        margen, y,
                        "Total de productos: " + totalProductos);

                y -= 16;

                escribirTexto(contenido, fuenteNegrita, 9,
                        margen, y,
                        "Total de unidades: " + totalUnidades);


                y -= 35;

                escribirTexto(contenido, fuenteNegrita, 12,
                        margen, y, "HISTORIAL DE MOVIMIENTOS");

                y -= 25;

                escribirTexto(contenido, fuenteNegrita, 8,
                        margen, y, "ID");

                escribirTexto(contenido, fuenteNegrita, 8,
                        95, y, "FECHA");

                escribirTexto(contenido, fuenteNegrita, 8,
                        220, y, "PRODUCTO");

                escribirTexto(contenido, fuenteNegrita, 8,
                        350, y, "CANTIDAD");

                escribirTexto(contenido, fuenteNegrita, 8,
                        430, y, "TIPO");

                y -= 18;

                for (String[] movimiento : movimientos) {

                    if (y < 60) {
                        break;
                    }

                    escribirTexto(contenido, fuente, 7,
                            margen, y, movimiento[0]);

                    escribirTexto(contenido, fuente, 7,
                            95, y, movimiento[1]);

                    escribirTexto(contenido, fuente, 7,
                            220, y, movimiento[2]);

                    escribirTexto(contenido, fuente, 7,
                            350, y, movimiento[3]);

                    escribirTexto(contenido, fuente, 7,
                            430, y, movimiento[4]);

                    y -= 15;
                }
            }

            documento.save(salida);

            return salida.toByteArray();
        }
    }

    private void escribirTexto(
            PDPageContentStream contenido,
            PDType1Font fuente,
            int tamano,
            float x,
            float y,
            String texto) throws IOException {

        contenido.beginText();
        contenido.setFont(fuente, tamano);
        contenido.newLineAtOffset(x, y);
        contenido.showText(texto);
        contenido.endText();
    }
}
