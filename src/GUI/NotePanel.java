package GUI;

import BL.JointNote;
import BL.Managers.JointNoteManager;
import BL.Note;
import BL.Managers.Session;
//import BL.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotePanel extends JPanel {
    private final int sizeOfRow = 25;
    private Note note;
    private JLabel titleOfNote ;
    private JTextArea content;
    private JButton editButton;
    private JButton deleteNote;
    private JButton okButton = new JButton("ok");
    private JButton cancelButton = new JButton("cancel");
    public NotePanel(Note note, MainFrame mainFrame, Session session){
        //this.mainFrame = mainFrame;

        setLayout(new GridLayout(0,1));
        this.note=note;
        titleOfNote = new JLabel(note.getDate().toString());
        content = new JTextArea(note.getContent(),note.getContent().length()/sizeOfRow,sizeOfRow);
        content.setEditable(false);
        content.setLineWrap(true);
        okButton.setVisible(false);
        cancelButton.setVisible(false);

        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //String firstContent = content.getText();
                content.setEditable(true);
                okButton.setVisible(true);
                cancelButton.setVisible(true);
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        content.setText(note.getContent());
                        content.setEditable(false);
                        okButton.setVisible(false);
                        cancelButton.setVisible(false);
                    }
                });
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        note.changeContent(content.getText());
                        content.setEditable(false);
                        okButton.setVisible(false);
                        cancelButton.setVisible(false);
                    }
                });
            }
        });
        deleteNote = new JButton("Delete");
        deleteNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int result = JOptionPane.showConfirmDialog(null,
                        new JLabel("Do you really want to delete it?"),
                        "Deleting",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null);
                if(result == JOptionPane.YES_OPTION){
                    session.deleteNote(note.getId());
                    mainFrame.updateNotes();
                }
            }
        });
        add(titleOfNote);
        add(content);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(editButton);
        buttons.add(deleteNote);
        buttons.add(okButton);
        buttons.add(cancelButton);
        if(note instanceof JointNote){
            JointNoteManager manager = new JointNoteManager();
            StringBuilder names = new StringBuilder();
            for(String n : manager.getNamesOfJointNoteUsers((JointNote) note)){
                names.append(n).append(" ");
            }
            JTextArea area = new JTextArea(names.toString());
            area.setEditable(false);
            add(area);
        }
        add(buttons);

    }
}
