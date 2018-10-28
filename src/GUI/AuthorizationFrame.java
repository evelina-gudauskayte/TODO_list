package GUI;

import BL.LogInManager;
import BL.Session;
import BL.User;
import DESIGN.DesignedFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizationFrame extends DesignedFrames {
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton enterButton = new JButton("Enter");
    private JButton registerButton = new JButton("Registration");
    private String username;
    private String password;
    public AuthorizationFrame() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = LogInManager.authorizeUser(usernameField.getText(), new String(passwordField.getPassword()));
                if (user != null) {
                    MainFrame mainFrame = new MainFrame(user);
                    mainFrame.launch();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong username or password");
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisrationFrame regisrationFrame = new RegisrationFrame();
                regisrationFrame.launch();
                regisrationFrame.setVisible(false);
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(enterButton);
        panel.add(registerButton);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(panel);
        setLayout(new GridLayout(0, 1));
        //setSize(240,120);
        pack();
        setLocation(MainFrame.screenWidth / 2 - this.getWidth() / 2, MainFrame.screenHeight / 2 - this.getHeight() / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void launch(){
        setVisible(true);
    }

    public static void main(String[] args){
        AuthorizationFrame authorizationFrame = new AuthorizationFrame();
        authorizationFrame.launch();

    }
}
