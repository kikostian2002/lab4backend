package com.unicauca.MoneyWise.usuarios;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

   @Mapping(target = "id", ignore = true)
    @Mapping(target = "estadoDeCuenta", source = "estadoDeCuenta")
    UsuarioEntity aEntidad(Usuario usuario);
    Usuario aUsuario(UsuarioEntity entity);


}
