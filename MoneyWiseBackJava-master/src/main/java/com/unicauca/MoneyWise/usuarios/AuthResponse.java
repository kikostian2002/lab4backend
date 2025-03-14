package com.unicauca.MoneyWise.usuarios;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private String token;
}
