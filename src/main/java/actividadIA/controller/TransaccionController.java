package actividadIA.controller;

import actividadIA.dto.response.TransaccionResponseDTO;
import actividadIA.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@RequiredArgsConstructor
@Tag(name = "Transacciones", description = "Operaciones de solo lectura para transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    @GetMapping
    @Operation(summary = "Listar todas las transacciones (Read-Only)")
    public ResponseEntity<List<TransaccionResponseDTO>> listarTodas() {
        return ResponseEntity.ok(transaccionService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una transacción por ID (Read-Only)")
    public ResponseEntity<TransaccionResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(transaccionService.obtenerPorId(id));
    }
}