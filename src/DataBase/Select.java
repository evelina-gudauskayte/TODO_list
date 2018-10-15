package DataBase;

import java.sql.*;

public class Select {
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
    public void PrintUser( String username,  String password){
        String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
        try (Connection connection = this.connect();
             PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setString(1, username);
             statement.setString(2,password);

             ResultSet rs = statement.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("id")+"\t"+
                        rs.getString("username")+"\t"+
                        rs.getString("password")+"\t");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void selectAll(){
        String sql = "SELECT id, username, password FROM users";
        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)){
            while (rs.next()){
                System.out.println(rs.getInt("id")+"\t"+
                                rs.getString("username")+"\t"+
                                rs.getString("password")+"\t");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        Select select = new Select();
        select.selectAll();
    }
}
