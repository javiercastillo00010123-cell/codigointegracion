package dao;

import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario validarLogin(String correo, String password) {
        Usuario usuario = null;

        String sql = """
                SELECT u.id_usuario, u.id_rol, u.nombre, u.correo, u.password, u.estado, r.nombre_rol
                FROM usuario u
                INNER JOIN rol r ON u.id_rol = r.id_rol
                WHERE u.correo = ? AND u.password = ? AND u.estado = 'Activo'
                """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setIdRol(rs.getInt("id_rol"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setNombreRol(rs.getString("nombre_rol"));
            }

        } catch (SQLException e) {
            System.out.println("Error al validar login.");
            e.printStackTrace();
        }

        return usuario;
    }
}