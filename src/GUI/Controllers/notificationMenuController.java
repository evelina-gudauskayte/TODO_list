package GUI.Controllers;

import BL.JointNote;
import BL.Managers.NoteManager;
import BL.Managers.NoteManagerImplementation;
import BL.Note;
import DAL.RealNoteDAO;
import GUI.NotificationPane;
import Util.BadContextException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class notificationMenuController {
    @FXML
    public ListView<NotificationPane> listOfNotifications;

    @FXML
    public void initialize(){
        try {
            NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
            for(Note note: noteManager.getUnnoticed()){
                listOfNotifications.getItems().add(new NotificationPane((JointNote)note));
            }
        } catch (BadContextException e) {
            e.printStackTrace();
        }
    }

}
