package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.InventarioApi;
import com.uniminuto.biblioteca.entity.Inventario;
import com.uniminuto.biblioteca.entity.ProductoBajoStockDTO;
import com.uniminuto.biblioteca.services.InventarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<Inventario> guardarInventario(@RequestBody Inventario inventario) throws BadRequestException {
        return ResponseEntity.ok(inventarioService.guardarInventario(inventario));
    }

    @Override
    public ResponseEntity<Inventario> actualizarInventario(@RequestBody Inventario inventario) throws BadRequestException {
        return ResponseEntity.ok(inventarioService.actualizarInventario(inventario));
    }

    @Override
    public ResponseEntity<List<ProductoBajoStockDTO>> findBajoStock(String ubicacion, String sortBy, String order, int limit) {
        return ResponseEntity.ok(inventarioService.findBajoStock(ubicacion, sortBy, order, limit));
    }
}
