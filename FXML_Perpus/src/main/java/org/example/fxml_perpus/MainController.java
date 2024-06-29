package org.example.fxml_perpus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    private StackPane contentPane;

    @FXML
    public void initialize() {
        showBuku();
    }

    @FXML
    private void showBuku() {
        loadFXMLIntoPane("fxmlbuku.fxml");
    }

    @FXML
    private void showAnggota() {
        loadFXMLIntoPane("fxmlanggota.fxml");
    }

    private void loadFXMLIntoPane(String fxmlFile) {
        try {
            Node node = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentPane.getChildren().setAll(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
