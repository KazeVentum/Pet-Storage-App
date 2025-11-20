package com.uniminuto.biblioteca.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoProductoDTO {
    // Fields from DetallePedido
    private Integer idDetalle;
    private Integer idPedido; // Parent PedidoCompra ID
    private Integer cantidadRecibida;
    private Integer cantidadSolicitada;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    // Fields from Producto
    private Integer idProducto;
    private String nombreProducto;
    private String descripcionProducto;
}
