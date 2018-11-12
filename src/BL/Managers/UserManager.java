package BL.Managers;

import BL.User;
import DAL.UserDAO;
import DAL.UserDTO;

import java.sql.SQLException;

public class UserManager {
    private final UserDAO<UserDTO> userDAO;
    private User user;

    public UserManager(UserDAO<UserDTO> userDAO, User user){
        this.user = user;
        this.userDAO = userDAO;
    }
    public void updateUserPassword(String password) {

    }

    public void updateUsername(String username) {

    }

    public void deleleteUser() { //TODO нужно если не каскадное удаление, а я не помню какое у нас там
        try {
            userDAO.delete(user.getUserDTO());
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
}
