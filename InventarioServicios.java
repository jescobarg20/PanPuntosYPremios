
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.Inventario;
import com.mycompany.pan.api.modelos.Menu;
import com.mycompany.pan.api.modelos.MovimientoInventario;
import com.mycompany.pan.api.repositorios.InventarioRepo;
import com.mycompany.pan.api.repositorios.MenuRepo;
import com.mycompany.pan.api.repositorios.MovInvRep;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
public class InventarioServicios {

    private final MenuRepo menuRepo;
    private final InventarioRepo inventarioRepo;
    private final MovInvRep movInvRep;
    private final GeneradorPDF generadorPDF;

    public InventarioServicios(
        InventarioRepo inventarioRepo,
        MenuRepo menuRepo,
        MovInvRep movInvRep,
        GeneradorPDF generadorPDF) {

    this.inventarioRepo = inventarioRepo;
    this.menuRepo = menuRepo;
    this.movInvRep = movInvRep;
    this.generadorPDF = generadorPDF;
}

    public List<Inventario> obtenerTodos() {
        return inventarioRepo.findAll();
    }

    public Optional<Inventario> obtenerPorId(int id) {
        return inventarioRepo.findById(id);
    }

    public Inventario guardar(Inventario inventario) {
        return inventarioRepo.save(inventario);
    }

    @Transactional
    public Inventario registrarExistencias(int idmenu, int unidades) {

        if (unidades < 0) {
            throw new IllegalArgumentException(
                    "Las unidades no pueden ser negativas."
            );
        }

        Menu menu = menuRepo.findById(idmenu)
                .orElseThrow(() ->
                        new IllegalArgumentException("Producto no encontrado.")
                );

        if (menu.getActivo() != 1) {
            throw new IllegalArgumentException(
                    "No se puede modificar el inventario de un producto inactivo."
            );
        }

        Inventario inventario = inventarioRepo.findByMenu_Idmenu(idmenu)
                .orElse(new Inventario());

        int unidadesAnteriores = inventario.getUnidades();
        int diferencia = unidades - unidadesAnteriores;

        inventario.setMenu(menu);
        inventario.setUnidades(unidades);

        Inventario inventarioGuardado = inventarioRepo.save(inventario);

        if (diferencia != 0) {

            MovimientoInventario movimiento = new MovimientoInventario();

            movimiento.setMenu(menu);
            movimiento.setCantidad(Math.abs(diferencia));

            if (diferencia > 0) {
                movimiento.setTipo("ENTRADA");
            } else {
                movimiento.setTipo("AJUSTE");
               }

            movimiento.setFecha(LocalDateTime.now());

            movInvRep.save(movimiento);
        }

        return inventarioGuardado;
    }

    public void eliminar(int id) {
        inventarioRepo.deleteById(id);
    }
    
    
public byte[] generarReportePDF() throws java.io.IOException {

    List<Inventario> inventarios = inventarioRepo.findAll();

    List<String[]> datos = new java.util.ArrayList<>();

    int totalProductos = 0;
    int totalUnidades = 0;

    for (Inventario inventario : inventarios) {

        Menu menu = inventario.getMenu();

        String tipo = menu.getIndividual() == 1
                ? "Individual"
                : "Combo";

        datos.add(new String[]{
            String.valueOf(menu.getIdmenu()),
            menu.getNombre(),
            menu.getPrecio().toString(),
            tipo,
            String.valueOf(inventario.getUnidades())
        });

        totalProductos++;
        totalUnidades += inventario.getUnidades();
    }

    List<String[]> movimientos = obtenerDatosMovimientosPDF();

    return generadorPDF.generarReporteInventario(
        datos,
        totalProductos,
        totalUnidades,
        movimientos
    );
    }


public List<String[]> obtenerDatosMovimientosPDF() {

    List<MovimientoInventario> movimientos =
            movInvRep.findByTipoInOrderByFechaDesc(
                    List.of("ENTRADA", "SALIDA_VENTA")
            );

    List<String[]> datos = new ArrayList<>();

    java.time.format.DateTimeFormatter formato =
            java.time.format.DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HH:mm"
            );

    for (MovimientoInventario movimiento : movimientos) {

        datos.add(new String[]{
            String.valueOf(movimiento.getIdmovimiento()),
            movimiento.getFecha().format(formato),
            movimiento.getMenu().getNombre(),
            String.valueOf(movimiento.getCantidad()),
            movimiento.getTipo()
        });
    }

    return datos;
}


    
}
