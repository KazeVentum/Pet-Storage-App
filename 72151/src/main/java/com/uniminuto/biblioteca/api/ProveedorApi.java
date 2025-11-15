package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.Proveedor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/proveedor")
public interface ProveedorApi {

    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Proveedor>> listarProveedores() throws BadRequestException;

    @RequestMapping(value = "/listar-activos",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Proveedor>> listarProveedoresActivos() throws BadRequestException;

    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor) throws BadRequestException;

    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Proveedor> actualizarProveedor(@RequestBody Proveedor proveedor) throws BadRequestException;

    @RequestMapping(value = "/eliminar",
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> eliminarProveedor(@RequestBody Map<String, Object> request) throws BadRequestException;
}
