package Dialogs;

import TODO.Note;
import TODO.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotePanel extends JPanel {
    private MainFrame mainFrame;
    private final int sizeOfRow = 25;
    private Note note;
    private JLabel titleOfNote ;
    private JTextArea content;
    private JButton editButton;
    private JButton deleteNote;
    private JButton okButton = new JButton("ok");
    private JButton cancelButton = new JButton("cancel");
    public NotePanel(Note note, MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(0,1));
        this.note=note;
        titleOfNote = new JLabel(note.getId()+":"+note.getDate().toString());
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
                    ToDoList.getToDoList().removeNote(note);
                    mainFrame.update();
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
        add(buttons);
    }

    /*public static void main(String[] args){
        NotePanel panel1= new NotePanel(new Note("message1",new User("bob", "pass")));
        NotePanel panel2= new NotePanel(new Note("message2",new User("bob", "pass")));
        NotePanel panel3= new NotePanel(new Note("message3",new User("bob", "pass")));
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(0,2));
        newPanel.add(panel1);
        newPanel.add(panel2);
        newPanel.add(panel3);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLayout();
        frame.add(newPanel);
        frame.pack();
        frame.setVisible(true);

    }*/
}
