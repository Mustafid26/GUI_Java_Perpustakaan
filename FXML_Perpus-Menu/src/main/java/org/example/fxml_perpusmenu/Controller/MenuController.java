package org.example.fxml_perpusmenu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
public class MenuController {
    @FXML
    private Button btnHome;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnBuku;
    @FXML
    private Button btnPustakawan;
    @FXML
    private Button btnAnggota;
    @FXML
    void home(ActionEvent event) {
        try {
            homee();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private Button btnselesai;
    @FXML
    void p1(ActionEvent event) {
    }
    @FXML
    void p2(ActionEvent event) {
    }
    @FXML
    void p3(ActionEvent event) {
    }
    @FXML
    private StackPane contentArea;
    @FXML
    private Label lbTeks;
    public void initialize(URL location,ResourceBundle resources) {
        try {
            homee();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void selesai() {
        System.exit(0);
    }
    @FXML
    public void homee() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/org/example/fxml_perpusmenu/fxml/fxmlhome.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    @FXML
    public void buku() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/org/example/fxml_perpusmenu/fxml/fxmlbuku.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    @FXML
    public void pustakawan() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/org/example/fxml_perpusmenu/fxml/fxmlpustakawan.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    @FXML
    public void anggota() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/org/example/fxml_perpusmenu/fxml/fxmlanggota.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void setUserInfo(String userName) {
        lbTeks.setText("Selamat Datang ....." + userName);
    }
}
