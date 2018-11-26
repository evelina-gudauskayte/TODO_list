package GUI;

import BL.Managers.Session;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void handleConfirmButton(javafx.event.ActionEvent actionEvent) throws IOException {
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());
        Session session = new Session(usernameField.getText(), passwordField.getText());
        if(session.isAuthorized()){ // TODO create new stage with notes
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("frame.fxml")));
            stage.setScene(scene);
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
