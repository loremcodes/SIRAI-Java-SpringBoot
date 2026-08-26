package com.assignment.sirai.service;

import com.assignment.sirai.dto.LoginRequestDTO;
import com.assignment.sirai.dto.RespuestaLogin;
import com.assignment.sirai.entity.Administrador;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdministradorService {
    Administrador guardar(Administrador administrador);
    Administrador buscarPorId(Long id);
    List<Administrador> listar();
    void eliminarUsuario(Long id);
    Administrador buscarPorUsuario(String usuario);

    RespuestaLogin autenticar(LoginRequestDTO request);

}
