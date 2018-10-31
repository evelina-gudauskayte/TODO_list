package BL.Managers;

import BL.User;
import DAL.UserDAO;
import DAL.UserDTO;
import Util.HashGenerator;

import java.sql.SQLException;

public class UserManager {
    public void updateUserPassword(String password) {

    }

    public void updateUsername(String username) {

    }

    public void deleleteUser(User user) { //нужно если не каскадное удаление, а я не помню какое у нас там
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.delete(new UserDTO(user));
            System.out.println(user.getUserName() + " is deleted.");
        } catch (SQLException e) {
            System.out.println(user.getUserName() + " is not deleted.");
            e.printStackTrace();
        }
    }

/*    public static ArrayList<String> getAllUsernames() {
        ArrayList<String> names = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        try {
            for (UserDTO userDTO : userDAO.getAll()) {
                names.add(userDTO.getName());
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return names;
    }*/


    public static boolean addNewUser(String username, String password) {
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.add(new UserDTO(new User(username, HashGenerator.hashPassword(password))));
            System.out.println("User added");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Not added");
        }

        return true;
    }
}
