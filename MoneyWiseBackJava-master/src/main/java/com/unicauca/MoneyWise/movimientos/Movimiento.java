package com.unicauca.MoneyWise.movimientos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movimiento {
    private Long id;
    private String descripcion;
    private String fecha;
    private String categoria;
    private double monto;
    private String tipoMovimiento;
}


