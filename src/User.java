import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

public class User {

    private static int nextId = 1;

    private int id = getNextID();
    private String userName;
    private String userPassword;
    private ToDoList toDoList = new ToDoList();
    private ArrayList<String> friends = new ArrayList<String>();
    private boolean status = false; //notification before getting in note, if status id true then notes will be added to list without any notification

    private static int getNextID() {
        nextId++;
        return nextId;
    }

    public User(String name, String password) {
        userName = name;
        try {
            userPassword = hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void addNote(NoteDate date, String content) {
        toDoList.addNote(date, content);
    }
    public void addNote(Note note){
        toDoList.addNote(note);
    }

    public void printNotes(PrintStream stream) {
        toDoList.printAllNotes(stream);
    }

    public void printJointNotes(PrintStream stream) {
        toDoList.printJointNotes(stream);
    }

    public void printPersonalNotes(PrintStream stream) {
        toDoList.printPersonalNotes(stream);
    }

    public boolean isStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    //create a notice system
    public boolean checkFriend(User user) {
        for (String name : friends) {
            if (user.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean enterSystem(String name, String password) {
        try {
            if (name.equals(this.userName) && hashPassword(password).equals(this.userPassword)) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return String.format("%064x", new BigInteger(1, digest));
    }
}
