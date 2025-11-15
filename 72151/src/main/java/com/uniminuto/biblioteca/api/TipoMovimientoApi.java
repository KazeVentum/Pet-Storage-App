package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.TipoMovimiento;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/tipo-movimiento")
public interface TipoMovimientoApi {
    
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<TipoMovimiento>> listarTiposMovimiento() throws BadRequestException;
}

