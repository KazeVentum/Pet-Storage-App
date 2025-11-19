package com.uniminuto.biblioteca.entity;

import java.sql.Date; // Still using java.sql.Date for the field
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoCompraDetalleDTO {
    private Integer idPedido;
    private Date fechaPedido; // Using java.sql.Date for field
    private String nombreProveedor;
    private String telefonoProveedor;
    private String emailProveedor;

    // Explicit constructor to match JPQL's expected arguments
    public PedidoCompraDetalleDTO(Integer idPedido, java.util.Date fechaPedido, String nombreProveedor, String telefonoProveedor, String emailProveedor) {
        this.idPedido = idPedido;
        // Cast java.util.Date to java.sql.Date if the field is java.sql.Date and it's not null
        this.fechaPedido = (fechaPedido != null) ? new java.sql.Date(fechaPedido.getTime()) : null;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.emailProveedor = emailProveedor;
    }
}
