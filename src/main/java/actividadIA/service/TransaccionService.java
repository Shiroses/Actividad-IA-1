package actividadIA.service;

import actividadIA.dto.response.TransaccionResponseDTO;

import java.util.List;

public interface TransaccionService {
    List<TransaccionResponseDTO> listarTodas();
    TransaccionResponseDTO obtenerPorId(Integer id);
}