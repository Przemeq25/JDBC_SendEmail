package sample;



import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Controller {


    @FXML
    private ComboBox comboBox;

    @FXML
    private Button showButton;

    @FXML
    private Button sendEmail;

    @FXML
    private TableView tableView;


    public void addMenuItems(ArrayList list) {

        for (int i = 0 ; i< list.size(); i++) {
            comboBox.getItems().add(list.get(i).toString());
        }
    }

    public void selectTable(){
        if (!comboBox.getSelectionModel().isEmpty()) {
            tableView.getItems().clear();
            tableView.getColumns().clear();
            ObservableList rowsData = DBMain.selectFromTables(tableView, comboBox.getValue().toString());
            for (Object rowsDatum : rowsData) {
                tableView.getItems().add(rowsDatum);
            }
            if(comboBox.getValue().toString().equals("email"))
            {
                sendEmail.setDisable(false);
            }
        }

    }
    public void sendEmailGUI(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("email.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Send Email");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            DBMain db = new DBMain();
            EmailController emailController = new EmailController();
            emailController.addUsersItems(db.emailUsers());
        } catch (Exception err) {
            err.printStackTrace();
        }
    }




}
