package Dialogs;

import TODO.Note;
import TODO.User;

import javax.swing.*;

public class NotePanel extends JPanel {
    private Note note;
    private JLabel titleOfNote ;
    private JLabel contentLabel;
    private JButton editButton;
    private JButton deleteNote;
    public NotePanel(Note note){
        this.note=note;
         titleOfNote = new JLabel(note.getId()+":"+note.getDate().toString());
         contentLabel = new JLabel(note.getContent());
         editButton = new JButton("Edit");
         deleteNote = new JButton("Delete");
        add(titleOfNote);
        add(contentLabel);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(editButton);
        buttons.add(deleteNote);
        add(buttons);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    public static void main(String[] args){
        NotePanel panel= new NotePanel(new Note("message",new User("bob", "pass")));
    }
}
