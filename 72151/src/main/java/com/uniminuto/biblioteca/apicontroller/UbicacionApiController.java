package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.UbicacionApi;
import com.uniminuto.biblioteca.entity.Ubicacion;
import com.uniminuto.biblioteca.services.UbicacionService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UbicacionApiController implements UbicacionApi {
    
    @Autowired
    private UbicacionService ubicacionService;
    
    @Override
    public ResponseEntity<List<Ubicacion>> listarUbicaciones() throws BadRequestException {
        return ResponseEntity.ok(ubicacionService.listarUbicaciones());
    }
    
    @Override
    public ResponseEntity<Ubicacion> obtenerUbicacion(Integer id) throws BadRequestException {
        return ResponseEntity.ok(ubicacionService.obtenerUbicacion(id));
    }
    
    @Override
    public ResponseEntity<Ubicacion> guardarUbicacion(Ubicacion ubicacion) throws BadRequestException {
        return ResponseEntity.ok(ubicacionService.guardarUbicacion(ubicacion));
    }
    
    @Override
    public ResponseEntity<Ubicacion> actualizarUbicacion(Ubicacion ubicacion) throws BadRequestException {
        return ResponseEntity.ok(ubicacionService.actualizarUbicacion(ubicacion));
    }
    
    @Override
    public ResponseEntity<Void> eliminarUbicacion(java.util.Map<String, Object> request) throws BadRequestException {
        Integer idDireccion = ((Number) request.get("id_direccion")).intValue();
        ubicacionService.eliminarUbicacion(idDireccion);
        return ResponseEntity.ok().build();
    }
}

