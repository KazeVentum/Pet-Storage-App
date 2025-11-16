package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.Inventario;
import com.uniminuto.biblioteca.entity.InventarioResumenDTO;
import com.uniminuto.biblioteca.entity.ProductoBajoStockDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    @Query("SELECT i FROM Inventario i WHERE i.ubicacion.idDireccion = :idUbicacion")
    List<Inventario> findByUbicacion(@Param("idUbicacion") Integer idUbicacion);

    @Query("SELECT i FROM Inventario i WHERE i.stockActual <= i.stockMinimo")
    List<Inventario> findProductosBajoStock();

    @Query("SELECT new com.uniminuto.biblioteca.entity.ProductoBajoStockDTO(p.nombreProducto, i.stockActual, i.stockMinimo, m.nombreMarca) " +
           "FROM Inventario i JOIN i.producto p JOIN p.marca m " +
           "WHERE i.stockActual <= i.stockMinimo " +
           "AND (:ubicacion IS NULL OR i.ubicacion.nombreUbicacion = :ubicacion)")
    List<ProductoBajoStockDTO> findProductosConBajoStock(@Param("ubicacion") String ubicacion, Pageable pageable);

    @Query(value = "SELECT u.nombre_ubicacion as nombreUbicacion, " +
            "SUM(i.stock_actual) AS totalBultos, " +
            "SUM(i.stock_actual * p.precio_compra) AS valorInventario " +
            "FROM inventario AS i " +
            "JOIN productos AS p ON i.id_producto = p.id_producto " +
            "JOIN ubicaciones AS u ON i.id_direccion = u.id_direccion " +
            "WHERE (:ubicacion IS NULL OR u.nombre_ubicacion = :ubicacion) " +
            "GROUP BY u.nombre_ubicacion " +
            "ORDER BY :orderByClause " +
            "LIMIT :limit",
            nativeQuery = true)
    List<Object[]> resumenInventario(
            @Param("ubicacion") String ubicacion,
            @Param("orderByClause") String orderByClause,
            @Param("limit") Integer limit
    );}
