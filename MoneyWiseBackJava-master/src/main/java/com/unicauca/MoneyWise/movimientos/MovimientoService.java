package com.unicauca.MoneyWise.movimientos;

import com.unicauca.MoneyWise.JwtService;
import com.unicauca.MoneyWise.usuarios.UsuarioEntity;
import com.unicauca.MoneyWise.usuarios.UsuarioRepository;
import com.unicauca.MoneyWise.usuarios.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoEntityMapper movimientoEntityMapper;
    private final UsuarioRepository usuarioRepository;
   private final JwtService jwtService;
    public List<Movimiento> getMovimientos(String token) {

        String username = getUsernameFromToken(token);
        return movimientoRepository.findByUsuarioCorreo(username).stream()
                .map(movimientoEntityMapper::toDomain)
                .toList();

    }
    public void saveMovimiento(Movimiento movimiento, String token) {
        String correo = getUsernameFromToken(token);
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        MovimientoEntity movimientoEntity = movimientoEntityMapper.toEntity(movimiento);

        Optional<MovimientoEntity> movimientoDataBase = movimientoRepository.findById(movimiento.getId());

        if (movimientoDataBase.isPresent()) {
            MovimientoEntity movimientoExistente = movimientoDataBase.get();


            if (movimientoExistente.getTipoMovimiento().equals("Ingreso")) {
                usuarioEntity.setEstadoDeCuenta(usuarioEntity.getEstadoDeCuenta() - movimientoExistente.getMonto());
            } else {
                usuarioEntity.setEstadoDeCuenta(usuarioEntity.getEstadoDeCuenta() + movimientoExistente.getMonto());
            }


            if (movimientoEntity.getTipoMovimiento().equals("Ingreso")) {
                usuarioEntity.setEstadoDeCuenta(usuarioEntity.getEstadoDeCuenta() + movimientoEntity.getMonto());
            } else {
                usuarioEntity.setEstadoDeCuenta(usuarioEntity.getEstadoDeCuenta() - movimientoEntity.getMonto());
            }
        } else {

            if (movimientoEntity.getTipoMovimiento().equals("Ingreso")) {
                usuarioEntity.setEstadoDeCuenta(usuarioEntity.getEstadoDeCuenta() + movimientoEntity.getMonto());
            } else {
                usuarioEntity.setEstadoDeCuenta(usuarioEntity.getEstadoDeCuenta() - movimientoEntity.getMonto());
            }
        }


        movimientoEntity.setUsuario(usuarioEntity);
        usuarioRepository.save(usuarioEntity);
        movimientoRepository.save(movimientoEntity);
    }

    public void deleteMovimiento(Long id, String token) {
        String correo = getUsernameFromToken(token);
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        MovimientoEntity movimientoEntity = movimientoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento no encontrado"));
        if(movimientoEntity.getTipoMovimiento().equals("Ingreso")){
            usuarioEntity.setEstadoDeCuenta(usuarioEntity.getEstadoDeCuenta() - movimientoEntity.getMonto());
        }
        if(movimientoEntity.getTipoMovimiento().equals("Egreso")) {
            usuarioEntity.setEstadoDeCuenta(usuarioEntity.getEstadoDeCuenta() + movimientoEntity.getMonto());
        }
        usuarioRepository.save(usuarioEntity);
        movimientoRepository.deleteById(id);
    }

    public String getUsernameFromToken(String token) {
        return jwtService.getUsernameFromToken(token);
    }
}
