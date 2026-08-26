package com.assignment.sirai.service.impl;

import com.assignment.sirai.dto.LoginRequestDTO;
import com.assignment.sirai.dto.RespuestaLogin;
import com.assignment.sirai.entity.Administrador;
import com.assignment.sirai.repository.AdministradorRepository;
import com.assignment.sirai.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiImpl implements AdministradorService {

    private final AdministradorRepository repository;

    public AdministradorServiImpl(AdministradorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Administrador guardar(Administrador administrador) {
        return repository.save(administrador);
    }

    @Override
    public Administrador buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Administrador> listar() {
        return repository.findAll();
    }

    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Administrador buscarPorUsuario(String usuario) {
        return repository.findByUsuario(usuario).orElse(null);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RespuestaLogin autenticar(LoginRequestDTO login) {
        Optional<Administrador> usuario = repository.findByUsuario(login.getUsuario());

        if (usuario.isEmpty()) {
            return new RespuestaLogin(false, "Usuario o contraseña incorrectos", null);
        }

        Administrador admin = usuario.get();

        // 1. Validar contraseña encriptada (BCrypt)
        if (!passwordEncoder.matches(login.getPassword(), admin.getPassword())) {
            return new RespuestaLogin(false, "Usuario o contraseña incorrectos", null);
        }

        // 2. Registrar la sesión asignando el rol "ROLE_ADMIN" directamente
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                admin.getUsuario(),
                null,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) // 👈 Rol fijo asignado directamente
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        // Persistir el contexto de seguridad en la respuesta HTTP
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        SecurityContextRepository repo = new HttpSessionSecurityContextRepository();
        repo.saveContext(context, attr.getRequest(), attr.getResponse());

        // 3. Respuesta de éxito
        String nombreAdmin = admin.getNombres();
        return new RespuestaLogin(true, "Inicio de sesión exitoso", nombreAdmin);
    }
}
