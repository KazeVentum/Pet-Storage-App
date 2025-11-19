package com.uniminuto.biblioteca.services;

import com.uniminuto.biblioteca.entity.PedidoCompra;
import com.uniminuto.biblioteca.entity.PedidoCompraDetalleDTO; // New import
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface PedidoCompraService {
    List<PedidoCompra> listarPedidos() throws BadRequestException;
    PedidoCompra obtenerPedido(Integer idPedido) throws BadRequestException;
    List<PedidoCompra> listarPedidosPorEstado(String estado) throws BadRequestException;
    PedidoCompra guardarPedido(PedidoCompra pedido) throws BadRequestException;
    PedidoCompra actualizarPedido(PedidoCompra pedido) throws BadRequestException;
    PedidoCompra cambiarEstadoPedido(Integer idPedido, String estado) throws BadRequestException;
    List<PedidoCompraDetalleDTO> listarPedidosDetallePorEstado(String estado) throws BadRequestException;
}

