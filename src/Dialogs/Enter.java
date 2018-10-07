package Dialogs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enter {
    private JTextField textField1;
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton enterButton;
    private String username = "";
    private String password = "";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Enter(){
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                username = textField1.getText();
                password = new String(passwordField1.getPassword());
                System.out.println("Done");
            }
        });
    }

    public void start() {
        JFrame frame = new JFrame("Enter");
        frame.setContentPane(new Enter().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
