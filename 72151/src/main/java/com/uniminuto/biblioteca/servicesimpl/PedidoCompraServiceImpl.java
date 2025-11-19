package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.DetallePedido;
import com.uniminuto.biblioteca.entity.PedidoCompra;
import com.uniminuto.biblioteca.entity.PedidoCompraDetalleDTO; // New import
import com.uniminuto.biblioteca.entity.Proveedor;
import com.uniminuto.biblioteca.repository.PedidoCompraRepository;
import com.uniminuto.biblioteca.repository.ProveedorRepository;
import com.uniminuto.biblioteca.services.PedidoCompraService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoCompraServiceImpl implements PedidoCompraService {
    
    @Autowired
    private PedidoCompraRepository pedidoCompraRepository;
    
    @Autowired
    private ProveedorRepository proveedorRepository;
    
    @Override
    public List<PedidoCompra> listarPedidos() throws BadRequestException {
        return pedidoCompraRepository.findAll();
    }
    
    @Override
    public PedidoCompra obtenerPedido(Integer idPedido) throws BadRequestException {
        Optional<PedidoCompra> optPedido = pedidoCompraRepository.findById(idPedido);
        if (!optPedido.isPresent()) {
            throw new BadRequestException("No se encuentra el pedido con el id = " + idPedido);
        }
        return optPedido.get();
    }
    
    @Override
    public List<PedidoCompra> listarPedidosPorEstado(String estado) throws BadRequestException {
        try {
            PedidoCompra.EstadoPedido estadoEnum = PedidoCompra.EstadoPedido.valueOf(estado.toLowerCase()); // Added toLowerCase for robustness
            return pedidoCompraRepository.findByEstado(estadoEnum);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Estado inválido: " + estado);
        }
    }

    @Override
    public List<PedidoCompraDetalleDTO> listarPedidosDetallePorEstado(String estado) throws BadRequestException {
        try {
            PedidoCompra.EstadoPedido estadoEnum = PedidoCompra.EstadoPedido.valueOf(estado.toLowerCase()); // Added toLowerCase for robustness
            return pedidoCompraRepository.findDetalleByEstado(estadoEnum);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Estado inválido para el detalle de pedidos: " + estado);
        }
    }
    
    @Override
    @Transactional
    public PedidoCompra guardarPedido(PedidoCompra pedido) throws BadRequestException {
        if (pedido.getProveedor() == null || pedido.getProveedor().getIdProveedor() == null) {
            throw new BadRequestException("El proveedor es obligatorio");
        }
        Optional<Proveedor> optProveedor = proveedorRepository.findById(pedido.getProveedor().getIdProveedor());
        if (!optProveedor.isPresent()) {
            throw new BadRequestException("No se encuentra el proveedor con el id = " + pedido.getProveedor().getIdProveedor());
        }
        pedido.setProveedor(optProveedor.get());
        
        if (pedido.getEstado() == null) {
            pedido.setEstado(PedidoCompra.EstadoPedido.pendiente);
        }
        
        if (pedido.getDetalles() != null && !pedido.getDetalles().isEmpty()) {
            for (DetallePedido detalle : pedido.getDetalles()) {
                detalle.setPedido(pedido);
                if (detalle.getSubtotal() == null && detalle.getCantidadRecibida() != null && detalle.getPrecioUnitario() != null) {
                    detalle.setSubtotal(detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidadRecibida())));
                }
            }
        }
        
        PedidoCompra pedidoGuardado = pedidoCompraRepository.save(pedido);
        
        // Calcular total
        BigDecimal total = BigDecimal.ZERO;
        if (pedidoGuardado.getDetalles() != null) {
            for (DetallePedido detalle : pedidoGuardado.getDetalles()) {
                if (detalle.getSubtotal() != null) {
                    total = total.add(detalle.getSubtotal());
                }
            }
        }
        pedidoGuardado.setTotal(total);
        
        return pedidoCompraRepository.save(pedidoGuardado);
    }
    
    @Override
    @Transactional
    public PedidoCompra actualizarPedido(PedidoCompra pedido) throws BadRequestException {
        if (pedido.getIdPedido() == null) {
            throw new BadRequestException("El ID del pedido es obligatorio para actualizar");
        }
        Optional<PedidoCompra> optPedido = pedidoCompraRepository.findById(pedido.getIdPedido());
        if (!optPedido.isPresent()) {
            throw new BadRequestException("No se encuentra el pedido con el id = " + pedido.getIdPedido());
        }
        
        PedidoCompra pedidoExistente = optPedido.get();
        pedidoExistente.setEstado(pedido.getEstado());
        pedidoExistente.setFecha(pedido.getFecha());
        pedidoExistente.setFechaPedido(pedido.getFechaPedido());
        
        if (pedido.getProveedor() != null && pedido.getProveedor().getIdProveedor() != null) {
            Optional<Proveedor> optProveedor = proveedorRepository.findById(pedido.getProveedor().getIdProveedor());
            if (optProveedor.isPresent()) {
                pedidoExistente.setProveedor(optProveedor.get());
            }
        }
        
        if (pedido.getDetalles() != null) {
            pedidoExistente.getDetalles().clear();
            for (DetallePedido detalle : pedido.getDetalles()) {
                detalle.setPedido(pedidoExistente);
                if (detalle.getSubtotal() == null && detalle.getCantidadRecibida() != null && detalle.getPrecioUnitario() != null) {
                    detalle.setSubtotal(detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidadRecibida())));
                }
                pedidoExistente.getDetalles().add(detalle);
            }
        }
        
        PedidoCompra pedidoActualizado = pedidoCompraRepository.save(pedidoExistente);
        
        // Recalcular total
        BigDecimal total = BigDecimal.ZERO;
        if (pedidoActualizado.getDetalles() != null) {
            for (DetallePedido detalle : pedidoActualizado.getDetalles()) {
                if (detalle.getSubtotal() != null) {
                    total = total.add(detalle.getSubtotal());
                }
            }
        }
        pedidoActualizado.setTotal(total);
        
        return pedidoCompraRepository.save(pedidoActualizado);
    }
    
    @Override
    public PedidoCompra cambiarEstadoPedido(Integer idPedido, String estado) throws BadRequestException {
        Optional<PedidoCompra> optPedido = pedidoCompraRepository.findById(idPedido);
        if (!optPedido.isPresent()) {
            throw new BadRequestException("No se encuentra el pedido con el id = " + idPedido);
        }
        try {
            PedidoCompra.EstadoPedido estadoEnum = PedidoCompra.EstadoPedido.valueOf(estado);
            PedidoCompra pedido = optPedido.get();
            pedido.setEstado(estadoEnum);
            return pedidoCompraRepository.save(pedido);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Estado inválido: " + estado);
        }
    }
}

