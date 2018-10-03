package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteJDBCDriverConnection {
    public static void connect(){
        Connection conn = null;
        String url = "jdbc:sqlite:D:/sqlite/first.db";
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(conn!= null) conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
