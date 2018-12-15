package BL.Managers;

import BL.User;
import DAL.RealUserDAO;
import DAL.UserDAO;
import DAL.UserDTO;
import Util.HashGenerator;
import Util.Logger;

public class UserManagerImplementation implements UserManager {
    private final UserDAO<UserDTO> userDAO;

    public UserManagerImplementation(UserDAO<UserDTO> userDAO){
        this.userDAO = userDAO;
    }

    public void updateUserPassword(User user, String password) {
        User newUser = user.clone();
        newUser.updatedUserPassword(password);
        Logger.getInstance().log(() -> userDAO.update(user.getUserDTO(), newUser.getUserDTO()), "note updating");
    }
    public void deleleteUser(User user) {
        Logger.getInstance().log(() -> userDAO.delete(user.getUserDTO()), "User deleting");
    }
    public User authorizeUser(String username, String password){
       // System.out.println(username + HashGenerator.hashPassword(password));
        Context.getInstance().authorizeUser(username,password,userDAO);
//        UserDTO userDTO = Logger.getInstance().logWithReturn(() -> userDAO.get(username, HashGenerator.hashPassword(password)), "Authorization");
//        if (userDTO == null){
//            return null;
//        }
//        return new User(userDTO);
        return Context.getInstance().getCurrentUser();
    }
    public void addNewUser(String username, String password) {
        RealUserDAO userDAO = new RealUserDAO();
        Logger.getInstance().log(() -> userDAO.add((new User(username, HashGenerator.hashPassword(password))).getUserDTO()), "User added");
    }
}
