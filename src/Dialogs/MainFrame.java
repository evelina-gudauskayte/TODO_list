package Dialogs;

import TODO.User;

import javax.swing.*;

public class MainFrame extends JFrame{
    User currentUser;
    public MainFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public  void  launch(){
        AuthorizationFrame authorizationFrame = new AuthorizationFrame();
        currentUser=authorizationFrame.StartAuthorizationDialog();
        if( currentUser != null) {
            this.add(new UserNotesPanel(currentUser));
            this.pack();
            this.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.launch();

    }
}
