// paquete: informatica.servicio
package informatica.servicio;

import informatica.modelo.Usuario; // <-- Importa del nuevo paquete

public interface IUsuarioService {

    /**
     * Valida las credenciales de un usuario.
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return El objeto Usuario si es válido, o null si no lo es.
     */
    Usuario validarLogin(String username, String password);
}