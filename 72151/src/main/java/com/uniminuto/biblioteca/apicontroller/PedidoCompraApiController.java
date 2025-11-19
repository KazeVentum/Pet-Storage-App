package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.PedidoCompraApi;
import com.uniminuto.biblioteca.entity.PedidoCompra;
import com.uniminuto.biblioteca.entity.PedidoCompraDetalleDTO; // New import
import com.uniminuto.biblioteca.services.PedidoCompraService;
import java.util.List;
import java.util.Map;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoCompraApiController implements PedidoCompraApi {
    
    @Autowired
    private PedidoCompraService pedidoCompraService;
    
    @Override
    public ResponseEntity<List<PedidoCompra>> listarPedidos() throws BadRequestException {
        return ResponseEntity.ok(pedidoCompraService.listarPedidos());
    }
    
    @Override
    public ResponseEntity<PedidoCompra> obtenerPedido(Integer id) throws BadRequestException {
        return ResponseEntity.ok(pedidoCompraService.obtenerPedido(id));
    }
    
    @Override
    public ResponseEntity<List<PedidoCompra>> listarPedidosPorEstado(String estado) throws BadRequestException {
        return ResponseEntity.ok(pedidoCompraService.listarPedidosPorEstado(estado));
    }

    @Override
    public ResponseEntity<List<PedidoCompraDetalleDTO>> listarPedidosDetallePorEstado(String estado) throws BadRequestException {
        return ResponseEntity.ok(pedidoCompraService.listarPedidosDetallePorEstado(estado));
    }
    
    @Override
    public ResponseEntity<PedidoCompra> guardarPedido(PedidoCompra pedido) throws BadRequestException {
        return ResponseEntity.ok(pedidoCompraService.guardarPedido(pedido));
    }
    
    @Override
    public ResponseEntity<PedidoCompra> actualizarPedido(PedidoCompra pedido) throws BadRequestException {
        return ResponseEntity.ok(pedidoCompraService.actualizarPedido(pedido));
    }
    
    @Override
    public ResponseEntity<PedidoCompra> cambiarEstadoPedido(Map<String, Object> request) throws BadRequestException {
        Integer idPedido = (Integer) request.get("id_pedido");
        String estado = (String) request.get("estado");
        if (idPedido == null || estado == null) {
            throw new BadRequestException("id_pedido y estado son obligatorios");
        }
        return ResponseEntity.ok(pedidoCompraService.cambiarEstadoPedido(idPedido, estado));
    }
}

