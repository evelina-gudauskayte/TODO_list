package Dialogs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutButton extends JButton {
    MainFrame mainFrame;
    public LogOutButton(String name,MainFrame mainFrame){
        super(name);
        this.mainFrame=mainFrame;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.setVisible(false);
                mainFrame.launch();
            }
        });
    }
}
