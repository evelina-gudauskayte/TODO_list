package Dialogs;

import TODO.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    User currentUser;
    LogOutButton logOutButton = new LogOutButton("Log Out", this);

    public MainFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void launch() {
        currentUser = null;
        this.getContentPane().removeAll();
        AuthorizationFrame authorizationFrame = new AuthorizationFrame();
        currentUser = authorizationFrame.StartAuthorizationDialog();
        if (currentUser != null) {
            JPanel sessionPanel = new JPanel();
            sessionPanel.add(new JLabel(currentUser.getUserName()));
            sessionPanel.add(logOutButton);
            this.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            //constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 0;
            this.add(sessionPanel, constraints);

            constraints.gridx = 1;
            this.add(new UserNotesPanel(currentUser, this), constraints);
            this.pack();
            this.setVisible(true);
        }
    }
    public void update(){
        if(currentUser != null){
            this.setVisible(false);
            this.getContentPane().remove(1);
            this.add(new UserNotesPanel(currentUser,this));
            this.setVisible(true);
        }

    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.launch();

    }
}
