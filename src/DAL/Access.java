package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Access {
    private static final String pathToData = System.getProperty("user.dir")+"\\src\\Data\\todolistData.db";
    private Connection connect(){
        Connection conn = null;

        String url = pathToData;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void addUser(String username, String password){
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        try (Connection connection = this.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);
                System.out.println("User added");
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addNote(int userId, String content){
        String sql = "INSERT INTO notes(userId,content) VALUES(?,?)";
        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, Integer.toString(userId));
            preparedStatement.setString(2, content);
            preparedStatement.executeUpdate();
            System.out.println("Note added");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addJointNote(int userId, String content, ArrayList<Integer> users){
        String sql = "INSERT INTO notes(userId,content, idJoint) VALUES(?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, Integer.toString(userId));
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, "1");
            preparedStatement.executeUpdate();
            /*for(int i = 0; i < users.size(); i++){
                sql = "INSERT INTO  jointNotes"
                preparedStatement = connection.prepareStatement("")

            }*/ //found id and add all users to table
            System.out.println("Note added");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    }
}
