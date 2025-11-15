package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.Raza;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface RazaService {
    List<Raza> listarRazas() throws BadRequestException;
    List<Raza> listarRazasActivas() throws BadRequestException;
    Raza guardarRaza(Raza raza) throws BadRequestException;
    Raza actualizarRaza(Raza raza) throws BadRequestException;
    void eliminarRaza(Integer idRaza) throws BadRequestException;
}

