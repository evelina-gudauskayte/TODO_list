package DAL;

public class UserDTO {
    private String id;
    private String name;
    private String password;

    public UserDTO(String id, String name, String password) { //pac private
        this.id = id;
        this.name = name;
        this.password = password;
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
