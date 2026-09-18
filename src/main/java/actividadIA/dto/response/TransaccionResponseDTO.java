package actividadIA.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransaccionResponseDTO {
    private Integer idTransaccion;
    private ProductoResponseDTO producto;
    private Integer cantidad;
    private LocalDateTime fecha;
}