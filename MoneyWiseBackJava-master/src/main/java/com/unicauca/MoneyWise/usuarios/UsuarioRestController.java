package com.unicauca.MoneyWise.usuarios;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioRestController {
    private UsuarioService service;

 @PostMapping("/save")
    public void save(@RequestBody Usuario usuario) {
        service.save(usuario);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {

       return service.login(loginRequest);
    }
    @GetMapping
    public Usuario getUsuario(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);

        return service.getUsuario(token);
    }
    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new IllegalArgumentException("Token JWT no válido o ausente");
        }
    }

}

