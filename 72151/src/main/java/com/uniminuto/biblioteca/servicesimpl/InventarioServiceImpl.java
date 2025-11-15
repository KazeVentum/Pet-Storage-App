package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.Inventario;
import com.uniminuto.biblioteca.entity.Producto;
import com.uniminuto.biblioteca.entity.ProductoBajoStockDTO;
import com.uniminuto.biblioteca.entity.Ubicacion;
import com.uniminuto.biblioteca.repository.InventarioRepository;
import com.uniminuto.biblioteca.repository.ProductoRepository;
import com.uniminuto.biblioteca.repository.UbicacionRepository;
import com.uniminuto.biblioteca.services.InventarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Override
    public List<Inventario> listarInventarios() throws BadRequestException {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario obtenerInventario(Integer idInventario) throws BadRequestException {
        Optional<Inventario> optInventario = inventarioRepository.findById(idInventario);
        if (!optInventario.isPresent()) {
            throw new BadRequestException("No se encuentra el inventario con el id = " + idInventario);
        }
        return optInventario.get();
    }

    @Override
    public List<Inventario> listarInventariosPorUbicacion(Integer idUbicacion) throws BadRequestException {
        return inventarioRepository.findByUbicacion(idUbicacion);
    }

    @Override
    public List<Inventario> listarProductosBajoStock() throws BadRequestException {
        return inventarioRepository.findProductosBajoStock();
    }

    @Override
    public Inventario guardarInventario(Inventario inventario) throws BadRequestException {
        if (inventario.getProducto() == null || inventario.getProducto().getIdProducto() == null) {
            throw new BadRequestException("El producto es obligatorio");
        }
        if (inventario.getUbicacion() == null || inventario.getUbicacion().getIdDireccion() == null) {
            throw new BadRequestException("La ubicación es obligatoria");
        }

        Optional<Producto> optProducto = productoRepository.findById(inventario.getProducto().getIdProducto());
        if (!optProducto.isPresent()) {
            throw new BadRequestException("No se encuentra el producto con el id = " + inventario.getProducto().getIdProducto());
        }
        inventario.setProducto(optProducto.get());

        Optional<Ubicacion> optUbicacion = ubicacionRepository.findById(inventario.getUbicacion().getIdDireccion());
        if (!optUbicacion.isPresent()) {
            throw new BadRequestException("No se encuentra la ubicación con el id = " + inventario.getUbicacion().getIdDireccion());
        }
        inventario.setUbicacion(optUbicacion.get());

        if (inventario.getStockMinimo() == null) {
            inventario.setStockMinimo(10);
        }
        if (inventario.getStockMaximo() == null) {
            inventario.setStockMaximo(1000);
        }
        if (inventario.getStockActual() == null) {
            inventario.setStockActual(0);
        }

        return inventarioRepository.save(inventario);
    }

    @Override
    public Inventario actualizarInventario(Inventario inventario) throws BadRequestException {
        if (inventario.getIdInventario() == null) {
            throw new BadRequestException("El ID del inventario es obligatorio para actualizar");
        }
        Optional<Inventario> optInventario = inventarioRepository.findById(inventario.getIdInventario());
        if (!optInventario.isPresent()) {
            throw new BadRequestException("No se encuentra el inventario con el id = " + inventario.getIdInventario());
        }

        Inventario inventarioExistente = optInventario.get();
        inventarioExistente.setStockMinimo(inventario.getStockMinimo());
        inventarioExistente.setStockMaximo(inventario.getStockMaximo());
        inventarioExistente.setStockActual(inventario.getStockActual());

        if (inventario.getProducto() != null && inventario.getProducto().getIdProducto() != null) {
            Optional<Producto> optProducto = productoRepository.findById(inventario.getProducto().getIdProducto());
            if (optProducto.isPresent()) {
                inventarioExistente.setProducto(optProducto.get());
            }
        }

        if (inventario.getUbicacion() != null && inventario.getUbicacion().getIdDireccion() != null) {
            Optional<Ubicacion> optUbicacion = ubicacionRepository.findById(inventario.getUbicacion().getIdDireccion());
            if (optUbicacion.isPresent()) {
                inventarioExistente.setUbicacion(optUbicacion.get());
            }
        }

        return inventarioRepository.save(inventarioExistente);
    }

    @Override
    public List<ProductoBajoStockDTO> findBajoStock(String ubicacion, String sortBy, String order, int limit) {
        // Validar sortBy para evitar JPQL Injection
        List<String> allowedSortBy = Arrays.asList("stockActual", "nombreProducto");
        String sortField = allowedSortBy.contains(sortBy) ? sortBy : "stockActual";

        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(0, limit, Sort.by(direction, sortField));

        return inventarioRepository.findProductosConBajoStock(ubicacion, pageable);
    }
}
