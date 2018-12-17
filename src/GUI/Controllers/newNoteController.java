package GUI.Controllers;

import BL.*;
import BL.Managers.*;
import DAL.*;
import Util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.util.ArrayList;

public class newNoteController {
    @FXML
    public TextArea textArea;
    @FXML
    public VBox checkBoxVBox;
    @FXML
    public DatePicker datePicker;
    @FXML
    public CheckBox inviteAllCheckBox;
    @FXML
    public ProgressBar createNoteProgressBar;

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();


    @FXML
    public void initialize() {
        initCheckBoxes();
    }

    private void initCheckBoxes() {
        UserManager userManager = new UserManagerImplementation(new RealUserDAO());
        for (String name : userManager.getAllUsernames()) {
            if (!name.equals(Context.getInstance().getCurrentUser().getUserName())) {
                CheckBox checkBox = new CheckBox(name);
                checkBoxVBox.getChildren().add(checkBox);
                checkBoxes.add(checkBox);
            }
        }
    }

    @FXML
    public void handleCreateButton(ActionEvent actionEvent) throws BadContextException {
        ArrayList<String> names = new ArrayList<>();
        if (inviteAllCheckBox.isSelected()) {
            for (CheckBox checkBox : checkBoxes) {
                names.add(checkBox.getText());
            }
        } else {
            for (CheckBox check : checkBoxes) {
                if (check.isSelected()) {
                    names.add(check.getText());
                }
            }
        }
        createNote(names);
    }

    private void createNote(ArrayList<String> names) throws BadContextException { //TODO progress bar from here depends on size of array
        NoteDate date;
        if (datePicker.getValue() == null) {
            date = new NoteDate();
        } else {
            date = new NoteDate(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth());
        }
        NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
        UserManager userManager = new UserManagerImplementation(new RealUserDAO());
        if (names.size() != 0) {
            ArrayList<String> ids = new ArrayList<>();
            for (String name : names) {
                ids.add(userManager.getIdByUsername(name));
                System.out.println(userManager.getIdByUsername(name));
            }
            noteManager.add(new JointNote(date, textArea.getText(), ids));
        } else {
            noteManager.add(new Note(date, textArea.getText()));
        }
    }

    @FXML
    public void handleInviteAllCheckBox(ActionEvent actionEvent) {
        if (inviteAllCheckBox.isSelected()) {
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setSelected(true);
            }
        } else {
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setSelected(false);
            }
        }
    }
}
