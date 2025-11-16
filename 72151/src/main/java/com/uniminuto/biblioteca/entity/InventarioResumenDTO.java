package com.uniminuto.biblioteca.entity;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;

@SqlResultSetMapping(
        name = "InventarioResumenDTOMapping",
        classes = @ConstructorResult(
                targetClass = InventarioResumenDTO.class,
                columns = {
                        @ColumnResult(name = "nombreUbicacion", type = String.class),
                        @ColumnResult(name = "totalBultos", type = Long.class),
                        @ColumnResult(name = "valorInventario", type = BigDecimal.class)
                }
        )
)
public class InventarioResumenDTO {

    private String nombreUbicacion;
    private Long totalBultos;
    private BigDecimal valorInventario;

    public InventarioResumenDTO(String nombreUbicacion, Long totalBultos, BigDecimal valorInventario) {
        this.nombreUbicacion = nombreUbicacion;
        this.totalBultos = totalBultos;
        this.valorInventario = valorInventario;
    }

    // Getters and setters
    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public Long getTotalBultos() {
        return totalBultos;
    }

    public void setTotalBultos(Long totalBultos) {
        this.totalBultos = totalBultos;
    }

    public BigDecimal getValorInventario() {
        return valorInventario;
    }

    public void setValorInventario(BigDecimal valorInventario) {
        this.valorInventario = valorInventario;
    }
}
