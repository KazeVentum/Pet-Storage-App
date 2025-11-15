package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.Proveedor;
import com.uniminuto.biblioteca.repository.ProveedorRepository;
import com.uniminuto.biblioteca.services.ProveedorService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    
    @Autowired
    private ProveedorRepository proveedorRepository;
    
    @Override
    public List<Proveedor> listarProveedores() throws BadRequestException {
        return proveedorRepository.findAll();
    }
    
    @Override
    public List<Proveedor> listarProveedoresActivos() throws BadRequestException {
        return proveedorRepository.findProveedoresActivos();
    }
    
    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) throws BadRequestException {
        if (proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().trim().isEmpty()) {
            throw new BadRequestException("El nombre del proveedor es obligatorio");
        }
        if (proveedor.getActivo() == null) {
            proveedor.setActivo(true);
        }
        return proveedorRepository.save(proveedor);
    }
    
    @Override
    public Proveedor actualizarProveedor(Proveedor proveedor) throws BadRequestException {
        if (proveedor.getIdProveedor() == null) {
            throw new BadRequestException("El ID del proveedor es obligatorio para actualizar");
        }
        Optional<Proveedor> optProveedor = proveedorRepository.findById(proveedor.getIdProveedor());
        if (!optProveedor.isPresent()) {
            throw new BadRequestException("No se encuentra el proveedor con el id = " + proveedor.getIdProveedor());
        }
        return proveedorRepository.save(proveedor);
    }
    
    @Override
    public void eliminarProveedor(Integer idProveedor) throws BadRequestException {
        if (!proveedorRepository.existsById(idProveedor)) {
            throw new BadRequestException("No se encuentra el proveedor con el id = " + idProveedor);
        }
        proveedorRepository.deleteById(idProveedor);
    }
}

