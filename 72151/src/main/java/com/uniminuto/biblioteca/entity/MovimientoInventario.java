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
@Table(name = "MOVIMIENTOS_INVENTARIO")
public class MovimientoInventario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;
    
    @ManyToOne
    @JoinColumn(name = "id_inventario", nullable = false)
    private Inventario inventario;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_mov", nullable = false)
    private TipoMovimiento tipoMovimiento;
    
    @ManyToOne
    @JoinColumn(name = "id_direccion_origen")
    private Ubicacion ubicacionOrigen;
    
    @ManyToOne
    @JoinColumn(name = "id_direccion_destino")
    private Ubicacion ubicacionDestino;
    
    @Column(name = "cantidad_bultoa", nullable = false)
    private Integer cantidadBultoa;
    
    @Column(name = "fecha_movimiento")
    private Timestamp fechaMovimiento;
}

