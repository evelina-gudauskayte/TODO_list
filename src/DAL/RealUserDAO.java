package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class RealUserDAO implements UserDAO<UserDTO> {

    private static Connection connection = Access.getConnection();

    @Override
    public UserDTO get(String username, String password) throws SQLException {
        String sql = "SELECT id, username, password FROM users WHERE username = ? collate nocase AND password = ?";
        PreparedStatement selectUser = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        selectUser.setString(1, username);
        selectUser.setString(2, password);
        ResultSet rs = selectUser.executeQuery();
        connection.commit();
        if (rs.next()) {
            connection.setAutoCommit(true);
            return new UserDTO(rs.getString("id"), rs.getString("username"), rs.getString("password"));
        }
        rs.close();
        connection.setAutoCommit(true);
        return null;
    }

    @Override
    public String getUsernameById(String id) throws SQLException {
        String sql = "SELECT username FROM users WHERE id = ? ";
        PreparedStatement selectUser = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        selectUser.setString(1, id);
        ResultSet rs = selectUser.executeQuery();
        String ans = rs.getString("username");
        connection.commit();
        rs.close();
        connection.setAutoCommit(true);
        return ans;
    }

    @Override
    public String getUserId(String username) throws SQLException {
        String sql = "SELECT id FROM users WHERE username = ? ";
        PreparedStatement selectUser = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        selectUser.setString(1, username);
        ResultSet rs = selectUser.executeQuery();
        String ans = rs.getString("id");
        connection.commit();
        rs.close();
        connection.setAutoCommit(true);
        return ans;
    }

    @Override
    public ArrayList<String> getAllUserNames() throws SQLException {
        ArrayList<String> names = new ArrayList<>();
        for(UserDTO userDTO: getAll()){
            names.add(userDTO.getName());
        }
        return names;
    }

    @Override
    public UserDTO get(String id) throws SQLException {
        String sql = "SELECT username, password FROM users WHERE id = ? ";
        PreparedStatement selectUser = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        selectUser.setString(1, id);
        ResultSet rs = selectUser.executeQuery();
        UserDTO userDTO = new UserDTO(id, rs.getString("username"), rs.getString("password"));
        connection.commit();
        rs.close();
        connection.setAutoCommit(true);
        return userDTO;
    }

    @Override
    public void delete(UserDTO userDTO) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        preparedStatement.setString(1, userDTO.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        sql = "DELETE from notes where userId = ? ";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userDTO.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        sql = "DELETE from jointNotes where userId = ? "; //add deleting from joint notes users's notes
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userDTO.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public void add(UserDTO userDTO) throws SQLException {
        String sql = "INSERT INTO users(id, username,password) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        preparedStatement.setString(1, userDTO.getId());
        preparedStatement.setString(2, userDTO.getName());
        preparedStatement.setString(3, userDTO.getPassword());
        preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }
//
//    @Override
//    public void update(UserDTO userDTO, UserDTO newUserDTO) {//TODO сделать
//    }

    @Override
    public void update(UserDTO object) throws SQLException {

    }

    private ArrayList<UserDTO> getAll() throws SQLException {
        ArrayList<UserDTO> users = new ArrayList<>();
        String sql = "select id, username from users";
        PreparedStatement selectUsers = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        ResultSet rs = selectUsers.executeQuery();
        while (rs.next()) {
            users.add(new UserDTO(rs.getString("id"), rs.getString("username"), null));//не вытаскиваем пароли всех пользователей
        }
        connection.commit();
        rs.close();
        connection.setAutoCommit(true);
        return users;
    }

    @Override
    public ArrayList<UserDTO> getSome(Predicate<UserDTO> predicate) throws SQLException {
        ArrayList<UserDTO> some = new ArrayList<>();
        for (UserDTO userDTO : this.getAll()) {
            if (predicate.test(userDTO)) {
                some.add(userDTO);
            }
        }
        return some;
    }

}
