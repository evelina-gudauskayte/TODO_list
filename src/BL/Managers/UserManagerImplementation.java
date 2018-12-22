package BL.Managers;

import BL.User;
import DAL.Access;
import DAL.RealUserDAO;
import DAL.UserDAO;
import DAL.UserDTO;
import Util.HashGenerator;
import Util.Logger;

import java.util.ArrayList;

public class UserManagerImplementation implements UserManager {
    private final UserDAO<UserDTO> userDAO;

    public UserManagerImplementation(UserDAO<UserDTO> userDAO) {
        this.userDAO = userDAO;
    }

    public void updateUserPassword(User user, String password) {
        User newUser = user.clone();
        newUser.updatedUserPassword(password);
        Logger.getInstance().log(() -> userDAO.update(newUser.getUserDTO()), "note updating");
    }

    public void deleleteUser(User user) {
        Logger.getInstance().log(() -> userDAO.delete(user.getUserDTO()), "User deleting");
    }

    public User authorizeUser(String username, String password) {
        Context.getInstance().authorizeUser(username, password, userDAO);
        return Context.getInstance().getCurrentUser();
    }

    @Override
    public String getIdByUsername(String username) {
        return Logger.getInstance().logWithReturn(() -> userDAO.getUserId(username), "get id by username");
    }

    @Override
    public ArrayList<String> getAllUsernames() {
        return Logger.getInstance().logWithReturn(()->userDAO.getAllUserNames(),"get all usernames");
    }


    public void addNewUser(String username, String password) {
        RealUserDAO userDAO = new RealUserDAO();
        Logger.getInstance().log(() -> userDAO.add((new User(username, HashGenerator.hashPassword(password))).getUserDTO()), "User added");
    }
}
