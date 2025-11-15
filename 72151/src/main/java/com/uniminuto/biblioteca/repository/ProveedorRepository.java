package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    
    @Query("SELECT p FROM Proveedor p WHERE p.activo = true")
    List<Proveedor> findProveedoresActivos();
}

