package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.RazaApi;
import com.uniminuto.biblioteca.entity.Raza;
import com.uniminuto.biblioteca.services.RazaService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RazaApiController implements RazaApi {
    
    @Autowired
    private RazaService razaService;
    
    @Override
    public ResponseEntity<List<Raza>> listarRazas() throws BadRequestException {
        return ResponseEntity.ok(razaService.listarRazas());
    }
    
    @Override
    public ResponseEntity<List<Raza>> listarRazasActivas() throws BadRequestException {
        return ResponseEntity.ok(razaService.listarRazasActivas());
    }
    
    @Override
    public ResponseEntity<Raza> guardarRaza(Raza raza) throws BadRequestException {
        return ResponseEntity.ok(razaService.guardarRaza(raza));
    }
    
    @Override
    public ResponseEntity<Raza> actualizarRaza(Raza raza) throws BadRequestException {
        return ResponseEntity.ok(razaService.actualizarRaza(raza));
    }
    
    @Override
    public ResponseEntity<Void> eliminarRaza(java.util.Map<String, Object> request) throws BadRequestException {
        Integer idRaza = ((Number) request.get("id_raza")).intValue();
        razaService.eliminarRaza(idRaza);
        return ResponseEntity.ok().build();
    }
}

