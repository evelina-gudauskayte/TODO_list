package GUI;

import BL.JointNote;
import BL.Managers.JointNoteManager;
import BL.Managers.UserManager;
import com.sun.org.apache.xml.internal.security.utils.JDKXPathAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SelectUsersFrame extends JFrame {
    JointNote jointNote;
    public SelectUsersFrame(JointNote jointNote){
        setLayout(new GridLayout(0,1));
        this.jointNote = jointNote;
        UserManager userManager = new UserManager();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));
        for(String name : userManager.getAllUsernames()){
            panel.add(new JCheckBox(name));
            System.out.println(name);
        }
        add(panel);
        JButton button = new JButton("confirm");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JointNoteManager jointNoteManager = new JointNoteManager();
                for(Component comp : panel.getComponents()){
                    if( comp instanceof JCheckBox){
                        if(((JCheckBox)comp).isSelected()){
                            jointNoteManager.addUserToNote(jointNote, userManager.getIdByUsername(((JCheckBox) comp).getText()));
                            System.out.println("added"+((JCheckBox) comp).getText());
                        }
                    }
                }
             close();
            }
        });
        add(button);
        pack();
        setLocation(MainFrame.screenWidth / 2 - this.getWidth() / 2, MainFrame.screenHeight / 2 - this.getHeight() / 2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void launch(){
        setVisible(true);
    }
    public void close() {
        this.dispatchEvent((new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }
    public static void main(String args[]){
        SelectUsersFrame selectUsersFrame = new SelectUsersFrame(null);
        selectUsersFrame.launch();
    }
}
