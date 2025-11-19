package com.uniminuto.biblioteca.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "RAZAS")
public class Raza implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_raza")
    private Integer idRaza;
    
    @Column(name = "nombre_raza", nullable = false, length = 100)
    private String nombreRaza;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tamano", nullable = false, length = 20)
    private TamanoRaza tamano;
    
    @Column(name = "activo")
    private Boolean activo;
    
    public enum TamanoRaza {
        pequenio, mediano, grande
    }
}

