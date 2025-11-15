package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.Inventario;
import com.uniminuto.biblioteca.entity.ProductoBajoStockDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface InventarioService {
    List<Inventario> listarInventarios() throws BadRequestException;
    Inventario obtenerInventario(Integer idInventario) throws BadRequestException;
    List<Inventario> listarInventariosPorUbicacion(Integer idUbicacion) throws BadRequestException;
    List<Inventario> listarProductosBajoStock() throws BadRequestException;
    Inventario guardarInventario(Inventario inventario) throws BadRequestException;
    Inventario actualizarInventario(Inventario inventario) throws BadRequestException;
    List<ProductoBajoStockDTO> findBajoStock(String ubicacion, String sortBy, String order, int limit);
}

