package com.uniminuto.biblioteca.entity;

public class ProductoBajoStockDTO {

    private String nombreProducto;
    private int stockActual;
    private int stockMinimo;
    private String nombreMarca;

    public ProductoBajoStockDTO(String nombreProducto, int stockActual, int stockMinimo, String nombreMarca) {
        this.nombreProducto = nombreProducto;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.nombreMarca = nombreMarca;
    }

    // Getters y Setters
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }
}
