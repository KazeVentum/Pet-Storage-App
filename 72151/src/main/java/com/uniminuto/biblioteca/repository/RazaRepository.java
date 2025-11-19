package com.uniminuto.biblioteca.repository;

import com.uniminuto.biblioteca.entity.Raza;
import com.uniminuto.biblioteca.entity.RazaInventarioDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Integer> {
    
    @Query("SELECT r FROM Raza r WHERE r.activo = true")
    List<Raza> findRazasActivas();
    
    @Query("SELECT new com.uniminuto.biblioteca.entity.RazaInventarioDTO(r.nombreRaza, SUM(i.stockActual)) " +
           "FROM Inventario i JOIN i.producto p JOIN p.razas r " +
           "GROUP BY r.idRaza, r.nombreRaza " +
           "ORDER BY SUM(i.stockActual) DESC")
    List<RazaInventarioDTO> findTopRazasByStock(Pageable pageable);
}

