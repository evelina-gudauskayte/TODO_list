package Dialogs;

import TODO.Note;
import TODO.ToDoList;
import TODO.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserNotesPanel extends JPanel {
    //MainFrame mainFrame;
    public UserNotesPanel(User user, MainFrame mainFrame){
        this.setLayout(new GridLayout(0,2));
        ArrayList<Note> notes = ToDoList.getToDoList().getUsersNotes(user, p->true);
        for(Note n : notes){
            this.add(new NotePanel(n, mainFrame));
        }
    }
    /*public static void main(String[] args){
        User alice = new User("Alice","password");
        ToDoList.getToDoList().addNote(new NoteDate(1012,06,12), "first note", alice);
        ToDoList.getToDoList().addNote(new NoteDate(2012,14,12), "sec note", alice);
        ToDoList.getToDoList().addNote(new NoteDate(2019,10,29), "third note", alice);
        UserNotesPanel panel = new UserNotesPanel(alice);
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }*/
}
