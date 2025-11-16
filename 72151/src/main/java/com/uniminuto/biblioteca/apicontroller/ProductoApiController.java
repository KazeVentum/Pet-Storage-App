package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.ProductoApi;
import com.uniminuto.biblioteca.entity.Producto;
import com.uniminuto.biblioteca.entity.ProductoMasSalidasDTO;
import com.uniminuto.biblioteca.services.ProductoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoApiController implements ProductoApi {

    @Autowired
    private ProductoService productoService;

    @Override
    public ResponseEntity<List<Producto>> listarProductos() throws BadRequestException {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @Override
    public ResponseEntity<List<Producto>> listarProductosActivos() throws BadRequestException {
        return ResponseEntity.ok(productoService.listarProductosActivos());
    }

    @Override
    public ResponseEntity<Producto> obtenerProducto(Integer id) throws BadRequestException {
        return ResponseEntity.ok(productoService.obtenerProducto(id));
    }

    @Override
    public ResponseEntity<Producto> guardarProducto(Producto producto) throws BadRequestException {
        return ResponseEntity.ok(productoService.guardarProducto(producto));
    }

    @Override
    public ResponseEntity<Producto> actualizarProducto(Producto producto) throws BadRequestException {
        return ResponseEntity.ok(productoService.actualizarProducto(producto));
    }

    @Override
    public ResponseEntity<Void> eliminarProducto(java.util.Map<String, Object> request) throws BadRequestException {
        Integer idProducto = ((Number) request.get("id_producto")).intValue();
        productoService.eliminarProducto(idProducto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ProductoMasSalidasDTO>> findProductosMasSalidas(int dias, int limit, String order) throws BadRequestException {
        return ResponseEntity.ok(productoService.findProductosMasSalidas(dias, limit, order));
    }
}
