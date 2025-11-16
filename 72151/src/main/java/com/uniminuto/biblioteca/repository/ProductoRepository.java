package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.Producto;
import com.uniminuto.biblioteca.entity.ProductoMasSalidasDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.estado = 'activo'")
    List<Producto> findProductosActivos();

    @Query("SELECT new com.uniminuto.biblioteca.entity.ProductoMasSalidasDTO(p.nombreProducto, SUM(mi.cantidadBultoa)) " +
           "FROM MovimientoInventario mi " +
           "JOIN mi.inventario i " +
           "JOIN i.producto p " +
           "JOIN mi.tipoMovimiento tm " +
           "WHERE tm.nombreTipo = 'salida' AND mi.fechaMovimiento >= :fechaInicio " +
           "GROUP BY p.nombreProducto " +
           "ORDER BY " +
           "CASE WHEN :orderDirection = 'ASC' THEN SUM(mi.cantidadBultoa) END ASC, " +
           "CASE WHEN :orderDirection = 'DESC' THEN SUM(mi.cantidadBultoa) END DESC")
    List<ProductoMasSalidasDTO> findProductosMasSalidas(@Param("fechaInicio") Date fechaInicio, @Param("orderDirection") String orderDirection, Pageable pageable);
}

