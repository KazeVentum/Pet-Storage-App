package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.Proveedor;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface ProveedorService {
    List<Proveedor> listarProveedores() throws BadRequestException;
    List<Proveedor> listarProveedoresActivos() throws BadRequestException;
    Proveedor guardarProveedor(Proveedor proveedor) throws BadRequestException;
    Proveedor actualizarProveedor(Proveedor proveedor) throws BadRequestException;
    void eliminarProveedor(Integer idProveedor) throws BadRequestException;
}

