package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.TipoMovimiento;
import com.uniminuto.biblioteca.repository.TipoMovimientoRepository;
import com.uniminuto.biblioteca.services.TipoMovimientoService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoMovimientoServiceImpl implements TipoMovimientoService {
    
    @Autowired
    private TipoMovimientoRepository tipoMovimientoRepository;
    
    @Override
    public List<TipoMovimiento> listarTiposMovimiento() throws BadRequestException {
        return tipoMovimientoRepository.findAll();
    }
}

