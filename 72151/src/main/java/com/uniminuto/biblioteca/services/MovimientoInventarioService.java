package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.MovimientoInventario;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface MovimientoInventarioService {
    List<MovimientoInventario> listarMovimientos() throws BadRequestException;
    MovimientoInventario obtenerMovimiento(Integer idMovimiento) throws BadRequestException;
    MovimientoInventario guardarMovimiento(MovimientoInventario movimiento) throws BadRequestException;
}

