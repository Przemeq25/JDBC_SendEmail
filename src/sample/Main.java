package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("JDBC");
        primaryStage.setResizable(false);
        primaryStage.show();
        Controller myController = loader.getController();
        DBMain DBMain = new DBMain();
        myController.addMenuItems(DBMain.connectDatabase());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
