package actividadIA.mapper;

import actividadIA.dto.request.ProductoRequestDTO;
import actividadIA.dto.response.ProductoResponseDTO;
import actividadIA.modelo.Producto;

public class ProductoMapper {

    public static Producto toEntity(ProductoRequestDTO dto) {
        if (dto == null) return null;
        Producto producto = new Producto();
        producto.setStock(dto.getStock());
        producto.setActivo(true);
        return producto;
    }

    public static ProductoResponseDTO toResponseDTO(Producto producto) {
        if (producto == null) return null;
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setStock(producto.getStock());
        dto.setActivo(producto.getActivo());
        return dto;
    }

    public static void updateEntity(Producto producto, ProductoRequestDTO dto) {
        if (dto != null && producto != null) {
            producto.setStock(dto.getStock());
        }
    }
}