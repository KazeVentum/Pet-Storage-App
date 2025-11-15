package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.Ubicacion;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/ubicacion")
public interface UbicacionApi {
    
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Ubicacion>> listarUbicaciones() throws BadRequestException;
    
    @RequestMapping(value = "/obtener/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Ubicacion> obtenerUbicacion(@PathVariable("id") Integer id) throws BadRequestException;
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Ubicacion> guardarUbicacion(@RequestBody Ubicacion ubicacion) throws BadRequestException;
    
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Ubicacion> actualizarUbicacion(@RequestBody Ubicacion ubicacion) throws BadRequestException;
    
    @RequestMapping(value = "/eliminar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> eliminarUbicacion(@RequestBody java.util.Map<String, Object> request) throws BadRequestException;
}

