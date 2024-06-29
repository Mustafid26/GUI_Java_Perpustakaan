module com.example.fxmldemo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.fxmldemo1 to javafx.fxml;
    exports com.example.fxmldemo1;
}