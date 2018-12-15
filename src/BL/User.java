package BL;

import DAL.UserDTO;

import java.util.ArrayList;
import java.util.UUID;

public class User implements Cloneable{
    private String id = String.valueOf(UUID.randomUUID());
    private String userName;
    private String userPassword;
    private ArrayList<Integer> notifications = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        if (user.getId().equals(id)
                || (user.getUserName().equalsIgnoreCase(userName) && user.getUserPassword().equals(userPassword))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getUserName();
    }

    public User clone(){
        try {
            return (User)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw  new InternalError();
        }
    }

    public User(String name, String password) {
        userName = name;
        this.userPassword = password;
    }

    public User(UserDTO userDTO) {
        userName = userDTO.getName();
        userPassword = userDTO.getPassword();
        id = userDTO.getId();
    }

    public UserDTO getUserDTO(){
        return new UserDTO(this.id,this.userName,this.userPassword);
    }

    public String getId() {
        return id;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void updatedUserPassword(String newUserPassword){
        this.userPassword=newUserPassword;
    }

    public String getUserName() {
        return userName;
    }
}
