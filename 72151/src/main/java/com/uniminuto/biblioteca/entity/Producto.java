package com.uniminuto.biblioteca.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTOS")
public class Producto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;
    
    @Column(name = "nombre_producto", nullable = false, length = 200)
    private String nombreProducto;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;
    
    @Column(name = "precio_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private EstadoProducto estado;
    
    @Column(name = "peso_kg", precision = 8, scale = 2)
    private BigDecimal pesoKg;
    
    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;
    
    @ManyToMany
    @JoinTable(
        name = "PRODUCTOS_RAZAS",
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "id_raza")
    )
    private Set<Raza> razas;
    
    public enum EstadoProducto {
        activo, descontinuado, agotado
    }
}

