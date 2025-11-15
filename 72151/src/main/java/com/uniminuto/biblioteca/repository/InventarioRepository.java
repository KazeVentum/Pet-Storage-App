package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.Inventario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    
    @Query("SELECT i FROM Inventario i WHERE i.ubicacion.idDireccion = :idUbicacion")
    List<Inventario> findByUbicacion(@Param("idUbicacion") Integer idUbicacion);
    
    @Query("SELECT i FROM Inventario i WHERE i.stockActual <= i.stockMinimo")
    List<Inventario> findProductosBajoStock();
}

