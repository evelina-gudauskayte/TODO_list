package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class NoteDAO implements DAO<NoteDTO> {

    private static Connection connection = Access.getConnection();

    public void addJointNote(NoteDTO noteDTO, ArrayList<String> usersIds) throws SQLException {
        this.add(noteDTO);
        connection.setAutoCommit(false);
        String sql = "INSERT INTO jointNotes(noteId, userId) VALUES(?,?)";
        String update = "update notes set isJoint = 1 where id = ?";
        PreparedStatement updateStatement = connection.prepareStatement(update);
        updateStatement.setString(1, noteDTO.getId());
        updateStatement.executeUpdate();
        connection.commit();
        PreparedStatement insertStatement = connection.prepareStatement(sql);
        for (String userId : usersIds) {
            insertStatement.setString(1, noteDTO.getId());
            insertStatement.setString(2, userId);
            insertStatement.executeUpdate();
        }
        connection.setAutoCommit(true);
    }

    public ArrayList<String> getIdsOfUsersOfJointNote(NoteDTO noteDTO) throws SQLException {
        String sql = "SELECT userId FROM jointNotes WHERE  noteId = ?";
        ArrayList<String> usersIds = new ArrayList<>();
        PreparedStatement selectUsers = connection.prepareStatement(sql);
        selectUsers.setString(1, noteDTO.getId());
        ResultSet rs = selectUsers.executeQuery();
        while (rs.next()) {
            usersIds.add(rs.getString("userId"));
        }
        return usersIds;
    }

    @Override
    public void add(NoteDTO noteDTO) throws SQLException {
        String sql = "INSERT INTO notes(id,userId,content,year, month, day) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        preparedStatement.setString(1, noteDTO.getId());
        preparedStatement.setString(2, noteDTO.getUserId());
        preparedStatement.setString(3, noteDTO.getContent());
        preparedStatement.setString(4, Integer.toString(noteDTO.getYear()));
        preparedStatement.setString(5, Integer.toString(noteDTO.getMonth()));
        preparedStatement.setString(6, Integer.toString(noteDTO.getDay()));
        preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public NoteDTO get(String id) throws SQLException {
        String sql = "SELECT userId, content, isJoint, year, month, day, isDone FROM notes WHERE  id = ?";
        PreparedStatement selectNote = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        selectNote.setString(1, id);
        ResultSet rs = selectNote.executeQuery();
        NoteDTO noteDTO = new NoteDTO(id, rs.getString("userId"), rs.getString("content"), rs.getInt("year"),
                rs.getInt("month"), rs.getInt("day"), rs.getInt("isJoint"), rs.getInt("isDone"));
        connection.commit();
        rs.close();
        connection.setAutoCommit(true);
        return noteDTO;
    }

    @Override
    public void update(NoteDTO noteDTO, NoteDTO newNoteDTO) throws SQLException {
        String sql = "UPDATE notes SET content = ?, year = ?, month = ?, day = ?, isJoint = ?, isDone = ? WHERE id = ?";
        PreparedStatement update = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        update.setString(1, newNoteDTO.getContent());
        update.setString(2, String.valueOf(newNoteDTO.getYear()));
        update.setString(3, String.valueOf(newNoteDTO.getMonth()));
        update.setString(4, String.valueOf(newNoteDTO.getDay()));
        if(newNoteDTO.IsJoint()){
            update.setString(5,"1");
        }else {
            update.setString(5,"0");
        }
        if(newNoteDTO.IsDone()){
            update.setString(6,"1");
        }else {
            update.setString(6,"0");
        }
        update.setString(7, noteDTO.getId());
        update.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public void delete(NoteDTO noteDTO) throws SQLException {
        String sql = "DELETE FROM notes WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        preparedStatement.setString(1, noteDTO.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        sql = "DELETE FROM jointNotes WHERE noteId = ?";
        PreparedStatement deepDelete = connection.prepareStatement(sql);
        preparedStatement.setString(1, noteDTO.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public ArrayList<NoteDTO> getAll() throws SQLException {
        ArrayList<NoteDTO> allNotes = new ArrayList<>();
        String sql = "SELECT id, userId, content, isJoint, year, month, day, isDone FROM notes";
        PreparedStatement selectNotes = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        ResultSet rs = selectNotes.executeQuery();
        while (rs.next()) {
            allNotes.add(new NoteDTO(rs.getString("id"),
                    rs.getString("userId"),
                    rs.getString("content"),
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("day"),
                    rs.getInt("isJoint"),
                    rs.getInt("isDone")));
        }
        connection.commit();
        rs.close();
        connection.setAutoCommit(true);
        return allNotes;
    }

    @Override
    public ArrayList<NoteDTO> getSome(Predicate<NoteDTO> predicate) throws SQLException {
        ArrayList<NoteDTO> some = new ArrayList<>();
        for (NoteDTO noteDTO : this.getAll()) {
            if (predicate.test(noteDTO)) {
                some.add(noteDTO);
            }
        }
        return some;
    }

    public ArrayList<NoteDTO> getNotesOfUser(UserDTO user) throws SQLException {
        ArrayList<NoteDTO> some = new ArrayList<>();
        for (NoteDTO noteDTO : this.getAll()) {
            if (noteDTO.getUserId() == user.getId()){
                some.add(noteDTO);
            }
        }
        return some;
    }

}
