package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.TipoMovimiento;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface TipoMovimientoService {
    List<TipoMovimiento> listarTiposMovimiento() throws BadRequestException;
}

