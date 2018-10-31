package BL.Managers;

import BL.User;
import DAL.UserDAO;
import DAL.UserDTO;
import Util.HashGenerator;

import java.sql.SQLException;

public class LogInManager {

    public static User authorizeUser(String username, String password) throws NullPointerException {
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = null;
        try {
            System.out.println(username+HashGenerator.hashPassword(password));
            userDTO = userDAO.get(username, HashGenerator.hashPassword(password));
            return new User(userDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Wrong something");
        return null;
    }
}