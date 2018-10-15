package Dialogs;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AppView  {
    public void createWindow(){
        JFrame frame = new JFrame("Enter");
        JTextField usernameField = new JTextField();
        //usernameField.setMinimumSize(new Dimension(100,50));
        usernameField.setBorder(new LineBorder(Color.GRAY,5));
        JLabel usernameLabel = new JLabel("username");
        usernameLabel.setLocation(0,0);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        panel.add(usernameField);
       // panel.setMinimumSize(new Dimension(100,50));

       // panel.add(usernameLabel);



        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(400,400);
       // frame.pack();

        frame.setVisible(true);
     /*   while (frame.isVisible()){

        }*/
        System.out.println("done");
       // System.exit(0);

    }
    public static void main(String[] args) {
        AppView app = new AppView();
        app.createWindow();

    }
}
