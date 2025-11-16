package com.uniminuto.biblioteca.entity;

public class ProductoMasSalidasDTO {

    private String nombreProducto;
    private Long totalSalidas; // Usamos Long porque SUM puede devolver un valor grande

    public ProductoMasSalidasDTO(String nombreProducto, Long totalSalidas) {
        this.nombreProducto = nombreProducto;
        this.totalSalidas = totalSalidas;
    }

    // Getters y Setters
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Long getTotalSalidas() {
        return totalSalidas;
    }

    public void setTotalSalidas(Long totalSalidas) {
        this.totalSalidas = totalSalidas;
    }
}
