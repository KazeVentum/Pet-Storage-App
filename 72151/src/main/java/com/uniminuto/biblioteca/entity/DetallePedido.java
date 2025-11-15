package com.uniminuto.biblioteca.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DETALLE_PEDIDOS")
public class DetallePedido implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoCompra pedido;
    
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    @Column(name = "cantidad_recibida", nullable = false)
    private Integer cantidadRecibida;
    
    @Column(name = "cantidad_solicitada", nullable = false)
    private Integer cantidadSolicitada;
    
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;
    
    @Column(name = "subtotal", precision = 12, scale = 2, insertable = false, updatable = false)
    private BigDecimal subtotal;
}

