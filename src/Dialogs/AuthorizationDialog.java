package Dialogs;

import javax.swing.*;
import java.awt.*;

public class AuthorizationDialog extends JDialog {
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton enterButton = new JButton("Enter");
    private JButton registerButton = new JButton("Registration");
    public AuthorizationDialog(JFrame owner){
        super(owner, "Authorization", true);
        JPanel panel = new JPanel();
        //panel.add(Box.createRigidArea(new Dimension(10,0));
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(enterButton);
        panel.add(registerButton);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(panel);
        setLayout(new GridLayout(0,1));
        setSize(240,120);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
    public static void main(String[] args){
        AuthorizationDialog authorizationDialog = new AuthorizationDialog(new JFrame());

    }
}
