package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.UbicacionApi;
import com.uniminuto.biblioteca.entity.Ubicacion;
import com.uniminuto.biblioteca.services.UbicacionService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UbicacionApiController implements UbicacionApi {
    
    private static final Logger logger = LoggerFactory.getLogger(UbicacionApiController.class);
    
    @Autowired
    private UbicacionService ubicacionService;
    
    @Override
    public ResponseEntity<List<Ubicacion>> listarUbicaciones() throws BadRequestException {
        try {
            logger.info("Solicitud para listar ubicaciones recibida");
            List<Ubicacion> ubicaciones = ubicacionService.listarUbicaciones();
            logger.info("Ubicaciones encontradas: {}", ubicaciones.size());
            return ResponseEntity.ok(ubicaciones);
        } catch (BadRequestException e) {
            logger.error("Error al listar ubicaciones: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado al listar ubicaciones", e);
            throw new BadRequestException("Error inesperado al listar ubicaciones: " + e.getMessage());
        }
    }
    
    @Override
    public ResponseEntity<Ubicacion> obtenerUbicacion(Integer id) throws BadRequestException {
        try {
            logger.info("Solicitud para obtener ubicación con id: {}", id);
            return ResponseEntity.ok(ubicacionService.obtenerUbicacion(id));
        } catch (BadRequestException e) {
            logger.error("Error al obtener ubicación: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado al obtener ubicación", e);
            throw new BadRequestException("Error inesperado al obtener ubicación: " + e.getMessage());
        }
    }
    
    @Override
    public ResponseEntity<Ubicacion> guardarUbicacion(Ubicacion ubicacion) throws BadRequestException {
        try {
            logger.info("Solicitud para guardar ubicación: {}", ubicacion.getNombreUbicacion());
            return ResponseEntity.ok(ubicacionService.guardarUbicacion(ubicacion));
        } catch (BadRequestException e) {
            logger.error("Error al guardar ubicación: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado al guardar ubicación", e);
            throw new BadRequestException("Error inesperado al guardar ubicación: " + e.getMessage());
        }
    }
    
    @Override
    public ResponseEntity<Ubicacion> actualizarUbicacion(Ubicacion ubicacion) throws BadRequestException {
        try {
            logger.info("Solicitud para actualizar ubicación con id: {}", ubicacion.getIdDireccion());
            return ResponseEntity.ok(ubicacionService.actualizarUbicacion(ubicacion));
        } catch (BadRequestException e) {
            logger.error("Error al actualizar ubicación: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado al actualizar ubicación", e);
            throw new BadRequestException("Error inesperado al actualizar ubicación: " + e.getMessage());
        }
    }
    
    @Override
    public ResponseEntity<Void> eliminarUbicacion(java.util.Map<String, Object> request) throws BadRequestException {
        try {
            Integer idDireccion = ((Number) request.get("id_direccion")).intValue();
            logger.info("Solicitud para eliminar ubicación con id: {}", idDireccion);
            ubicacionService.eliminarUbicacion(idDireccion);
            return ResponseEntity.ok().build();
        } catch (BadRequestException e) {
            logger.error("Error al eliminar ubicación: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado al eliminar ubicación", e);
            throw new BadRequestException("Error inesperado al eliminar ubicación: " + e.getMessage());
        }
    }
}

