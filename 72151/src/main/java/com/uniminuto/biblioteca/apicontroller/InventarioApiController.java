package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.InventarioApi;
import com.uniminuto.biblioteca.entity.Inventario;
import com.uniminuto.biblioteca.services.InventarioService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventarioApiController implements InventarioApi {
    
    @Autowired
    private InventarioService inventarioService;
    
    @Override
    public ResponseEntity<List<Inventario>> listarInventarios() throws BadRequestException {
        return ResponseEntity.ok(inventarioService.listarInventarios());
    }
    
    @Override
    public ResponseEntity<Inventario> obtenerInventario(Integer id) throws BadRequestException {
        return ResponseEntity.ok(inventarioService.obtenerInventario(id));
    }
    
    @Override
    public ResponseEntity<List<Inventario>> listarInventariosPorUbicacion(Integer idUbicacion) throws BadRequestException {
        return ResponseEntity.ok(inventarioService.listarInventariosPorUbicacion(idUbicacion));
    }
    
    @Override
    public ResponseEntity<List<Inventario>> listarProductosBajoStock() throws BadRequestException {
        return ResponseEntity.ok(inventarioService.listarProductosBajoStock());
    }
    
    @Override
    public ResponseEntity<Inventario> guardarInventario(Inventario inventario) throws BadRequestException {
        return ResponseEntity.ok(inventarioService.guardarInventario(inventario));
    }
    
    @Override
    public ResponseEntity<Inventario> actualizarInventario(Inventario inventario) throws BadRequestException {
        return ResponseEntity.ok(inventarioService.actualizarInventario(inventario));
    }
}

