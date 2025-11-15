package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.Inventario;
import com.uniminuto.biblioteca.entity.ProductoBajoStockDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/bajo-stock")
    ResponseEntity<List<ProductoBajoStockDTO>> findBajoStock(
            @RequestParam(required = false) String ubicacion,
            @RequestParam(defaultValue = "stock_actual") String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "10") int limit
    );
}
