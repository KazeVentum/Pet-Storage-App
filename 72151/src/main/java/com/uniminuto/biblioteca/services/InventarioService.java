package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.Inventario;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface InventarioService {
    List<Inventario> listarInventarios() throws BadRequestException;
    Inventario obtenerInventario(Integer idInventario) throws BadRequestException;
    List<Inventario> listarInventariosPorUbicacion(Integer idUbicacion) throws BadRequestException;
    List<Inventario> listarProductosBajoStock() throws BadRequestException;
    Inventario guardarInventario(Inventario inventario) throws BadRequestException;
    Inventario actualizarInventario(Inventario inventario) throws BadRequestException;
}

