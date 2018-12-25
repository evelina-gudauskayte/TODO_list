package GUI;

import BL.JointNote;
import BL.Managers.NoteManager;
import BL.Managers.NoteManagerImplementation;
import BL.Note;
import DAL.RealNoteDAO;
import Util.BadContextException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

public class NotificationPane extends VBox {
    JointNote note;

    public NotificationPane(JointNote note, Node... children) throws BadContextException {
        super(children);
        this.note = note;

        Label content = new Label(note.getContent());
        NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
        Label author = new Label(noteManager.getNoteAuthor(note));

        this.getChildren().add(author);
        this.getChildren().add(content);
        ToolBar toolBar = new ToolBar();
        Button accept = new Button("Accept");
        accept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                noteManager.setNoticed(note);
            }
        });
        Button decline = new Button("Decline");
        decline.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                noteManager.deleteNote(note);
            }
        });
        accept.setMinWidth(130);
        decline.setMinWidth(130);
        toolBar.getItems().add(accept);
        toolBar.getItems().add(decline);
        this.getChildren().add(toolBar);
    }
}
