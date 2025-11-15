package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.MovimientoInventarioApi;
import com.uniminuto.biblioteca.entity.MovimientoInventario;
import com.uniminuto.biblioteca.services.MovimientoInventarioService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovimientoInventarioApiController implements MovimientoInventarioApi {
    
    @Autowired
    private MovimientoInventarioService movimientoInventarioService;
    
    @Override
    public ResponseEntity<List<MovimientoInventario>> listarMovimientos() throws BadRequestException {
        return ResponseEntity.ok(movimientoInventarioService.listarMovimientos());
    }
    
    @Override
    public ResponseEntity<MovimientoInventario> obtenerMovimiento(Integer id) throws BadRequestException {
        return ResponseEntity.ok(movimientoInventarioService.obtenerMovimiento(id));
    }
    
    @Override
    public ResponseEntity<MovimientoInventario> guardarMovimiento(MovimientoInventario movimiento) throws BadRequestException {
        return ResponseEntity.ok(movimientoInventarioService.guardarMovimiento(movimiento));
    }
}

