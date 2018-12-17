package GUI.Controllers;

import BL.Managers.*;
import BL.Note;
import DAL.RealNoteDAO;
import DAL.RealUserDAO;
import Util.BadContextException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainSceneController {
    @FXML
    public Button logOutButton;
    @FXML
    public Button settingsButton;
    @FXML
    public Label usernameLabel;
    @FXML
    public TextArea textArea;
    @FXML
    public Button addButton;
    @FXML
    public Label dateLabel;
    @FXML
    public ListView<Note> listView;

    @FXML
    public void initialize(){
        try {
            initList();
            initLabel();
        } catch (BadContextException e) {
            e.printStackTrace();
        }
    }

    private void initList() throws BadContextException {
        //UserManager userManager = new UserManagerImplementation(new RealUserDAO());
        NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
        ArrayList<Note> notes = noteManager.getAllNotesOfUser();
        for (Note note: notes){
            System.out.println(note);
        }
        listView.getItems().addAll(notes);
    }

    private void initLabel(){
        usernameLabel.setText(Context.getInstance().getCurrentUser().getUserName());
    }

    @FXML
    public void handleLogOutButton(ActionEvent actionEvent) throws IOException {
        UserManager userManager = new UserManagerImplementation(new RealUserDAO());
        Context.getInstance().logOut();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/authorization.fxml")));
        stage.setScene(scene);
    }

}
