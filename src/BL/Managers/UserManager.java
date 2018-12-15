package BL.Managers;

import BL.User;

public interface UserManager {
    void updateUserPassword(User user, String password);
    void addNewUser(String username, String password);
    void deleleteUser(User user);
    User authorizeUser(String username, String password);
}
