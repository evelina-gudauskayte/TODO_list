package GUI.Controllers;

import BL.JointNote;
import BL.Managers.*;
import BL.Note;
import DAL.RealNoteDAO;
import DAL.RealUserDAO;
import Util.BadContextException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.tools.Tool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

public class MainSceneController {
    private Note prevSelected = null;
    private BorderPane borderPane = null;

    @FXML
    public Button notificationButton;
    @FXML
    public HBox Hbox;
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
            initButtonLabel();
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

    private void initButtonLabel(){
        try {
            NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
            notificationButton.setText("Notifications("+noteManager.getUnnoticed().size()+")");
        } catch (BadContextException e) {
            e.printStackTrace();
        }

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
        if (event.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null) {
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
    public void handleNotificationButton(ActionEvent event) {
        try {
            if (borderPane == null) {
                NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
                borderPane = new BorderPane();
                ListView<Note> noteListView = new ListView<>();
                ToolBar toolBar = new ToolBar();
                Button accept = new Button("Accept");
                noteListView.setCellFactory(param -> new ListCell<Note>() {
                    @Override
                    protected void updateItem(Note note, boolean empty) {
                        super.updateItem(note, empty);
                        if (!empty && note != null) {
                            setText("Author:" + noteManager.getNoteAuthor(note) + "\n" + note.getContent());
                        } else {
                            setText("");
                        }
                    }
                });
                for (Note note : noteManager.getUnnoticed()) {
                    noteListView.getItems().add(note);
                }
                accept.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        noteManager.setNoticed((JointNote) noteListView.getSelectionModel().getSelectedItem());
                        noteListView.getItems().remove(noteListView.getSelectionModel().getSelectedItem());
                        try {
                            initList();
                        } catch (BadContextException e) {
                            e.printStackTrace();
                        }
                        initButtonLabel();
                    }
                });
                Button decline = new Button("Decline");
                decline.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        noteManager.deleteNote(noteListView.getSelectionModel().getSelectedItem());
                        noteListView.getItems().remove(noteListView.getSelectionModel().getSelectedItem());
                        initButtonLabel();
                    }
                });
                accept.setMinWidth(50);
                decline.setMinWidth(50);
                toolBar.getItems().addAll(accept, decline);
                borderPane.setMinWidth(140);
                borderPane.setCenter(noteListView);
                borderPane.setBottom(toolBar);
                Hbox.getChildren().add(borderPane);
            } else {
                Hbox.getChildren().remove(borderPane);
                borderPane = null;
            }
        } catch (BadContextException e) {
            e.printStackTrace();
        }

    }
}
