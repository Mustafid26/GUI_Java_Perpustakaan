module org.example.fxml_perpustakaan {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fxml_perpustakaan to javafx.fxml;
    exports org.example.fxml_perpustakaan;
}