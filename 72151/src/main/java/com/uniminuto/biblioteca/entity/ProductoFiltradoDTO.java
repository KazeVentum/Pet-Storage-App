package com.uniminuto.biblioteca.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoFiltradoDTO {
    private String nombreProducto;
    private BigDecimal precioVenta;
    private BigDecimal pesoKg;
}