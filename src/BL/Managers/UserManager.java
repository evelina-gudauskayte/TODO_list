package BL.Managers;

import BL.User;
import DAL.Access;

public class UserManager {
    User user;

    public void updateUserPassword(String password) {

    }

    public void updateUsername(String username) {

    }

    public void deleleteUSer() { //нужно если не каскадное удаление, а я не помню какое у нас там

    }

    public int getIdByUsername(String username) {
        Access access = new Access();
        return access.getUserId(username);
    }
    /*public ArrayList<String> getAllUsernames(){
        return new Access().getAllUsernames();
    }*/
}
