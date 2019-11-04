package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import java.util.ArrayList;


public class EmailController {

    @FXML
    private ComboBox emailComboBox;

    @FXML
    private TextField titleTextField;
    @FXML
    private HTMLEditor textHTMLEditor;



    public void addUsersItems(ArrayList list) {

        for (int i = 0 ; i< list.size(); i++) {
            emailComboBox.getItems().add(list.get(i).toString());
        }


    }


   @FXML
    public void sendEmail() {
        if(emailComboBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("No emails selected!");
            alert.showAndWait();
        }else{
            if(MailManager.sendMail(titleTextField.getText(),textHTMLEditor.getHtmlText(),emailComboBox.getValue().toString())){
                titleTextField.clear();
                textHTMLEditor.setHtmlText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("Mail sent!");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("ERROR");
                alert.showAndWait();
            }
        }
    }
}
