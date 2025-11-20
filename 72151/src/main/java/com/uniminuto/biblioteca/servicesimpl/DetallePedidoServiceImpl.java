package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.DetallePedidoProductoDTO;
import com.uniminuto.biblioteca.repository.DetallePedidoRepository;
import com.uniminuto.biblioteca.services.DetallePedidoService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public List<DetallePedidoProductoDTO> listarDetallesPorPedido(Integer idPedido) throws BadRequestException {
        if (idPedido == null) {
            throw new BadRequestException("El ID del pedido es obligatorio para listar los detalles.");
        }
        List<DetallePedidoProductoDTO> detalles = detallePedidoRepository.findDetallesByPedidoId(idPedido);
        if (detalles.isEmpty()) {
            // Optionally throw an exception or return an empty list if no details are found
            // For now, returning an empty list seems appropriate.
        }
        return detalles;
    }
}
