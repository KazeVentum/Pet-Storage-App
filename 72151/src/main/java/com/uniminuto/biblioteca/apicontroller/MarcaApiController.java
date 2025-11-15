package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.MarcaApi;
import com.uniminuto.biblioteca.entity.Marca;
import com.uniminuto.biblioteca.services.MarcaService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarcaApiController implements MarcaApi {
    
    @Autowired
    private MarcaService marcaService;
    
    @Override
    public ResponseEntity<List<Marca>> listarMarcas() throws BadRequestException {
        return ResponseEntity.ok(marcaService.listarMarcas());
    }
    
    @Override
    public ResponseEntity<Marca> guardarMarca(Marca marca) throws BadRequestException {
        return ResponseEntity.ok(marcaService.guardarMarca(marca));
    }
    
    @Override
    public ResponseEntity<Marca> actualizarMarca(Marca marca) throws BadRequestException {
        return ResponseEntity.ok(marcaService.actualizarMarca(marca));
    }
    
    @Override
    public ResponseEntity<Void> eliminarMarca(java.util.Map<String, Object> request) throws BadRequestException {
        Integer idMarca = ((Number) request.get("id_marca")).intValue();
        marcaService.eliminarMarca(idMarca);
        return ResponseEntity.ok().build();
    }
}

