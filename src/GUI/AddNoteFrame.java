package GUI;

import BL.Note;
import BL.NoteDate;
import BL.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AddNoteFrame extends JFrame {
    private Session session = null;
    private JTextField content = new JTextField();
    private JTextField year = new JTextField();
    private JTextField month = new JTextField();
    private JTextField day = new JTextField();
    private JButton create = new JButton("create");
    private JCheckBox isJoint = new JCheckBox("Joint");
    public AddNoteFrame(MainFrame mainFrame){
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        datePanel.add(new JLabel("Year"));
        datePanel.add(year);
        datePanel.add(new JLabel("Month"));
        datePanel.add(month);
        datePanel.add(new JLabel("Day"));
        datePanel.add(day);
        datePanel.add(isJoint);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JLabel("content:"));
        panel.add(content);
        panel.add(datePanel);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(year.getText().isEmpty()){
                    session.addNote(new Note(content.getText(),session.getCurrentUser()));
                }else {
                    session.addNote(new Note(new NoteDate(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText())), content.getText()));
                }
                mainFrame.update();
                close();
            }
        });
        panel.add(create);
        add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void launch(Session session){
        this.session=session;
        setLocation(MainFrame.screenWidth/2 - this.getWidth()/2, MainFrame.screenHeight/2-this.getHeight()/2);
        setSize(200,200);
        setVisible(true);
    }
    public void close(){
        this.dispatchEvent((new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }

   /* public static void main(String args[]){
        AddNoteFrame addNoteFrame = new AddNoteFrame();
        addNoteFrame.launch();
    }*/
}
