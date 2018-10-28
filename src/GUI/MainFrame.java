package GUI;

import BL.Session;
import BL.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    private Session session;
    AddNoteFrame addNoteFrame = new AddNoteFrame(this);
    //User currentUser;

    public MainFrame(User user) {
        //this.currentUser = user;
        this.session = new Session(user);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void close() {
        this.dispatchEvent((new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }

    public void launch() {
        if (session.isAuthorized()) {
            JPanel sessionPanel = new JPanel();
            sessionPanel.add(new JLabel(session.getCurrentUser().getUserName()));
            JButton logOutButton = new JButton("LogOut");
            logOutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    session.logOut();
                    AuthorizationFrame authorizationFrame = new AuthorizationFrame();
                    authorizationFrame.launch();
                }
            });
            sessionPanel.add(logOutButton);
            JButton deleteUser = new JButton("delete account");
            deleteUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(null, "Are you really want to delete your account?", "Deleting account", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        session.deleteUser();
                        close();
                    }
                }
            });
            sessionPanel.add(deleteUser);
            JButton createNote = new JButton("Add note");
            createNote.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addNoteFrame.launch(session);
                    update();
                }
            });
            sessionPanel.add(createNote);
            this.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            //constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 0;
            this.add(sessionPanel, constraints);

            constraints.gridx = 1;
            this.add(new UserNotesPanel(this, session), constraints);
            this.pack();
            this.setVisible(true);
        }
    }

    public void update() {
        if (session.isAuthorized()) {
            this.setVisible(false);
            this.getContentPane().remove(1);
            this.add(new UserNotesPanel(this, session));
            this.setVisible(true);
        }

    }

    /*public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.launch();

    }*/
}
