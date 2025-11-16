package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.Producto;
import com.uniminuto.biblioteca.entity.ProductoMasSalidasDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ProductoService {
    List<Producto> listarProductos() throws BadRequestException;
    List<Producto> listarProductosActivos() throws BadRequestException;
    Producto obtenerProducto(Integer idProducto) throws BadRequestException;
    Producto guardarProducto(Producto producto) throws BadRequestException;
    Producto actualizarProducto(Producto producto) throws BadRequestException;
    void eliminarProducto(Integer idProducto) throws BadRequestException;
    List<ProductoMasSalidasDTO> findProductosMasSalidas(int dias, int limit, String order) throws BadRequestException;
}

