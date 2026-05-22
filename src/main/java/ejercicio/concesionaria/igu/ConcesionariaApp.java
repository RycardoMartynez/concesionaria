package ejercicio.concesionaria.igu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConcesionariaApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                ConcesionariaApp.class.getResource("concesionaria-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 1280, 820);

        stage.setTitle("Concesionaria");
        stage.setScene(scene);
        stage.show();
    }
}
