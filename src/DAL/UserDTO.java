package DAL;

public class UserDTO {
    private int id;
    private String name;
    private String password;

    UserDTO(int id, String name, String password) { //pac private
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
