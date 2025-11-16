
package informatica.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {

    // 1. Datos de tu BD
    private static final String URL = "jdbc:mysql://localhost:3306/informatica_superior";
    private static final String USER = "root"; // Usuario de tu MySQL


    private static final String PASSWORD = "";

    /**
     * Establece y devuelve una conexión a la base de datos.
     */
    public static Connection getConexion() throws SQLException {
        try {
            // Registramos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de MySQL.");
            e.printStackTrace();
            throw new SQLException("Driver JDBC no encontrado", e);
        }

        // Establecemos y devolvemos la conexión
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
