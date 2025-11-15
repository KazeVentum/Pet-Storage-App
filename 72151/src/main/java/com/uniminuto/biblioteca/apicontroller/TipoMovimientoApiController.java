package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.TipoMovimientoApi;
import com.uniminuto.biblioteca.entity.TipoMovimiento;
import com.uniminuto.biblioteca.services.TipoMovimientoService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoMovimientoApiController implements TipoMovimientoApi {
    
    @Autowired
    private TipoMovimientoService tipoMovimientoService;
    
    @Override
    public ResponseEntity<List<TipoMovimiento>> listarTiposMovimiento() throws BadRequestException {
        return ResponseEntity.ok(tipoMovimientoService.listarTiposMovimiento());
    }
}

