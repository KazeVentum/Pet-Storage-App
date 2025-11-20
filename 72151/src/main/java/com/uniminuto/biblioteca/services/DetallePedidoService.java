package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.DetallePedidoProductoDTO;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface DetallePedidoService {
    List<DetallePedidoProductoDTO> listarDetallesPorPedido(Integer idPedido) throws BadRequestException;
}
