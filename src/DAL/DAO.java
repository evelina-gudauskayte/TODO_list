package DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public interface DAO<T> {
    void add(T object) throws SQLException;

    void update(T object, T newObject) throws SQLException;//сравнивать id сделать функцию

    void delete(T object) throws SQLException;

    T get(String id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    ArrayList<T> getSome(Predicate<T> predicate) throws SQLException;
}