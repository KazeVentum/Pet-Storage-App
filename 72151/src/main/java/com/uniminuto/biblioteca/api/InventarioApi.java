package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.Inventario;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/inventario")
public interface InventarioApi {
    
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Inventario>> listarInventarios() throws BadRequestException;
    
    @RequestMapping(value = "/obtener/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Inventario> obtenerInventario(@PathVariable("id") Integer id) throws BadRequestException;
    
    @RequestMapping(value = "/listar-por-ubicacion/{idUbicacion}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Inventario>> listarInventariosPorUbicacion(@PathVariable("idUbicacion") Integer idUbicacion) throws BadRequestException;
    
    @RequestMapping(value = "/productos-bajo-stock",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Inventario>> listarProductosBajoStock() throws BadRequestException;
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Inventario> guardarInventario(@RequestBody Inventario inventario) throws BadRequestException;
    
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Inventario> actualizarInventario(@RequestBody Inventario inventario) throws BadRequestException;
}

