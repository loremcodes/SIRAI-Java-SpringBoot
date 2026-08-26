package com.assignment.sirai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitamos CSRF para permitir peticiones POST vía Fetch/JSON
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // 🔓 Rutas públicas (HTML de login, CSS, JS y el endpoint del Service)
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/evaluacion.html",
                                "/api/public/escuelas-profesionales",
                                "/api/public/estudiantes",
                                "/api/evaluacion/guardar",
                                "/agradecimiento.html",
                                "/login.html",
                                "/api/admin/login",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico"
                        ).permitAll()

                        // 🔒 Vistas protegidas (Solo accesibles si inició sesión)
                        .requestMatchers(
                                "/dashboard.html",
                                "/escuelas-profesionales.html",
                                "/historial-evaluaciones.html",
                                "/reportes.html",
                                "/usuarios.html"
                        ).hasRole("ADMIN") // O .authenticated() si solo evalúas sesión activa

                        // Cualquier otra ruta requiere estar autenticado
                        .anyRequest().authenticated()
                )

                // Redirección automática si intentan entrar directamente a una URL protegida por navegador
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendRedirect("/login.html");
                        })
                )

                // Configuración del Logout gestionado por Spring Security
                .logout(logout -> logout
                        .logoutUrl("/api/admin/logout")
                        .logoutSuccessUrl("/login.html")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}