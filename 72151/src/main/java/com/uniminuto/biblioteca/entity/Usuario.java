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
@Table(name = "USUARIOS")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;
    
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    
    @Column(name = "activo")
    private Boolean activo;
    
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
}
