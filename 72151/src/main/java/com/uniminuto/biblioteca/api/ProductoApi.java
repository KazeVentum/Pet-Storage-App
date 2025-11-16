package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.Producto;
import com.uniminuto.biblioteca.entity.ProductoMasSalidasDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/mas-salidas")
    ResponseEntity<List<ProductoMasSalidasDTO>> findProductosMasSalidas(
            @RequestParam(defaultValue = "30") int dias,
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "desc") String order
    ) throws BadRequestException;
}
