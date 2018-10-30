package DAL;

import BL.User;

public class UserDTO {
    private String id;
    private String name;
    private String password;

    public UserDTO(String id, String name, String password) { //pac private
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserDTO(User user) { //pac private
        this.id = user.getId();
        this.name = user.getUserName();
        this.password = user.getUserPassword();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
