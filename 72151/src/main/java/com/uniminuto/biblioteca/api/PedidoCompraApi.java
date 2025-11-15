package com.uniminuto.biblioteca.api;

import com.uniminuto.biblioteca.entity.PedidoCompra;
import java.util.List;
import java.util.Map;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/pedido-compra")
public interface PedidoCompraApi {
    
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<PedidoCompra>> listarPedidos() throws BadRequestException;
    
    @RequestMapping(value = "/obtener/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<PedidoCompra> obtenerPedido(@PathVariable("id") Integer id) throws BadRequestException;
    
    @RequestMapping(value = "/listar-por-estado/{estado}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<PedidoCompra>> listarPedidosPorEstado(@PathVariable("estado") String estado) throws BadRequestException;
    
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<PedidoCompra> guardarPedido(@RequestBody PedidoCompra pedido) throws BadRequestException;
    
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<PedidoCompra> actualizarPedido(@RequestBody PedidoCompra pedido) throws BadRequestException;
    
    @RequestMapping(value = "/cambiar-estado",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<PedidoCompra> cambiarEstadoPedido(@RequestBody Map<String, Object> request) throws BadRequestException;
}

