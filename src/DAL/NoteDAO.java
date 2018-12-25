package DAL;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NoteDAO<T> extends DAO<T> {

    void addJointNote(T noteDTO, ArrayList<String> usersIds) throws SQLException;

    ArrayList<String> getIdsOfUsersOfJointNote(T noteDTO) throws SQLException;

    ArrayList<NoteDTO> getNotesOfUser(String userId) throws SQLException;

    ArrayList<NoteDTO> getNotesToNotice(String userId) throws SQLException;

    void setNoticed(String noteId, String userId) throws SQLException;

}
