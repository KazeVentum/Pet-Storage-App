package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.DetallePedidoProductoDTO;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/detalle-pedido")
public interface DetallePedidoApi {

    @RequestMapping(value = "/listar-por-pedido/{idPedido}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<DetallePedidoProductoDTO>> listarDetallesPorPedido(@PathVariable("idPedido") Integer idPedido) throws BadRequestException;
}
