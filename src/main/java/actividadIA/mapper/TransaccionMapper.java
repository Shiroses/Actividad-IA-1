package actividadIA.mapper;

import actividadIA.dto.response.TransaccionResponseDTO;
import actividadIA.modelo.Transaccion;

public class TransaccionMapper {

    public static TransaccionResponseDTO toResponseDTO(Transaccion transaccion) {
        if (transaccion == null) return null;
        TransaccionResponseDTO dto = new TransaccionResponseDTO();
        dto.setIdTransaccion(transaccion.getIdTransaccion());
        dto.setCantidad(transaccion.getCantidad());
        dto.setFecha(transaccion.getFecha());
        dto.setProducto(ProductoMapper.toResponseDTO(transaccion.getProducto()));
        return dto;
    }
}