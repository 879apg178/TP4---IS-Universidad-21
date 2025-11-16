// paquete: informatica.servicio.mock
package informatica.servicio.mock;

// Importa de los nuevos paquetes
import informatica.modelo.Usuario;
import informatica.servicio.IUsuarioService;

// Implementamos la interfaz del contrato
public class UsuarioServiceMock implements IUsuarioService {

    @Override
    public Usuario validarLogin(String username, String password) {
        // Simulamos el login de tu captura de pantalla
        if (username.equals("alumno33") && password.equals("1234")) {
            // Devolvemos un usuario de prueba. "AP" por la esquina de la captura :)
            return new Usuario(1, "alumno33", "Ariel Pérez");
        }

        // Si el login falla
        return null;
    }
}
