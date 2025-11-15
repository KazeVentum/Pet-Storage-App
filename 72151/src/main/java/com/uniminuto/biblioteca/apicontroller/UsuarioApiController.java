package com.uniminuto.biblioteca.apicontroller;


import com.uniminuto.biblioteca.api.UsuarioApi;
import com.uniminuto.biblioteca.entity.Usuario;
import com.uniminuto.biblioteca.services.UsuarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author KazeVentum
 */
@RestController
public class UsuarioApiController implements UsuarioApi {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public ResponseEntity<List<Usuario>> listarUsuarios()
            throws BadRequestException {
        return ResponseEntity.ok(this.usuarioService.listarUsuarios());
    }

    @Override
    public ResponseEntity<Usuario> obtenerUsuarioPorId(Integer usuarioId) throws BadRequestException {
        return ResponseEntity.ok(this.usuarioService.obtenerUsuarioId(usuarioId));
    }

    /**
     * Metodo para listar un usuario por email registrado.
     *
     * @param correo correo del usuario.
     * @return usuario buscado.
     * @throws BadRequestException excepcion.
     */
    @Override
    public ResponseEntity<Usuario> obtenerUsuarioPorEmail(String correo) throws BadRequestException {
        return ResponseEntity.ok(this.usuarioService.obtenerUsuarioPorEmail(correo));
    }

    @Override
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) throws BadRequestException {
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
    }

    @Override
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario) throws BadRequestException {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuario));
    }
}
