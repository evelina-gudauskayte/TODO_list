package TODO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private static AtomicInteger nextId =new AtomicInteger(1);

    private int id = nextId.incrementAndGet();
    private String userName;
    private String userPassword;

    @Override
    public boolean equals(Object object){
        User user = (User) object;
        if(user.getUserName().equalsIgnoreCase(userName) && user.getUserPassword().equals(userPassword)){
            return true;
        }
        return false;
    }
    public User(String name, String password) {
        userName = name;
        try {
            userPassword = hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return String.format("%064x", new BigInteger(1, digest));
    }
}
