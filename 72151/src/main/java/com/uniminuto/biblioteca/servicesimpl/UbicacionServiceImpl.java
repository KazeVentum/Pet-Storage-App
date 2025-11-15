package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.Ubicacion;
import com.uniminuto.biblioteca.repository.UbicacionRepository;
import com.uniminuto.biblioteca.services.UbicacionService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionServiceImpl implements UbicacionService {
    
    @Autowired
    private UbicacionRepository ubicacionRepository;
    
    @Override
    public List<Ubicacion> listarUbicaciones() throws BadRequestException {
        return ubicacionRepository.findAll();
    }
    
    @Override
    public Ubicacion obtenerUbicacion(Integer idDireccion) throws BadRequestException {
        Optional<Ubicacion> optUbicacion = ubicacionRepository.findById(idDireccion);
        if (!optUbicacion.isPresent()) {
            throw new BadRequestException("No se encuentra la ubicación con el id = " + idDireccion);
        }
        return optUbicacion.get();
    }
    
    @Override
    public Ubicacion guardarUbicacion(Ubicacion ubicacion) throws BadRequestException {
        if (ubicacion.getIdUbicacion() == null || ubicacion.getIdUbicacion().trim().isEmpty()) {
            throw new BadRequestException("El ID de ubicación es obligatorio");
        }
        if (ubicacion.getNombreUbicacion() == null || ubicacion.getNombreUbicacion().trim().isEmpty()) {
            throw new BadRequestException("El nombre de la ubicación es obligatorio");
        }
        if (ubicacion.getTipoUbicacion() == null) {
            throw new BadRequestException("El tipo de ubicación es obligatorio");
        }
        return ubicacionRepository.save(ubicacion);
    }
    
    @Override
    public Ubicacion actualizarUbicacion(Ubicacion ubicacion) throws BadRequestException {
        if (ubicacion.getIdDireccion() == null) {
            throw new BadRequestException("El ID de dirección es obligatorio para actualizar");
        }
        Optional<Ubicacion> optUbicacion = ubicacionRepository.findById(ubicacion.getIdDireccion());
        if (!optUbicacion.isPresent()) {
            throw new BadRequestException("No se encuentra la ubicación con el id = " + ubicacion.getIdDireccion());
        }
        return ubicacionRepository.save(ubicacion);
    }
    
    @Override
    public void eliminarUbicacion(Integer idDireccion) throws BadRequestException {
        if (!ubicacionRepository.existsById(idDireccion)) {
            throw new BadRequestException("No se encuentra la ubicación con el id = " + idDireccion);
        }
        ubicacionRepository.deleteById(idDireccion);
    }
}

