package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.Raza;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Integer> {
    
    @Query("SELECT r FROM Raza r WHERE r.activo = true")
    List<Raza> findRazasActivas();
}

