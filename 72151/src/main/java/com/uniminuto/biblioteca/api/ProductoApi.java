package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.Producto;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/producto")
public interface ProductoApi {
    
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Producto>> listarProductos() throws BadRequestException;
    
    @RequestMapping(value = "/listar-activos",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Producto>> listarProductosActivos() throws BadRequestException;
    
    @RequestMapping(value = "/obtener/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Producto> obtenerProducto(@PathVariable("id") Integer id) throws BadRequestException;
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) throws BadRequestException;
    
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Producto> actualizarProducto(@RequestBody Producto producto) throws BadRequestException;
    
    @RequestMapping(value = "/eliminar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> eliminarProducto(@RequestBody java.util.Map<String, Object> request) throws BadRequestException;
}

