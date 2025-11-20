package com.uniminuto.biblioteca.apicontroller;

import com.uniminuto.biblioteca.api.DetallePedidoApi;
import com.uniminuto.biblioteca.entity.DetallePedidoProductoDTO;
import com.uniminuto.biblioteca.services.DetallePedidoService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetallePedidoApiController implements DetallePedidoApi {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Override
    public ResponseEntity<List<DetallePedidoProductoDTO>> listarDetallesPorPedido(Integer idPedido) throws BadRequestException {
        return ResponseEntity.ok(detallePedidoService.listarDetallesPorPedido(idPedido));
    }
}
