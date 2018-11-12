package BL.Managers;

import BL.User;
import DAL.RealUserDAO;
import DAL.UserDTO;
import Util.HashGenerator;
import Util.Logger;

public class LogInManager {

    public static User authorizeUser(String username, String password) throws NullPointerException {
        RealUserDAO userDAO = new RealUserDAO();
        System.out.println(username + HashGenerator.hashPassword(password));
        UserDTO userDTO = Logger.getInstance().logWithReturn(() -> userDAO.get(username, HashGenerator.hashPassword(password)), "Authorized");
//        UserDTO userDTO = null;
//        try {
//            userDTO = userDAO.get(username, HashGenerator.hashPassword(password));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return new User(userDTO);
    }

    public static boolean addNewUser(String username, String password) {
        RealUserDAO userDAO = new RealUserDAO();
        Logger.getInstance().log(() -> userDAO.add((new User(username, HashGenerator.hashPassword(password))).getUserDTO()), "User added");
        return true;
    }
}
