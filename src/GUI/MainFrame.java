package GUI;

import BL.Managers.Session;
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

    public MainFrame() {
        setLocation(MainFrame.screenWidth / 2 - this.getWidth() / 2, MainFrame.screenHeight / 2 - this.getHeight() / 2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void close() {
        this.dispatchEvent((new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }

//    public void launch() {
//        //AuthorizationFrame authorizationFrame = new AuthorizationFrame();
//        authorizationFrame.launch();
//    }

    public void authorize(User user){
        session = new Session(user);
        if (session.isAuthorized()) {
            JPanel sessionPanel = new JPanel();

            sessionPanel.add(new JLabel(session.getCurrentUser().getUserName()));

            JButton logOutButton = new JButton("LogOut");
            logOutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    session.logOut();
                    setVisible(false);
                    removeAll();
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
                }
            });
            sessionPanel.add(createNote);

            this.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            this.add(sessionPanel, constraints);
            constraints.gridy = 1;
            this.add(new UserNotesPanel(this, session), constraints);
            this.pack();
            this.setVisible(true);
        }
    }

    public void updateNotes() {
        if (session.isAuthorized()) {
            this.getContentPane().remove(1);
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridy=1;
            constraints.gridx=0;
            this.add(new UserNotesPanel(this, session),constraints);
            this.pack();
        }

    }
}
