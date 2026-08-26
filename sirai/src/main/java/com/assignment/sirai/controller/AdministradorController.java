package com.assignment.sirai.controller;

import com.assignment.sirai.dto.LoginRequestDTO;
import com.assignment.sirai.dto.RespuestaLogin;
import com.assignment.sirai.entity.Administrador;
import com.assignment.sirai.entity.EscuelaProfesional;
import com.assignment.sirai.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Administrador> listar() {
        return administradorService.listar();
    }

    @GetMapping("/{id}")
    public Administrador editar(@PathVariable Long id) {
        return administradorService.buscarPorId(id);
    }

    @PostMapping("/usuario/nuevo")
    public Administrador crearUsuario(@RequestBody Administrador usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return administradorService.guardar(usuario);
    }

    @PutMapping("/usuario/editar/{id}")
    public Administrador editarUsuario(@PathVariable Long id, @RequestBody Administrador datosNuevos) {

        Administrador usuarioExistente = administradorService.buscarPorId(id);

        // 2. Validar si existe
        if (usuarioExistente == null) {
            throw new RuntimeException("Usuario no encontrado con el ID: " + id);
        }

        // 3. Actualizar los campos
        usuarioExistente.setNombres(datosNuevos.getNombres());
        usuarioExistente.setUsuario(datosNuevos.getUsuario());

        if (datosNuevos.getPassword() != null && !datosNuevos.getPassword().isBlank()) {
            usuarioExistente.setPassword(passwordEncoder.encode(datosNuevos.getPassword()));
        }

        // 4. Guardar a través del servicio
        return administradorService.guardar(usuarioExistente);
    }

    @DeleteMapping("/usuario/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        administradorService.eliminarUsuario(id);
    }

    @PostMapping("/login")
    public RespuestaLogin login(@RequestBody LoginRequestDTO request) {
        return administradorService.autenticar(request);
    }
}
