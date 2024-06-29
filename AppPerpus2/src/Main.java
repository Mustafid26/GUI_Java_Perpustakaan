import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {
    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Connection conn = DBConnection.getConn();
        stage.show();
    }
}
