package com.unicauca.MoneyWise.movimientos;

import com.unicauca.MoneyWise.usuarios.UsuarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
public class MovimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String fecha;
    private String categoria;
    private double monto;
    private String tipoMovimiento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

}
