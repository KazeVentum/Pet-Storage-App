package com.uniminuto.biblioteca.servicesimpl;

import com.uniminuto.biblioteca.entity.Usuario;
import com.uniminuto.biblioteca.repository.UsuarioRepository;
import com.uniminuto.biblioteca.services.UsuarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.regex.Pattern;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * @return Todos los usuarios registrados
     * @throws BadRequestException
     */
    @Override
    public List<Usuario> listarUsuarios() throws BadRequestException {
        return this.usuarioRepository.findAll();
    }

    /**
     * @param usuarioId
     * @return usuario por id
     * @throws BadRequestException
     */
    @Override
    public Usuario obtenerUsuarioId(Integer usuarioId) throws BadRequestException {
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(usuarioId);
        if (!optUsuario.isPresent()) {
            throw new BadRequestException("No se encuentra el Usuario con el id = "
                    + usuarioId);
    }
        return optUsuario.get();
    }

    /**
     * @param correo
     * @return Usuario por correo
     * @throws BadRequestException
     */
    @Override
    public Usuario obtenerUsuarioPorEmail(String correo) throws BadRequestException {
        // Cadena de caracteres para verificar estructura de email
        String regexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

        // Validación de estructura del correo
        if (!Pattern.matches(regexPattern, correo)) {
            throw new BadRequestException("El formato del correo electrónico no es válido");
        }

        // Buscar usuario por correo
        Usuario usuario = this.usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new BadRequestException("No se encuentra el Usuario con el correo = " + correo);
        }
        return usuario;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) throws BadRequestException {
        usuario.setPassword(hashPassword(usuario.getPassword()));
        usuario.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws BadRequestException {
        Optional<Usuario> optUsuario = usuarioRepository.findById(usuario.getIdUsuario());
        if (!optUsuario.isPresent()) {
            throw new BadRequestException("No se encuentra el Usuario con el id = " + usuario.getIdUsuario());
        }
        Usuario usuarioExistente = optUsuario.get();
        // Mantener la contraseña si no se envía una nueva
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            usuario.setPassword(usuarioExistente.getPassword());
        } else {
            usuario.setPassword(hashPassword(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
