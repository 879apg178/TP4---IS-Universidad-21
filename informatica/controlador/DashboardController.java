
package informatica.controlador;

import informatica.modelo.Curso;
import informatica.modelo.Usuario;
import informatica.servicio.ICursoService;
import informatica.servicio.real.CursoServiceReal;

// JavaFX
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Java
import java.io.IOException;
import java.util.List;

public class DashboardController {

    @FXML
    private Label lblBienvenida;
    @FXML
    private VBox vboxCursos;
    @FXML
    private Button btnNotificaciones;
    @FXML
    private MenuButton menuPerfil;

    private final ICursoService cursoService;
    private Usuario usuarioActual; // El Dashboard ya tenía al usuario

    public DashboardController() {

        // Ahora usamos la implementación REAL que va a MySQL
        this.cursoService = new CursoServiceReal();
    }

    public void inicializarConUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        lblBienvenida.setText("Vista general de cursos de: " + usuario.getNombreCompleto());
        String iniciales = getIniciales(usuario.getNombreCompleto());
        menuPerfil.setText("👤 " + iniciales + " ▿");
        cargarCursos();
    }

    private void cargarCursos() {
        if (usuarioActual == null) return;


        List<Curso> cursos = cursoService.getCursosPorUsuario(usuarioActual.getIdUsuario());

        vboxCursos.getChildren().clear();
        if (cursos.isEmpty()) {
            vboxCursos.getChildren().add(new Label("No tienes cursos inscritos."));
            return;
        }
        for (Curso curso : cursos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/informatica/vista/CursoCardView.fxml"));
                Node cardNode = loader.load();
                CursoCardController cardController = loader.getController();
                cardController.setData(curso);
                cardNode.setOnMouseClicked(event -> {
                    abrirDetalleCurso(curso);
                });
                vboxCursos.getChildren().add(cardNode);
            } catch (IOException e) {
                System.err.println("Error al cargar la tarjeta del curso: " + curso.getNombre());
                e.printStackTrace();
            }
        }
    }

    private void abrirDetalleCurso(Curso curso) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/informatica/vista/CursoDetalleView.fxml"));
            Parent root = loader.load();
            CursoDetalleController detalleController = loader.getController();


            // Ahora el Curso Y el Usuario
            detalleController.inicializarDatos(curso, this.usuarioActual);

            Stage stage = new Stage();
            stage.setTitle("Detalle de: " + curso.getNombre());
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error al abrir la ventana de detalle del curso.");
            e.printStackTrace();
        }
    }



    @FXML
    private void handleNotificaciones() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notificaciones");
        alert.setHeaderText(null);
        alert.setContentText("No tienes notificaciones nuevas.");
        alert.showAndWait();
    }

    @FXML
    private void handleVerPerfil() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mi Perfil");
        alert.setHeaderText(usuarioActual.getNombreCompleto());
        alert.setContentText("Usuario: " + usuarioActual.getUsername() + "\nID: " + usuarioActual.getIdUsuario());
        alert.showAndWait();
    }

    @FXML
    private void handleCerrarSesion() {
        Stage stageActual = (Stage) menuPerfil.getScene().getWindow();
        stageActual.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/informatica/vista/LoginView.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setTitle("Informatica Superior - Login");
            loginStage.setScene(new Scene(root, 350, 400));
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getIniciales(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.isEmpty()) return "??";
        String[] partes = nombreCompleto.split(" ");
        String iniciales = "";
        if (partes.length > 0) iniciales += partes[0].charAt(0);
        if (partes.length > 1) iniciales += partes[partes.length - 1].charAt(0);
        return iniciales.toUpperCase();
    }
}