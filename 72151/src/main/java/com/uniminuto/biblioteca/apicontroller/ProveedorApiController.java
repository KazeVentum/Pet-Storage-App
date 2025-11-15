package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.ProveedorApi;
import com.uniminuto.biblioteca.entity.Proveedor;
import com.uniminuto.biblioteca.services.ProveedorService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProveedorApiController implements ProveedorApi {

    @Autowired
    private ProveedorService proveedorService;

    @Override
    public ResponseEntity<List<Proveedor>> listarProveedores() throws BadRequestException {
        return ResponseEntity.ok(proveedorService.listarProveedores());
    }

    @Override
    public ResponseEntity<List<Proveedor>> listarProveedoresActivos() throws BadRequestException {
        return ResponseEntity.ok(proveedorService.listarProveedoresActivos());
    }

    @Override
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor) throws BadRequestException {
        return ResponseEntity.ok(proveedorService.guardarProveedor(proveedor));
    }

    @Override
    public ResponseEntity<Proveedor> actualizarProveedor(@RequestBody Proveedor proveedor) throws BadRequestException {
        return ResponseEntity.ok(proveedorService.actualizarProveedor(proveedor));
    }

    @Override
    public ResponseEntity<Void> eliminarProveedor(@RequestBody Map<String, Object> request) throws BadRequestException {
        Integer idProveedor = ((Number) request.get("id_proveedor")).intValue();
        proveedorService.eliminarProveedor(idProveedor);
        return ResponseEntity.ok().build();
    }
}


