package com.uniminuto.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonValue;
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
@Table(name = "UBICACIONES")
public class Ubicacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer idDireccion;
    
    @Column(name = "id_ubicacion", nullable = false, unique = true, length = 20)
    private String idUbicacion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ubicacion", nullable = false, length = 20)
    private TipoUbicacion tipoUbicacion;
    
    @Column(name = "nombre_ubicacion", nullable = false, length = 100)
    private String nombreUbicacion;
    
    @Column(name = "responsable", length = 100)
    private String responsable;
    
    public enum TipoUbicacion {
        almacen, tienda, bodega;
        
        @JsonValue
        public String toValue() {
            return this.name();
        }
    }
}

