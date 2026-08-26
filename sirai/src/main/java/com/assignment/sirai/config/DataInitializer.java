package com.assignment.sirai.config;

import com.assignment.sirai.entity.Administrador;
import com.assignment.sirai.entity.EscuelaProfesional;
import com.assignment.sirai.repository.AdministradorRepository;
import com.assignment.sirai.repository.EscuelaProfesionalRepository;
import com.assignment.sirai.repository.EscuelaProfesionalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(
            AdministradorRepository adminRepo,
            EscuelaProfesionalRepository escuelasRepo,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (adminRepo.count() == 0) {
                Administrador admin = new Administrador();
                admin.setNombres("Administrador Default");
                admin.setUsuario("admin");

                admin.setPassword(passwordEncoder.encode("admin123"));

                adminRepo.save(admin);
            }
            if (escuelasRepo.count() == 0) {
                List<String> nombresEscuelas = List.of(
                        "Contabilidad",
                        "Administración",
                        "Derecho",
                        "Ingeniería de Sistemas"
                );

                for (String nombre : nombresEscuelas) {
                    EscuelaProfesional ep = new EscuelaProfesional();
                    ep.setNombre(nombre);
                    escuelasRepo.save(ep);
                }
            }
        };
    }
}