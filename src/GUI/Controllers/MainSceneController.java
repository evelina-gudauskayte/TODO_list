package GUI.Controllers;

import BL.Managers.*;
import BL.Note;
import DAL.RealNoteDAO;
import DAL.RealUserDAO;
import Util.BadContextException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainSceneController {
    private Note prevSelected = null;
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
    public MenuItem deleteNoteMenuItem;
    @FXML
    public MenuItem chandeStatusMenyItem;

    @FXML
    public void initialize() {
        try {
            initList();
            initLabel();
        } catch (BadContextException e) {
            e.printStackTrace();
        }
    }

    private void initList() throws BadContextException {
        NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
        ArrayList<Note> notes = noteManager.getAllNotesOfUser();
        notes.sort(Note::compareTo);
        Collections.reverse(notes);

        listView.getItems().clear();
        listView.getItems().addAll(notes);
        listView.setCellFactory(param -> new ListCell<Note>() {
            @Override
            protected void updateItem(Note note, boolean empty) {
                super.updateItem(note, empty);
                if (empty || note == null) {
                    //setText("");
                } else {
                    setText(note.toString());
                    if (note.isDone()) {
                        setStyle("-fx-control-inner-background:derive(palegreen, 50%) ");
                    } else {
                        setStyle("-fx-control-inner-background:derive(palevioletred, 50%) ");
                    }
                }
            }
        });
    }

    private void initLabel() {
        usernameLabel.setText(Context.getInstance().getCurrentUser().getUserName());
    }

    private void saveNoteContent(Note note) {
        if (note != null && !note.getContent().equals(textArea.getText())) {
            try {
                note.setContent(textArea.getText());
                NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
                noteManager.update(note);
            } catch (BadContextException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleLogOutButton(ActionEvent actionEvent) throws IOException {
        saveNoteContent(listView.getSelectionModel().getSelectedItem());
        Context.getInstance().logOut();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/authorization.fxml")));
        stage.setScene(scene);
    }

    @FXML
    public void changeNote(MouseEvent event) {
        saveNoteContent(prevSelected);
        if (event.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem()!=null) {
            Note selectedNote = listView.getSelectionModel().getSelectedItem();
            textArea.setText(selectedNote.getContent());
            prevSelected = listView.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    public void handleAddNoteButton(ActionEvent actionEvent) throws IOException {
        saveNoteContent(listView.getSelectionModel().getSelectedItem());
        Parent root = FXMLLoader.load(getClass().getResource("/newNote.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setOnHidden((event) -> {
            try {
                initList();
            } catch (BadContextException e) {
                e.printStackTrace();
            }
        });
        stage.setTitle("Note Creator");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void handleRefreshButton(ActionEvent actionEvent) {
        saveNoteContent(listView.getSelectionModel().getSelectedItem());
        try {
            initList();
        } catch (BadContextException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDelete() {
        try {
            NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
            noteManager.deleteNote(listView.getSelectionModel().getSelectedItem());
            initList();
        } catch (BadContextException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleHandleChangeStatus(ActionEvent event) {
        try {
            NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
            Note note = listView.getSelectionModel().getSelectedItem();
            note.changeStatus();
            noteManager.update(note);
            initList();
        } catch (BadContextException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleNotificationButton(ActionEvent event) throws IOException {
        saveNoteContent(listView.getSelectionModel().getSelectedItem());
        Parent root = FXMLLoader.load(getClass().getResource("/notificationMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setOnHidden((ev) -> {
            try {
                initList();
            } catch (BadContextException e) {
                e.printStackTrace();
            }
        });
        stage.setTitle("Note Creator");
        stage.setScene(scene);
        stage.show();
    }
}
