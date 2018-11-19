package GUI;

import BL.Managers.Session;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AuthorizationController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label error;

    @FXML
    public void handleConfirmButton(javafx.event.ActionEvent actionEvent){
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());
        Session session = new Session(usernameField.getText(), passwordField.getText());
        if(session.isAuthorized()){ // TODO create new stage with notes
            /*Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Congrads!");
            alert.setContentText("You did it wow!");
            alert.show();*/
        }else {
            passwordField.clear();
            error.setVisible(true);
            //pane.getChildren().add(new Label("Wrong username or password"));
        }
    }
    @FXML
    public void handleCancelButton(ActionEvent actionEvent) {
        try {
            Platform.exit();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
