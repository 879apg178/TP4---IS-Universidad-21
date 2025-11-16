package informatica.dao;

import informatica.db.ConexionDB;
import informatica.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para la entidad Usuario.
 * Contiene todo el SQL para la tabla 'usuarios'.
 * Este código implementa el diseño del TP2.
 */
public class UsuarioDAO {


    public Usuario validarLogin(String username, String password) {

        // Consulta SQL
        String sql = "SELECT id_usuario, username, nombre_completo " +
                "FROM usuarios " +
                "WHERE username = ? AND password_hash = ?";

        // Try-with-resources para asegurar que la conexión se cierre
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username); // Reemplaza el primer '?'
            ps.setString(2, password); // Reemplaza el segundo '?'


            try (ResultSet rs = ps.executeQuery()) {

                // Si la consulta devuelve una fila, el login es correcto
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String nombreCompleto = rs.getString("nombre_completo");

                    // Creamos y devolvemos el objeto Usuario con los datos de la BD
                    return new Usuario(idUsuario, username, nombreCompleto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar el login: " + e.getMessage());
            e.printStackTrace();
        }

        // Si no se encontró el usuario o hubo un error, devuelve null
        return null;
    }
}