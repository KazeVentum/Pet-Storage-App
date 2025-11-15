package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.MovimientoInventario;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/movimiento-inventario")
public interface MovimientoInventarioApi {
    
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<MovimientoInventario>> listarMovimientos() throws BadRequestException;
    
    @RequestMapping(value = "/obtener/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<MovimientoInventario> obtenerMovimiento(@PathVariable("id") Integer id) throws BadRequestException;
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<MovimientoInventario> guardarMovimiento(@RequestBody MovimientoInventario movimiento) throws BadRequestException;
}

