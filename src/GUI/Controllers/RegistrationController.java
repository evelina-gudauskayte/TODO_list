package GUI.Controllers;

import BL.Managers.UserManager;
import BL.Managers.UserManagerImplementation;
import DAL.RealUserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    public void initialize(){

    }
    @FXML
    public void handleRegistrationButton(ActionEvent actionEvent) throws IOException {
        UserManager userManager = new UserManagerImplementation(new RealUserDAO());
        if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && passwordField.getText().equals(secondPasswordField.getText())) {
            userManager.addNewUser(usernameField.getText(), passwordField.getText());
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/authorization.fxml")));
        stage.setScene(scene);
    }

    @FXML
    public void handleCancelButton(ActionEvent actionEvent) throws IOException { //TODO check password stuff 
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/authorization.fxml")));
        stage.setScene(scene);
    }

}
