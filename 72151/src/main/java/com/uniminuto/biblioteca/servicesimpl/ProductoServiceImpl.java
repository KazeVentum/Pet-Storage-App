package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.Marca;
import com.uniminuto.biblioteca.entity.Producto;
import com.uniminuto.biblioteca.entity.ProductoMasSalidasDTO;
import com.uniminuto.biblioteca.entity.ProductoFiltradoDTO; // New import
import com.uniminuto.biblioteca.entity.Raza; // Keep this import for the entity
import com.uniminuto.biblioteca.entity.Raza.TamanoRaza; // New specific import for the enum
import com.uniminuto.biblioteca.repository.MarcaRepository;
import com.uniminuto.biblioteca.repository.ProductoRepository;
import com.uniminuto.biblioteca.repository.RazaRepository;
import com.uniminuto.biblioteca.services.ProductoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private RazaRepository razaRepository;

    @Override
    public List<Producto> listarProductos() throws BadRequestException {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> listarProductosActivos() throws BadRequestException {
        return productoRepository.findProductosActivos();
    }

    @Override
    public Producto obtenerProducto(Integer idProducto) throws BadRequestException {
        Optional<Producto> optProducto = productoRepository.findById(idProducto);
        if (!optProducto.isPresent()) {
            throw new BadRequestException("No se encuentra el producto con el id = " + idProducto);
        }
        return optProducto.get();
    }

    @Override
    @Transactional
    public Producto guardarProducto(Producto producto) throws BadRequestException {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
            throw new BadRequestException("El nombre del producto es obligatorio");
        }
        if (producto.getMarca() == null || producto.getMarca().getIdMarca() == null) {
            throw new BadRequestException("La marca es obligatoria");
        }
        Optional<Marca> optMarca = marcaRepository.findById(producto.getMarca().getIdMarca());
        if (!optMarca.isPresent()) {
            throw new BadRequestException("No se encuentra la marca con el id = " + producto.getMarca().getIdMarca());
        }
        producto.setMarca(optMarca.get());

        if (producto.getRazas() != null && !producto.getRazas().isEmpty()) {
            Set<Raza> razasValidadas = new HashSet<>();
            for (Raza raza : producto.getRazas()) {
                if (raza.getIdRaza() != null) {
                    Optional<Raza> optRaza = razaRepository.findById(raza.getIdRaza());
                    if (optRaza.isPresent()) {
                        razasValidadas.add(optRaza.get());
                    }
                }
            }
            producto.setRazas(razasValidadas);
        }

        if (producto.getEstado() == null) {
            producto.setEstado(Producto.EstadoProducto.activo);
        }

        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Producto producto) throws BadRequestException {
        if (producto.getIdProducto() == null) {
            throw new BadRequestException("El ID del producto es obligatorio para actualizar");
        }
        Optional<Producto> optProducto = productoRepository.findById(producto.getIdProducto());
        if (!optProducto.isPresent()) {
            throw new BadRequestException("No se encuentra el producto con el id = " + producto.getIdProducto());
        }

        Producto productoExistente = optProducto.get();
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecioVenta(producto.getPrecioVenta());
        productoExistente.setPrecioCompra(producto.getPrecioCompra());
        productoExistente.setEstado(producto.getEstado());
        productoExistente.setPesoKg(producto.getPesoKg());

        if (producto.getMarca() != null && producto.getMarca().getIdMarca() != null) {
            Optional<Marca> optMarca = marcaRepository.findById(producto.getMarca().getIdMarca());
            if (optMarca.isPresent()) {
                productoExistente.setMarca(optMarca.get());
            }
        }

        if (producto.getRazas() != null) {
            Set<Raza> razasValidadas = new HashSet<>();
            for (Raza raza : producto.getRazas()) {
                if (raza.getIdRaza() != null) {
                    Optional<Raza> optRaza = razaRepository.findById(raza.getIdRaza());
                    if (optRaza.isPresent()) {
                        razasValidadas.add(optRaza.get());
                    }
                }
            }
            productoExistente.setRazas(razasValidadas);
        }

        return productoRepository.save(productoExistente);
    }

    @Override
    public void eliminarProducto(Integer idProducto) throws BadRequestException {
        if (!productoRepository.existsById(idProducto)) {
            throw new BadRequestException("No se encuentra el producto con el id = " + idProducto);
        }
        productoRepository.deleteById(idProducto);
    }

    @Override
    public List<ProductoMasSalidasDTO> findProductosMasSalidas(int dias, int limit, String order) throws BadRequestException {
        if (dias <= 0) {
            throw new BadRequestException("El número de días debe ser mayor que cero.");
        }
        if (limit <= 0) {
            throw new BadRequestException("El límite debe ser mayor que cero.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -dias);
        Date fechaInicio = calendar.getTime();

        String orderDirectionString = "asc".equalsIgnoreCase(order) ? "ASC" : "DESC";
        Pageable pageable = PageRequest.of(0, limit); // No sorting here, as it's handled in the query

        return productoRepository.findProductosMasSalidas(fechaInicio, orderDirectionString, pageable);
    }

    @Override
    public List<ProductoFiltradoDTO> filtrarProductosByMarcaAndTamanoRaza(String nombreMarca, String tamanoRaza) throws BadRequestException {
        if (nombreMarca == null || nombreMarca.trim().isEmpty()) {
            throw new BadRequestException("El nombre de la marca es obligatorio para filtrar productos.");
        }
        if (tamanoRaza == null || tamanoRaza.trim().isEmpty()) {
            throw new BadRequestException("El tamaño de la raza es obligatorio para filtrar productos.");
        }

        try {
            TamanoRaza tamanoRazaEnum = TamanoRaza.valueOf(tamanoRaza.toLowerCase()); // Convert string to enum
            return productoRepository.filtrarProductosByMarcaAndTamanoRaza(nombreMarca, tamanoRazaEnum);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Tamaño de raza inválido: " + tamanoRaza + ". Valores permitidos: pequenio, mediano, grande.");
        }
    }
}
