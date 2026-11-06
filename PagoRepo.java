package com.mycompany.pan.api.repositorios;

import com.mycompany.pan.api.modelos.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepo extends JpaRepository<Pago, Integer> {
    boolean existsByPedido_IdpedidoAndEstado(int idpedido, String estado);
}