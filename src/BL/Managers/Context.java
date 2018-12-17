package BL.Managers;

import BL.User;
import DAL.UserDAO;
import DAL.UserDTO;
import Util.HashGenerator;
import Util.Logger;

public class Context {
    private static Context context = new Context();
    private static User currentUser;

    public static Context getInstance() {
        return context;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    /*package private method*/
    void authorizeUser(String username, String password, UserDAO<UserDTO> userDAO) {
        UserDTO userDTO = Logger.getInstance().logWithReturn(() -> userDAO.get(username, HashGenerator.hashPassword(password)), "Authorization");
        if (userDTO != null) {
            currentUser = new User(userDTO);
        } else {
            currentUser = null;
        }
    }

    public boolean isAuthorized() {
        return currentUser != null;
    }

    public void logOut() {
        currentUser = null;
    }

    private Context() {
    }
}
