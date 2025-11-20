package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.Producto;
import com.uniminuto.biblioteca.entity.ProductoMasSalidasDTO;
import com.uniminuto.biblioteca.entity.ProductoFiltradoDTO; // New import
import com.uniminuto.biblioteca.entity.Raza; // New import for TamanoRaza
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

    @Query("SELECT new com.uniminuto.biblioteca.entity.ProductoMasSalidasDTO(p.nombreProducto, SUM(mi.cantidad)) " +
           "FROM MovimientoInventario mi " +
           "JOIN mi.inventario i " +
           "JOIN i.producto p " +
           "JOIN mi.tipoMovimiento tm " +
           "WHERE tm.nombreTipo = 'salida' AND mi.fechaMovimiento >= :fechaInicio " +
           "GROUP BY p.nombreProducto " +
           "ORDER BY " +
           "CASE WHEN :orderDirection = 'ASC' THEN SUM(mi.cantidad) END ASC, " +
           "CASE WHEN :orderDirection = 'DESC' THEN SUM(mi.cantidad) END DESC")
    List<ProductoMasSalidasDTO> findProductosMasSalidas(@Param("fechaInicio") Date fechaInicio, @Param("orderDirection") String orderDirection, Pageable pageable);

    @Query("SELECT new com.uniminuto.biblioteca.entity.ProductoFiltradoDTO(" +
           "p.nombreProducto, " +
           "p.precioVenta, " +
           "p.pesoKg) " +
           "FROM Producto p " +
           "JOIN p.marca m " +
           "JOIN p.razas r " + // Join via ManyToMany relationship
           "WHERE m.nombreMarca = :nombreMarca AND r.tamano = :tamanoRaza " +
           "GROUP BY p.idProducto, p.nombreProducto, p.precioVenta, p.pesoKg") // Group by all non-aggregated selected fields
    List<ProductoFiltradoDTO> filtrarProductosByMarcaAndTamanoRaza(@Param("nombreMarca") String nombreMarca, @Param("tamanoRaza") Raza.TamanoRaza tamanoRaza);
}

