package BL;

import DAL.UserDTO;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String id = String.valueOf(UUID.randomUUID()) ;
    private String userName;
    private String userPassword;
    private ArrayList<Integer> notifications = new ArrayList<>();

    @Override
    public boolean equals(Object object){
        User user = (User) object;
        if(user.getUserName().equalsIgnoreCase(userName) && user.getUserPassword().equals(userPassword)){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return this.getUserName();
    }

    public User(String name, String password) {
        userName = name;
        this.userPassword = password;
    }
    public User(UserDTO userDTO){
        userName = userDTO.getName();
        userPassword = userDTO.getPassword();
        id = userDTO.getId();
    }

    public String getId() {
        return id;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getUserName() {
        return userName;
    }
}
