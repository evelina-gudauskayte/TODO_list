import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    private static int nextId = 1;

    private int id = getNextID();
    private String userName;
    private String userPassword;
    private ToDoList toDoList;
    private ArrayList<String> friends;
    private boolean status = false; //false-only frinds can invite you, else everyone can

    private static int getNextID(){
        nextId++;
        return nextId;
    }

    public User(String name, String password){
        userName=name;
        try {
            userPassword = hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public boolean checkFriend(String friendName){
        for (String name: friends) {
            if(friendName == name){
                return true;
            }
        }
        return false;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return  String.format("%064x", new BigInteger(1, digest));
    }
}
