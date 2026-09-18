package actividadIA.dto.response;

import lombok.Data;

@Data
public class ProductoResponseDTO {
    private Integer idProducto;
    private Integer stock;
    private Boolean activo;
}
