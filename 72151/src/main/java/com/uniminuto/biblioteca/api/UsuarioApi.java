package com.uniminuto.biblioteca.api;


import com.uniminuto.biblioteca.entity.Usuario;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 *
 * @author KazeVentum
 */

@RequestMapping("/usuario")
public interface UsuarioApi {

    /**
     * Metodo para listar los usuarios registrados en BD.
     *
     * @return Lista de usuarios.
     * @throws BadRequestException excepcion.
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Usuario>> listarUsuarios()
            throws BadRequestException;

    /**
     * Metodo para listar un usuario por id registrado.
     *
     * @param usuarioId Id del usuario.
     * @return usuario buscado.
     * @throws BadRequestException excepcion.
     */
    @RequestMapping(value = "/usuarioId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Usuario> obtenerUsuarioPorId(@RequestParam Integer usuarioId)
            throws BadRequestException;


    /**
     * Metodo para listar un usuario por id registrado.
     *
     * @param correo correo del usuario.
     * @return usuario buscado.
     * @throws BadRequestException excepcion.
     */
    @RequestMapping(value = "/correo",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Usuario> obtenerUsuarioPorEmail(@RequestParam String correo)
            throws BadRequestException;

    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) throws BadRequestException;

    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario) throws BadRequestException;
}

