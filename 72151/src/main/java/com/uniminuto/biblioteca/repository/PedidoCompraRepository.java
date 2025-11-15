package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.PedidoCompra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Integer> {
    
    @Query("SELECT p FROM PedidoCompra p WHERE p.estado = :estado")
    List<PedidoCompra> findByEstado(@Param("estado") PedidoCompra.EstadoPedido estado);
}

