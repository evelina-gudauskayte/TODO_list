package GUI;

import BL.LogInManager;
import DESIGN.DesignedButton;
import DESIGN.DesignedFrames;
import DESIGN.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisrationFrame extends DesignedFrames {

    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField confirmPassword = new JPasswordField();

    public void launch(){
        setLayout(new GridLayout(0,1));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.add(new JLabel("Username:"));
        panel.add(username);
        panel.add(new JLabel("Password"));
        panel.add(password);
        panel.add(new JLabel("Confirm password"));
        panel.add(confirmPassword);
        add(panel);

        JButton okButton = new JButton("Registration");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userPass = new String(password.getPassword());
                if(userPass.equals(new String(confirmPassword.getPassword())) && !userPass.isEmpty() && !username.getText().isEmpty()){
                    if(LogInManager.addNewUser(username.getText(),userPass)){
                        JOptionPane.showMessageDialog(null,"Successfully registered");
                    }else {
                        JOptionPane.showMessageDialog(null,"Please try another username");
                    }
                }
            }
        });
        panel.add(okButton);
        pack();
        setLocation(MainFrame.screenWidth/2 - this.getWidth()/2, MainFrame.screenHeight/2-this.getHeight()/2);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args){
        RegisrationFrame regisrationFrame = new RegisrationFrame();
        regisrationFrame.launch();
    }
}
