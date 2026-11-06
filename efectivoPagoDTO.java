package com.mycompany.pan.api.modelos;

import java.math.BigDecimal;

public class efectivoPagoDTO {

    private int idpedido;
    private BigDecimal montoRecibido;

    public efectivoPagoDTO() {
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public BigDecimal getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(BigDecimal montoRecibido) {
        this.montoRecibido = montoRecibido;
    }
}