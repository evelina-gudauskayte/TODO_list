package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {

    private static final String pathToData = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/Data/todolistData.db";

    public static Connection connect() {
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

    public static void main(String argd[]){
        Connection connection = ConnectToDB.connect();
    }
}
