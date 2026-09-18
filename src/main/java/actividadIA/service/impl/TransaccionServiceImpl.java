package actividadIA.service.impl;

import actividadIA.dto.response.TransaccionResponseDTO;
import actividadIA.mapper.TransaccionMapper;
import actividadIA.modelo.Transaccion;
import actividadIA.repository.TransaccionRepository;
import actividadIA.service.TransaccionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;

    @Override
    public List<TransaccionResponseDTO> listarTodas() {
        return transaccionRepository.findAll().stream()
                .map(TransaccionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransaccionResponseDTO obtenerPorId(Integer id) {
        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transacción no encontrada"));
        return TransaccionMapper.toResponseDTO(transaccion);
    }
}