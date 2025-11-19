package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.Raza;
import com.uniminuto.biblioteca.entity.RazaInventarioDTO;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/raza")
public interface RazaApi {
    
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Raza>> listarRazas() throws BadRequestException;
    
    @RequestMapping(value = "/listar-activas",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Raza>> listarRazasActivas() throws BadRequestException;
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Raza> guardarRaza(@RequestBody Raza raza) throws BadRequestException;
    
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Raza> actualizarRaza(@RequestBody Raza raza) throws BadRequestException;
    
    @RequestMapping(value = "/eliminar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> eliminarRaza(@RequestBody java.util.Map<String, Object> request) throws BadRequestException;
    
    @RequestMapping(value = "/top-inventario",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<RazaInventarioDTO>> findTop5RazasByInventario() throws BadRequestException;
}

