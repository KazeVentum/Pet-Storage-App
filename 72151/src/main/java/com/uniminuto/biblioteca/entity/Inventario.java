package com.uniminuto.biblioteca.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "INVENTARIO")
public class Inventario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Integer idInventario;
    
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "id_direccion", nullable = false)
    private Ubicacion ubicacion;
    
    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo;
    
    @Column(name = "stock_maximo", nullable = false)
    private Integer stockMaximo;
    
    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual;
    
    @Column(name = "fecha_actualizacion")
    private Timestamp fechaActualizacion;
}

