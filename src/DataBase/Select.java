package DataBase;

import java.sql.*;

public class Select {
    private Connection connect(){
        Connection conn = null;
        String url = "jdbc:sqlite:D:/sqlite/first.db";
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void selectAll(){
        String sql = "SELECT id, name, password FROM users";
        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)){
            while (rs.next()){
                System.out.println(rs.getInt("id")+"\t"+
                                rs.getString("name")+"\t"+
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
