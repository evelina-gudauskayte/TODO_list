package DataBase;

import TODO.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import static TODO.User.hashPassword;

public class Authorization {
    private Connection connect(){
        Connection conn = null;

        String url = "jdbc:sqlite:/home/evelina/IdeaProjects/TODO_list/src/DataBase/users.db";
        try {
            conn = DriverManager.getConnection(url);
           // System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean authorize(User user){
        String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
        try (Connection connection = this.connect()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserPassword());
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()){
                count++;
            }if(count==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean authorize(String username, String password){
        try {
            String hashedPass = hashPassword(password);
            String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
            try (Connection connection = this.connect()){
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, hashedPass);
                ResultSet rs = statement.executeQuery();
                int count = 0;
                while (rs.next()){
                    count++;
                }if(count>0){
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static void main(String[] args){
        Authorization authorization = new Authorization();
        if(authorization.authorize("Alice", "password")){
            System.out.println("nice");
        }
    }
}
