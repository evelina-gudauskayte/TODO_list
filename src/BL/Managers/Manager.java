package BL.Managers;

import java.util.ArrayList;

public interface Manager<T> {
    void add(T object);

    //void update(T object, String... args);

    void delete(T object);

    T get(String id);

    ArrayList<T> getAll();

    //ArrayList<T> getSome(Predicate<Note> predicate);
}
