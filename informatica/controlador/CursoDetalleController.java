package informatica.controlador;

import informatica.modelo.Actividad;
import informatica.modelo.Curso;
import informatica.modelo.Usuario;
import informatica.servicio.ICursoService;
import informatica.servicio.real.CursoServiceReal;

// --- IMPORTS DE JAVAFX (¡AQUÍ ESTÁ LA CORRECCIÓN!) ---
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;      // <--- Este es para la fila
import javafx.scene.layout.VBox;      // <--- ¡ESTE ES EL QUE FALTA!
import javafx.geometry.Pos;
// --- FIN IMPORTS JAVAFX ---

import java.util.List;

public class CursoDetalleController {

    @FXML
    private Label lblNombreCurso;
    @FXML
    private VBox vboxActividades;

    private final ICursoService cursoService;
    private Curso cursoActual;
    private Usuario usuarioActual;

    public CursoDetalleController() {
        this.cursoService = new CursoServiceReal();
    }

    public void inicializarDatos(Curso curso, Usuario usuario) {
        this.cursoActual = curso;
        this.usuarioActual = usuario;

        lblNombreCurso.setText(curso.getNombre());
        cargarActividades();
    }


    private void cargarActividades() {
        if (cursoActual == null || usuarioActual == null) return;

        List<Actividad> actividades = cursoService.getActividadesPorCurso(
                cursoActual.getIdCurso(),
                usuarioActual.getIdUsuario()
        );

        vboxActividades.getChildren().clear();

        if (actividades.isEmpty()) {
            vboxActividades.getChildren().add(new Label("Este curso no tiene actividades."));
            return;
        }

        // Recorremos la lista y creamos la fila
        for (Actividad act : actividades) {

            // 1. Creamos el CheckBox (sin texto)
            CheckBox actCheckBox = new CheckBox();
            if (act.isCompletado()) {
                actCheckBox.setSelected(true);
            }

            // 2. Creamos el Hyperlink (con el nombre)
            Hyperlink actLink = new Hyperlink(act.getNombre());
            actLink.setStyle("-fx-font-size: 14px;");

            // 3. Creamos la fila horizontal (HBox)
            HBox activityRow = new HBox(10); // 10px de espaciado
            activityRow.setAlignment(Pos.CENTER_LEFT);
            activityRow.getChildren().addAll(actCheckBox, actLink); // Añadimos ambos

            // --- Lógica de Eventos ---

            // Evento para el CheckBox: Actualiza la BD
            actCheckBox.setOnAction(event -> {
                boolean nuevoEstado = actCheckBox.isSelected();
                cursoService.marcarActividad(
                        usuarioActual.getIdUsuario(),
                        act.getIdActividad(),
                        nuevoEstado
                );
            });

            // Evento para el Hyperlink: Abre el contenido (simulado)
            actLink.setOnAction(event -> {
                abrirContenido(act);
            });

            // 4. Añadimos la fila completa (HBox) al VBox
            vboxActividades.getChildren().add(activityRow);
        }
    }


    private void abrirContenido(Actividad actividad) {
        Alert popup = new Alert(Alert.AlertType.INFORMATION);
        popup.setTitle("Visor de Contenido");
        popup.setHeaderText("Abriendo: " + actividad.getNombre());

        // Mostramos un texto diferente según el tipo de actividad
        switch (actividad.getTipo()) {
            case "seccion":
                popup.setContentText("Este es el inicio de la " + actividad.getNombre() + ".");
                break;
            case "actividad":
                popup.setContentText("Cargando las instrucciones para la " + actividad.getNombre() + "...");
                break;
            case "video":
                popup.setContentText("Reproduciendo 'video instalador'...\n(Simulación de reproductor de video)");
                break;
            case "recurso":
                popup.setContentText("Descargando el recurso 'Arquitectura FTTH'...\n(Simulación de descarga)");
                break;
            default:
                popup.setContentText("Cargando contenido...");
        }

        popup.showAndWait();
    }
}