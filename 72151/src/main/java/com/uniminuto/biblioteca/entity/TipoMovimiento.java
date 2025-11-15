package com.uniminuto.biblioteca.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TIPO_MOVIMIENTO")
public class TipoMovimiento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_mov")
    private Integer idTipoMov;
    
    @Column(name = "nombre_tipo", nullable = false, unique = true, length = 50)
    private String nombreTipo;
    
    @Column(name = "descripcion", length = 200)
    private String descripcion;
}

