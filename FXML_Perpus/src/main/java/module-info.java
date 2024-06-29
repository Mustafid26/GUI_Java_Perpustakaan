module org.example.fxml_perpus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.fxml_perpus to javafx.fxml;
    exports org.example.fxml_perpus;
}