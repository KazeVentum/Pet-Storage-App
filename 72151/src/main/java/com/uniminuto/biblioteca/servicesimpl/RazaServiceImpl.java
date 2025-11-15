package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.Raza;
import com.uniminuto.biblioteca.repository.RazaRepository;
import com.uniminuto.biblioteca.services.RazaService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RazaServiceImpl implements RazaService {
    
    @Autowired
    private RazaRepository razaRepository;
    
    @Override
    public List<Raza> listarRazas() throws BadRequestException {
        return razaRepository.findAll();
    }
    
    @Override
    public List<Raza> listarRazasActivas() throws BadRequestException {
        return razaRepository.findRazasActivas();
    }
    
    @Override
    public Raza guardarRaza(Raza raza) throws BadRequestException {
        if (raza.getNombreRaza() == null || raza.getNombreRaza().trim().isEmpty()) {
            throw new BadRequestException("El nombre de la raza es obligatorio");
        }
        if (raza.getTamano() == null) {
            throw new BadRequestException("El tamaño de la raza es obligatorio");
        }
        if (raza.getActivo() == null) {
            raza.setActivo(true);
        }
        return razaRepository.save(raza);
    }
    
    @Override
    public Raza actualizarRaza(Raza raza) throws BadRequestException {
        if (raza.getIdRaza() == null) {
            throw new BadRequestException("El ID de la raza es obligatorio para actualizar");
        }
        Optional<Raza> optRaza = razaRepository.findById(raza.getIdRaza());
        if (!optRaza.isPresent()) {
            throw new BadRequestException("No se encuentra la raza con el id = " + raza.getIdRaza());
        }
        return razaRepository.save(raza);
    }
    
    @Override
    public void eliminarRaza(Integer idRaza) throws BadRequestException {
        if (!razaRepository.existsById(idRaza)) {
            throw new BadRequestException("No se encuentra la raza con el id = " + idRaza);
        }
        razaRepository.deleteById(idRaza);
    }
}

