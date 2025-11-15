package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.Producto;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface ProductoService {
    List<Producto> listarProductos() throws BadRequestException;
    List<Producto> listarProductosActivos() throws BadRequestException;
    Producto obtenerProducto(Integer idProducto) throws BadRequestException;
    Producto guardarProducto(Producto producto) throws BadRequestException;
    Producto actualizarProducto(Producto producto) throws BadRequestException;
    void eliminarProducto(Integer idProducto) throws BadRequestException;
}

