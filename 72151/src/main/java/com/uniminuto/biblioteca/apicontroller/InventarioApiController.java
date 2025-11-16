package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.InventarioApi;
import com.uniminuto.biblioteca.entity.Inventario;
import com.uniminuto.biblioteca.entity.InventarioResumenDTO;
import com.uniminuto.biblioteca.entity.ProductoBajoStockDTO;
import com.uniminuto.biblioteca.services.InventarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Override
    public ResponseEntity<List<InventarioResumenDTO>> resumenInventario(
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false, defaultValue = "valorInventario") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String order,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {

        String actualSortBy;
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "total_bultos":
                    actualSortBy = "totalBultos";
                    break;
                case "nombre_ubicacion":
                    actualSortBy = "nombreUbicacion";
                    break;
                default:
                    actualSortBy = "valorInventario";
                    break;
            }
        } else {
            actualSortBy = "valorInventario";
        }

        String actualOrder = "DESC";
        if (order != null && !order.isEmpty() && order.equalsIgnoreCase("ASC")) {
            actualOrder = "ASC";
        }

        String orderByClause = actualSortBy + " " + actualOrder;

        return ResponseEntity.ok(inventarioService.resumenInventario(ubicacion, orderByClause, limit));
    }
}
