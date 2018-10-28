package GUI;

import BL.Note;
import BL.Managers.Session;
//import BL.ToDoList;

import javax.swing.*;
import java.awt.*;

public class UserNotesPanel extends JPanel {
    public UserNotesPanel(MainFrame mainFrame, Session session){
        this.setLayout(new GridLayout(0,2));
        for(Note n : session.getAllNotes()){
            this.add(new NotePanel(n, mainFrame,session));
        }
    }
}
