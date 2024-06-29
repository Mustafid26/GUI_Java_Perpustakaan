package org.example.fxml_perpusmenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/fxml_perpusmenu/fxml/fxmlmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistem Perpustakaan");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
