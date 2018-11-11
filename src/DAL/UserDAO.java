package DAL;

import java.sql.SQLException;

public interface UserDAO<T> extends DAO<T> {

    String getUserId(String username) throws SQLException;

    T get(String username, String password) throws SQLException;
}
