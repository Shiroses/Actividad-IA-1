package actividadIA.service;

import actividadIA.dto.request.ProductoRequestDTO;
import actividadIA.dto.response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoResponseDTO> listarActivos();
    ProductoResponseDTO obtenerPorId(Integer id);
    ProductoResponseDTO crear(ProductoRequestDTO requestDTO);
    ProductoResponseDTO actualizar(Integer id, ProductoRequestDTO requestDTO);
    void eliminarLogico(Integer id);
}