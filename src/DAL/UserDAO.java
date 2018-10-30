package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class UserDAO implements DAO<UserDTO> {

    private static Connection connection = ConnectToDB.connect();

    public UserDTO get(String username, String password) {
        String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement selectUser = connection.prepareStatement(sql)) {
            selectUser.setString(1, username);
            selectUser.setString(2, password);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                return new UserDTO(rs.getString("id"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUserId(String username) {
        String sql = "SELECT id FROM users WHERE username = ? ";
        try (PreparedStatement selectUser = connection.prepareStatement(sql)) {
            selectUser.setString(1, username);
            ResultSet rs = selectUser.executeQuery();
            int ans = rs.getInt("id");
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public UserDTO get(String id) {
        String sql = "SELECT username, password FROM users WHERE id = ?";
        try (PreparedStatement selectUser = connection.prepareStatement(sql)) {
            selectUser.setString(1, id);
            ResultSet rs = selectUser.executeQuery();
            return new UserDTO(id, rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(UserDTO userDTO) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userDTO.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void add(UserDTO userDTO) throws SQLException {
        String sql = "INSERT INTO users(id, username,password) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userDTO.getId());
        preparedStatement.setString(2, userDTO.getName());
        preparedStatement.setString(3, userDTO.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(UserDTO userDTO, String... args){

    }

    @Override
    public ArrayList<UserDTO> getAll() throws SQLException {
        ArrayList<UserDTO> users = new ArrayList<>();
        String sql = "select id, username, password from users";
        PreparedStatement selectUsers = connection.prepareStatement(sql);
        ResultSet rs = selectUsers.executeQuery();
        while (rs.next()){
            users.add(new UserDTO(rs.getString("id"), rs.getString("username"), rs.getString("password")));
        }
        return users;
    }

    @Override
    public ArrayList<UserDTO> getSome(Predicate<UserDTO> predicate) throws SQLException{
        ArrayList<UserDTO> some = new ArrayList<>();
        for(UserDTO userDTO: this.getAll()){
            if(predicate.test(userDTO)){
                some.add(userDTO);
            }
        }
        return some;
    }

}
