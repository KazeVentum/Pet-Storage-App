package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.Ubicacion;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface UbicacionService {
    List<Ubicacion> listarUbicaciones() throws BadRequestException;
    Ubicacion obtenerUbicacion(Integer idDireccion) throws BadRequestException;
    Ubicacion guardarUbicacion(Ubicacion ubicacion) throws BadRequestException;
    Ubicacion actualizarUbicacion(Ubicacion ubicacion) throws BadRequestException;
    void eliminarUbicacion(Integer idDireccion) throws BadRequestException;
}

