package dinus.kaisa.pbo.demofxml1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Selamat Datang di JavaFX");
    }
    @FXML
    protected void onCancelButtonClick() {
        welcomeText.setText("Anda Menekan Cancel");
    }
}