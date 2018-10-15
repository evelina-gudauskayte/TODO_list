package Dialogs;

import DataBase.Authorization;

import javax.swing.*;

public class AuthorizationFrame {
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    public AuthorizationFrame(){    }

    public boolean StartAuthorizationDialog(){
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Username"),
                usernameField,
                new JLabel("Password"),
                passwordField
        };
        while(true) {
            int result = JOptionPane.showConfirmDialog(null, inputs, "Autorization", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
            if (result == JOptionPane.OK_OPTION) {
                Authorization authorization = new Authorization();
                System.out.println(usernameField.getText() + " "+ new String(passwordField.getPassword()));
                if (authorization.authorize(usernameField.getText(), new String(passwordField.getPassword()))){
                    System.out.println("authorized");
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null,"Wrong username or password");
                }
            } else {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args){
        AuthorizationFrame authorizationFrame = new AuthorizationFrame();
        authorizationFrame.StartAuthorizationDialog();

    }

}
