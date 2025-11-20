package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.DetallePedido;
import com.uniminuto.biblioteca.entity.DetallePedidoProductoDTO; // New import
import java.util.List; // New import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    @Query("SELECT new com.uniminuto.biblioteca.entity.DetallePedidoProductoDTO(" +
           "dp.idDetalle, " +
           "dp.pedido.idPedido, " +
           "dp.cantidadRecibida, " +
           "dp.cantidadSolicitada, " +
           "dp.precioUnitario, " +
           "dp.subtotal, " +
           "p.idProducto, " +
           "p.nombreProducto, " +
           "p.descripcion) " +
           "FROM DetallePedido dp " +
           "JOIN dp.producto p " +
           "WHERE dp.pedido.idPedido = :idPedido")
    List<DetallePedidoProductoDTO> findDetallesByPedidoId(@Param("idPedido") Integer idPedido);
}

