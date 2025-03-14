package com.unicauca.MoneyWise;

import com.unicauca.MoneyWise.usuarios.UserDetailsImp;
import com.unicauca.MoneyWise.usuarios.UsuarioEntity;
import com.unicauca.MoneyWise.usuarios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@AllArgsConstructor
public class AppConfig {
    private final UsuarioRepository usuarioRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {
            Optional<UsuarioEntity> user = usuarioRepository.findByCorreo(username);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("User not found");
            }
            return new UserDetailsImp(user.get());
        };
    }}