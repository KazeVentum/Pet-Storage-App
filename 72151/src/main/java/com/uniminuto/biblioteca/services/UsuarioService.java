package com.uniminuto.biblioteca.services;

import java.util.List;
import com.uniminuto.biblioteca.entity.Usuario;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

/**
 *
 * @author KazeVentum
 */

public interface UsuarioService {

    List<Usuario> listarUsuarios() throws BadRequestException;

    Usuario obtenerUsuarioId(Integer usuarioId) throws BadRequestException;

    Usuario obtenerUsuarioPorEmail(String correo) throws BadRequestException;

    Usuario guardarUsuario(Usuario usuario) throws BadRequestException;

    Usuario actualizarUsuario(Usuario usuario) throws BadRequestException;
}
