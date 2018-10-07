package DataBase;

import TODO.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUser {
    private Connection connect(){
        Connection conn = null;

        String url = "jdbc:sqlite:/home/evelina/IdeaProjects/TODO_list/src/DataBase/users.db";
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void addUser(User user){
        int id = user.getId();
        String username = user.getUserName();
        String password = user.getUserPassword();

        String sql = "INSERT INTO users(id,username,password) VALUES(?,?,?)";

        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            System.out.println("added");
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
