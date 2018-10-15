package Dialogs;

import TODO.Note;
import TODO.User;

import javax.swing.*;

public class MainFrame extends JFrame{
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField passwordField = new JPasswordField("password");
    private JTextField usernameField = new JTextField("Enter Username");
    private JButton enterButton = new JButton("Enter");
    public MainFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


    }
    public  void  launch(){
        AuthorizationFrame authorizationFrame = new AuthorizationFrame();
        if(authorizationFrame.StartAuthorizationDialog()) {
            this.setVisible(true);
            this.pack();
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.add(new NotePanel(new Note("Massage",new User("bob", "pass"))));
        mainFrame.launch();

    }
}
