package BL;

import DAL.UserDTO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private int id ;
    private String userName;
    private String userPassword;
    private ArrayList<Integer> notifications = new ArrayList<>();

    @Override
    public boolean equals(Object object){
        User user = (User) object;
        if(user.getUserName().equalsIgnoreCase(userName) && user.getUserPassword().equals(userPassword)){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return this.getUserName();
    }

    public User(String name, String password) {
        userName = name;
        this.userPassword = password;
        this.id = -1;
    }
    public User(UserDTO userDTO){
        userName = userDTO.getName();
        userPassword = userDTO.getPassword();
        id = userDTO.getId();
    }

    public int getId() {
        return id;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getUserName() {
        return userName;
    }

    //сделать изменение пароля, логина.

//    public static String hashPassword(String password) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(password.getBytes(StandardCharsets.UTF_8));
//        byte[] digest = md.digest();
//        return String.format("%064x", new BigInteger(1, digest));
//    }
}
