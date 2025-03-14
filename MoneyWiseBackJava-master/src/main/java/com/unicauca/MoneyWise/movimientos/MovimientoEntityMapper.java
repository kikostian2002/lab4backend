package com.unicauca.MoneyWise.movimientos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MovimientoEntityMapper {

   @Mapping(target = "usuario", ignore = true)
    MovimientoEntity toEntity(Movimiento movimiento);



    Movimiento toDomain(MovimientoEntity movimientoEntity);

    List<Movimiento> toDomainList(List<MovimientoEntity> movimientosEntity);
}
