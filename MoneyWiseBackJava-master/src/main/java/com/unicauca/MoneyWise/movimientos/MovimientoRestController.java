package com.unicauca.MoneyWise.movimientos;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class MovimientoRestController {

    private final MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> getMovimientos(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);

        return movimientoService.getMovimientos(token);
    }

    @PostMapping
    public ResponseEntity<?> saveMovimiento(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Movimiento movimiento) {
        String token = extractTokenFromHeader(authorizationHeader);
        movimientoService.saveMovimiento(movimiento, token);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovimiento(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long id) {
        String token = extractTokenFromHeader(authorizationHeader);

        movimientoService.deleteMovimiento(id, token);
        return ResponseEntity.ok().build();
    }



    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new IllegalArgumentException("Token JWT no válido o ausente");
        }
    }
}
