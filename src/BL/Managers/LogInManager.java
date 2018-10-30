package BL.Managers;

import BL.User;
import DAL.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LogInManager {
    public static boolean addNewUser(String username, String password) { //ok
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        try {
            Access.addUser(new UserDTO(new User(username, String.format("%064x", new BigInteger(1, digest)))));
            System.out.println("User added");
            return true;
        } catch (SQLException e) {
            System.out.println("User not added");
            return false;
        }
    }

    public static User authorizeUser(String username, String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        Access access = new Access();
        UserDTO userDTO = Access.getUser(username, String.format("%064x", new BigInteger(1, digest)));
        if (userDTO == null) {
            return null;
        }
        return new User(userDTO);
    }

}
