package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.Marca;
import com.uniminuto.biblioteca.repository.MarcaRepository;
import com.uniminuto.biblioteca.services.MarcaService;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;
    
    @Override
    public List<Marca> listarMarcas() throws BadRequestException {
        return marcaRepository.findAll();
    }
    
    @Override
    public Marca guardarMarca(Marca marca) throws BadRequestException {
        if (marca.getNombreMarca() == null || marca.getNombreMarca().trim().isEmpty()) {
            throw new BadRequestException("El nombre de la marca es obligatorio");
        }
        marca.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        return marcaRepository.save(marca);
    }
    
    @Override
    public Marca actualizarMarca(Marca marca) throws BadRequestException {
        if (marca.getIdMarca() == null) {
            throw new BadRequestException("El ID de la marca es obligatorio para actualizar");
        }
        Optional<Marca> optMarca = marcaRepository.findById(marca.getIdMarca());
        if (!optMarca.isPresent()) {
            throw new BadRequestException("No se encuentra la marca con el id = " + marca.getIdMarca());
        }
        Marca marcaExistente = optMarca.get();
        marcaExistente.setNombreMarca(marca.getNombreMarca());
        return marcaRepository.save(marcaExistente);
    }
    
    @Override
    public void eliminarMarca(Integer idMarca) throws BadRequestException {
        if (!marcaRepository.existsById(idMarca)) {
            throw new BadRequestException("No se encuentra la marca con el id = " + idMarca);
        }
        marcaRepository.deleteById(idMarca);
    }
}

