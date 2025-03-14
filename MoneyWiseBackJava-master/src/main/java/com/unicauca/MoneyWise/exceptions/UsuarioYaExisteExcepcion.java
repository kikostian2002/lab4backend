package com.unicauca.MoneyWise.exceptions;

public class UsuarioYaExisteExcepcion extends RuntimeException {
    public UsuarioYaExisteExcepcion(String correo) {
        super("El usuario con correo " + correo + " ya existe");
    }
}
