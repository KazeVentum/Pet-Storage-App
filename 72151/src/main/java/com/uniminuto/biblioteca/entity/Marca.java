package com.uniminuto.biblioteca.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MARCAS")
public class Marca implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Integer idMarca;
    
    @Column(name = "nombre_marca", nullable = false, unique = true, length = 100)
    private String nombreMarca;
    
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
}

