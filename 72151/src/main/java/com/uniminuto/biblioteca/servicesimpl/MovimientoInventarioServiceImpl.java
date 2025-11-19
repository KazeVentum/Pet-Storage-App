package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.Inventario;
import com.uniminuto.biblioteca.entity.MovimientoInventario;
import com.uniminuto.biblioteca.entity.TipoMovimiento;
import com.uniminuto.biblioteca.entity.Ubicacion;
import com.uniminuto.biblioteca.entity.Usuario;
import com.uniminuto.biblioteca.repository.InventarioRepository;
import com.uniminuto.biblioteca.repository.MovimientoInventarioRepository;
import com.uniminuto.biblioteca.repository.TipoMovimientoRepository;
import com.uniminuto.biblioteca.repository.UbicacionRepository;
import com.uniminuto.biblioteca.repository.UsuarioRepository;
import com.uniminuto.biblioteca.services.MovimientoInventarioService;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {
    
    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;
    
    @Autowired
    private InventarioRepository inventarioRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private TipoMovimientoRepository tipoMovimientoRepository;
    
    @Autowired
    private UbicacionRepository ubicacionRepository;
    
    @Override
    public List<MovimientoInventario> listarMovimientos() throws BadRequestException {
        return movimientoInventarioRepository.findAll();
    }
    
    @Override
    public MovimientoInventario obtenerMovimiento(Integer idMovimiento) throws BadRequestException {
        Optional<MovimientoInventario> optMovimiento = movimientoInventarioRepository.findById(idMovimiento);
        if (!optMovimiento.isPresent()) {
            throw new BadRequestException("No se encuentra el movimiento con el id = " + idMovimiento);
        }
        return optMovimiento.get();
    }
    
    @Override
    @Transactional
    public MovimientoInventario guardarMovimiento(MovimientoInventario movimiento) throws BadRequestException {
        if (movimiento.getInventario() == null || movimiento.getInventario().getIdInventario() == null) {
            throw new BadRequestException("El inventario es obligatorio");
        }
        if (movimiento.getUsuario() == null || movimiento.getUsuario().getIdUsuario() == null) {
            throw new BadRequestException("El usuario es obligatorio");
        }
        if (movimiento.getTipoMovimiento() == null || movimiento.getTipoMovimiento().getIdTipoMov() == null) {
            throw new BadRequestException("El tipo de movimiento es obligatorio");
        }
        if (movimiento.getCantidad() == null || movimiento.getCantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a cero");
        }
        
        Optional<Inventario> optInventario = inventarioRepository.findById(movimiento.getInventario().getIdInventario());
        if (!optInventario.isPresent()) {
            throw new BadRequestException("No se encuentra el inventario con el id = " + movimiento.getInventario().getIdInventario());
        }
        movimiento.setInventario(optInventario.get());
        
        Optional<Usuario> optUsuario = usuarioRepository.findById(movimiento.getUsuario().getIdUsuario());
        if (!optUsuario.isPresent()) {
            throw new BadRequestException("No se encuentra el usuario con el id = " + movimiento.getUsuario().getIdUsuario());
        }
        movimiento.setUsuario(optUsuario.get());
        
        Optional<TipoMovimiento> optTipoMov = tipoMovimientoRepository.findById(movimiento.getTipoMovimiento().getIdTipoMov());
        if (!optTipoMov.isPresent()) {
            throw new BadRequestException("No se encuentra el tipo de movimiento con el id = " + movimiento.getTipoMovimiento().getIdTipoMov());
        }
        movimiento.setTipoMovimiento(optTipoMov.get());
        
        if (movimiento.getUbicacionOrigen() != null && movimiento.getUbicacionOrigen().getIdDireccion() != null) {
            Optional<Ubicacion> optUbicacionOrigen = ubicacionRepository.findById(movimiento.getUbicacionOrigen().getIdDireccion());
            if (optUbicacionOrigen.isPresent()) {
                movimiento.setUbicacionOrigen(optUbicacionOrigen.get());
            }
        }
        
        if (movimiento.getUbicacionDestino() != null && movimiento.getUbicacionDestino().getIdDireccion() != null) {
            Optional<Ubicacion> optUbicacionDestino = ubicacionRepository.findById(movimiento.getUbicacionDestino().getIdDireccion());
            if (optUbicacionDestino.isPresent()) {
                movimiento.setUbicacionDestino(optUbicacionDestino.get());
            }
        }
        
        movimiento.setFechaMovimiento(new Timestamp(System.currentTimeMillis()));
        
        MovimientoInventario movimientoGuardado = movimientoInventarioRepository.save(movimiento);
        
        // Actualizar stock según el tipo de movimiento (esto se puede hacer con un trigger en la BD también)
        String nombreTipo = movimiento.getTipoMovimiento().getNombreTipo();
        Inventario inventario = movimiento.getInventario();
        
        if (nombreTipo != null) {
            if (nombreTipo.equals("entrada") || nombreTipo.equals("compra") || nombreTipo.equals("ajuste_positivo")) {
                inventario.setStockActual(inventario.getStockActual() + movimiento.getCantidad());
            } else if (nombreTipo.equals("salida") || nombreTipo.equals("venta") || nombreTipo.equals("ajuste_negativo")) {
                if (inventario.getStockActual() < movimiento.getCantidad()) {
                    throw new BadRequestException("No hay suficiente stock. Stock actual: " + inventario.getStockActual() + ", cantidad solicitada: " + movimiento.getCantidad());
                }
                inventario.setStockActual(inventario.getStockActual() - movimiento.getCantidad());
            }
            inventarioRepository.save(inventario);
        }
        
        return movimientoGuardado;
    }
}

