
package informatica.servicio.real;

import informatica.dao.UsuarioDAO; // ¡NUEVO IMPORT!
import informatica.modelo.Usuario;
import informatica.servicio.IUsuarioService;

/**
 * Implementación REAL del servicio de autenticación.
 * CONECTADO A MYSQL.
 * Esta clase llama al UsuarioDAO.
 */
public class UsuarioServiceReal implements IUsuarioService {

    // El Servicio ahora "usa" un DAO
    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceReal() {
        // Creación de objetos: El Servicio crea su DAO
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Valida el login llamando al DAO.
     * Esta clase no contiene SQL, solo llama al "obrero" (DAO).
     */
    @Override
    public Usuario validarLogin(String username, String password) {
        try {
            // Llama al método del DAO que SÍ tiene el SQL
            return usuarioDAO.validarLogin(username, password);
        } catch (Exception e) {
            System.err.println("Error en UsuarioServiceReal: " + e.getMessage());
            e.printStackTrace();
            return null; // Devuelve null si hay un error
        }
    }
}