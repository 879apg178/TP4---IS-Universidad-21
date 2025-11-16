
package informatica.controlador;

import informatica.modelo.Curso;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class CursoCardController {

    @FXML
    private Label lblNombreCurso;

    @FXML
    private ProgressBar pbProgreso;

    /**
     * Este método público será llamado por el DashboardController
     * para pasarle los datos del curso específico que esta tarjeta
     * debe mostrar.
     */
    public void setData(Curso curso) {
        lblNombreCurso.setText(curso.getNombre());

        // La barra de progreso funciona de 0.0 (0%) a 1.0 (100%).
        // Necesitamos convertir el 49 (de 49%) a 0.49.
        // Cambiar más adelante
        double progreso = (double) curso.getProgreso() / 100.0;
        pbProgreso.setProgress(progreso);
    }
}