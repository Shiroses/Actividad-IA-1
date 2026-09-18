package actividadIA.service.impl;

import actividadIA.dto.request.ProductoRequestDTO;
import actividadIA.dto.response.ProductoResponseDTO;
import actividadIA.mapper.ProductoMapper;
import actividadIA.modelo.Producto;
import actividadIA.repository.ProductoRepository;
import actividadIA.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDTO> listarActivos() {
        return productoRepository.findByActivoTrue().stream()
                .map(ProductoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponseDTO obtenerPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return ProductoMapper.toResponseDTO(producto);
    }

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO requestDTO) {
        Producto producto = ProductoMapper.toEntity(requestDTO);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDTO(guardado);
    }

    @Override
    public ProductoResponseDTO actualizar(Integer id, ProductoRequestDTO requestDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        ProductoMapper.updateEntity(producto, requestDTO);
        Producto actualizado = productoRepository.save(producto);
        return ProductoMapper.toResponseDTO(actualizado);
    }

    @Override
    public void eliminarLogico(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        producto.setActivo(false); // Borrado lógico
        productoRepository.save(producto);
    }
}