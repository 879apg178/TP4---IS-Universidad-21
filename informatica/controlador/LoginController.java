package informatica.controlador;

import informatica.modelo.Usuario;
import informatica.servicio.IUsuarioService;
import informatica.servicio.real.UsuarioServiceReal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color; // Para el mensaje de "olvidó contraseña"
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblError;

    private final IUsuarioService usuarioService;

    public LoginController() {

        this.usuarioService = new UsuarioServiceReal();
    }

    @FXML
    private void handleAccederButton() {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            lblError.setTextFill(Color.RED);
            lblError.setText("Por favor, ingrese usuario y contraseña.");
            return;
        }

        Usuario usuarioValidado = usuarioService.validarLogin(user, pass);

        if (usuarioValidado != null) {
            lblError.setTextFill(Color.GREEN); //  color de éxito
            lblError.setText("¡Bienvenido " + usuarioValidado.getNombreCompleto() + "!");
            cargarDashboard(usuarioValidado);
            cerrarVentanaActual();
        } else {
            lblError.setTextFill(Color.RED);
            lblError.setText("Usuario o contraseña incorrectos.");
        }
    }

    private void cargarDashboard(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/informatica/vista/DashboardView.fxml"));
            Parent root = loader.load();
            DashboardController dashboardController = loader.getController();

            // Le pasamos el usuario al Dashboard
            dashboardController.inicializarConUsuario(usuario);

            Stage stage = new Stage();
            stage.setTitle("Informatica Superior - Dashboard");
            stage.setScene(new Scene(root, 900, 600)); // más ancha
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            lblError.setTextFill(Color.RED);
            lblError.setText("Error al cargar el dashboard.");
        }
    }

    private void cerrarVentanaActual() {
        Stage stage = (Stage) txtUsuario.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleOlvidoPassword() {
        lblError.setTextFill(Color.BLUE);
        lblError.setText("Función 'Recuperar Contraseña' no implementada.");
    }
}