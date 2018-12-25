package BL.Managers;

import BL.User;

import java.util.ArrayList;

public interface UserManager {
    void updateUserPassword(User user, String password);
    void addNewUser(String username, String password);
    void deleteUser(User user);
    User authorizeUser(String username, String password);
    String getIdByUsername(String username);
    ArrayList<String> getAllUsernames();
    String getUsernameById(String id);
}
