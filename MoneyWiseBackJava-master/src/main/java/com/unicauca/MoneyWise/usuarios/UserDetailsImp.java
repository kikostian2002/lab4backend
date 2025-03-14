package com.unicauca.MoneyWise.usuarios;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImp implements UserDetails {
    private transient UsuarioEntity usuarioEntity;

    public UserDetailsImp(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(usuarioEntity.getRol()));
    }

    @Override
    public String getPassword() {
        return usuarioEntity.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuarioEntity.getCorreo();
    }
}
