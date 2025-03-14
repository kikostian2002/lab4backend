package com.unicauca.MoneyWise.movimientos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {


    @Query("SELECT m FROM MovimientoEntity m WHERE m.usuario.correo = :correo")
    List<MovimientoEntity> findByUsuarioCorreo(@Param("correo") String correo);}
