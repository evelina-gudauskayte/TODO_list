package GUI.Controllers;

import BL.Managers.Context;
import BL.Managers.UserManager;
import BL.Managers.UserManagerImplementation;
import DAL.RealUserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button registrationButton;
    @FXML
    public PasswordField secondPasswordField;

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleRegistrationButton(ActionEvent actionEvent) throws IOException {
        UserManager userManager = new UserManagerImplementation(new RealUserDAO());
        if (!usernameField.getText().isEmpty()
                && !passwordField.getText().isEmpty()
                && passwordField.getText().equals(secondPasswordField.getText())
                && !usernameField.getText().contains(" ")
                && !usernameField.getText().contains("#")) {
            userManager.addNewUser(usernameField.getText(), passwordField.getText());
            userManager.authorizeUser(usernameField.getText(), passwordField.getText());
            if(Context.getInstance().isAuthorized()){
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/mainScene.fxml")));
                stage.setScene(scene);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong input");
            alert.setContentText("Wrong username or different passwords, try again.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleCancelButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/authorization.fxml")));
        stage.setScene(scene);
    }

}
