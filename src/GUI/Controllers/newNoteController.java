package GUI.Controllers;

import BL.*;
import BL.Listeners.ProgressBarListener;
import BL.Managers.*;
import BL.Tasks.CreateNoteTask;
import DAL.*;
import Util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    @FXML
    public Button createButton;

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
                checkBox.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        checkCheckBoxesStatus();
                    }
                });
                checkBoxVBox.getChildren().add(checkBox);
                checkBoxes.add(checkBox);
            }
        }
    }

    private void checkCheckBoxesStatus() {
        int counter = 0;
        for (CheckBox checkBox : checkBoxes) {
            if (inviteAllCheckBox.isSelected() && !checkBox.isSelected()) inviteAllCheckBox.setSelected(false);
            if (checkBox.isSelected()) counter++;
        }
        if (counter == checkBoxes.size()) inviteAllCheckBox.setSelected(true);
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
        NoteDate date;
        if (datePicker.getValue() == null) {
            date = new NoteDate();
        } else {
            date = new NoteDate(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth()); //TODO test dates
        }
        NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
        UserManager userManager = new UserManagerImplementation(new RealUserDAO());

        CreateNoteTask createNoteTask = new CreateNoteTask(date, names, textArea.getText(), noteManager, userManager);
        createNoteTask.addListener(new ProgressBarListener(createNoteProgressBar));

        createNoteTask.setOnRunning((taskEvent) -> createButton.setDisable(true));
        createNoteTask.setOnSucceeded((taskEvent) -> {
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.close();
        });

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(createNoteTask);
        executor.shutdown();
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
