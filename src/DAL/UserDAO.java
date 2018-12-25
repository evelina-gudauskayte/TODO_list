package DAL;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO<T> extends DAO<T> {

    String getUserId(String username) throws SQLException;

    ArrayList<String> getAllUserNames() throws SQLException;

    T get(String username, String password) throws SQLException;

    String getUsernameById(String id) throws  SQLException;
}
