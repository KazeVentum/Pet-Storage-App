package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.Marca;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface MarcaService {
    List<Marca> listarMarcas() throws BadRequestException;
    Marca guardarMarca(Marca marca) throws BadRequestException;
    Marca actualizarMarca(Marca marca) throws BadRequestException;
    void eliminarMarca(Integer idMarca) throws BadRequestException;
}

