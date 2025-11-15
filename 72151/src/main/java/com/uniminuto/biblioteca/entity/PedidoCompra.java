package com.uniminuto.biblioteca.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PEDIDOS_COMPRA")
public class PedidoCompra implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private EstadoPedido estado;
    
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;
    
    @Column(name = "total", precision = 12, scale = 2)
    private BigDecimal total;
    
    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;
    
    public enum EstadoPedido {
        pendiente, recibido, cancelado
    }
}

