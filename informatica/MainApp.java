
package informatica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carga la vista de Login desde la carpeta 'vista'
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/informatica/vista/LoginView.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("IS - Login");
            primaryStage.setScene(new Scene(root, 350, 400)); //tamaño
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar LoginView.fxml:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}