package DAL;

import BL.User;

import java.sql.*;
import java.util.ArrayList;

public class Access {
    private static final String pathToData = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\Data\\todolistData.db";

    private Connection connect() {
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

    public void addUser(String username, String password) throws SQLException {
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        Connection connection = this.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
    }

    public void addNote(int userId, String content, int year, int month, int day) {
        String sql = "INSERT INTO notes(userId,content,year, month, day) VALUES(?,?,?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Integer.toString(userId));
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, Integer.toString(year));
            preparedStatement.setString(4, Integer.toString(month));
            preparedStatement.setString(5, Integer.toString(day));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addJointNote(int noteId, ArrayList<Integer> users) {
        String sql = "INSERT INTO jointNotes(noteId, userId) VALUES(?,?)";
        String update = "update notes set isJoint = 1 where id = ?";
        try (Connection connection = this.connect();
             PreparedStatement updateStatement = connection.prepareStatement(update)) {
            updateStatement.setString(1, Integer.toString(noteId));
            updateStatement.executeUpdate();
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            for (Integer user : users) {
                insertStatement.setString(1, Integer.toString(noteId));
                insertStatement.setString(2, Integer.toString(user));
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserToJointNote(int noteId, int userId) {
        String sql = "INSERT INTO jointNotes(noteId, userId) VALUES(?,?)";
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Integer.toString(noteId));
            preparedStatement.setString(2, Integer.toString(userId));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDTO getUser(String username, String password) {
        String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
        try (Connection connection = this.connect();
             PreparedStatement selectUser = connection.prepareStatement(sql)) {
            selectUser.setString(1, username);
            selectUser.setString(2, password);
            ResultSet rs = selectUser.executeQuery();
            return new UserDTO(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUserId(String username) {
        String sql = "SELECT id FROM users WHERE username = ? ";
        try (Connection connection = this.connect();
             PreparedStatement selectUser = connection.prepareStatement(sql)) {
            selectUser.setString(1, username);
            ResultSet rs = selectUser.executeQuery();
            int ans = rs.getInt("id") ;
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public UserDTO getUser(int id) {
        String sql = "SELECT username, password FROM users WHERE id = ?";
        try (Connection connection = this.connect();
             PreparedStatement selectUser = connection.prepareStatement(sql)) {
            selectUser.setString(1, Integer.toString(id));
            ResultSet rs = selectUser.executeQuery();
            return new UserDTO(id, rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public NoteDTO getNote(int id) {
        String sql = "SELECT userId, content, isJoint, year, month, day, isDone FROM notes WHERE  id = ?";
        try (Connection connection = connect();
             PreparedStatement selectNote = connection.prepareStatement(sql)) {
            selectNote.setString(1, Integer.toString(id));
            ResultSet rs = selectNote.executeQuery();
            return new NoteDTO(id,
                    rs.getInt("userId"),
                    rs.getString("content"),
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("day"),
                    rs.getInt("isJoint"),
                    rs.getInt("isDone")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getAllUsernames() {
        String sql = "select username from users";
        Connection connection = connect();
        ArrayList<String> names = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                names.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public ArrayList<Integer> getUsersOfJointNote(int noteId) {
        String sql = "SELECT userId FROM jointNotes WHERE  noteId = ?";
        ArrayList<Integer> users = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement selectUsers = connection.prepareStatement(sql)) {
            selectUsers.setString(1, Integer.toString(noteId));
            ResultSet rs = selectUsers.executeQuery();
            while (rs.next()) {
                users.add(rs.getInt("userId"));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<NoteDTO> getAllUsersNotes(int userId) {
        ArrayList<NoteDTO> allNotes = new ArrayList<>();
        String sql = "select id, content, year, month, day, isJoint, isDone from notes where userId = ?";
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Integer.toString(userId));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotes.add(new NoteDTO(rs.getInt("id"),
                        userId,
                        rs.getString("content"),
                        rs.getInt("year"),
                        rs.getInt("month"),
                        rs.getInt("day"),
                        rs.getInt("isJoint"),
                        rs.getInt("isDone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allNotes;
    }

    public ArrayList<Integer> getJointNotesOfUser(int userId) {
        ArrayList<Integer> notes = new ArrayList<>();
        String sql = "SELECT noteId FROM jointNotes WHERE  userId = ? AND isNoticed = 1";
        if (selectNotesFromJointNotes(userId, notes, sql)) return notes;
        return notes;
    }

    public ArrayList<Integer> getNotificationsOfUser(int userId) {
        ArrayList<Integer> notifications = new ArrayList<>();
        String sql = "SELECT noteId FROM jointNotes WHERE  userId = ? AND isNoticed = 0";
        if (selectNotesFromJointNotes(userId, notifications, sql)) return notifications;
        return notifications;
    }

    private boolean selectNotesFromJointNotes(int userId, ArrayList<Integer> notes, String sql) {
        try (Connection connection = connect();
             PreparedStatement selectUsers = connection.prepareStatement(sql)) {
            selectUsers.setString(1, Integer.toString(userId));
            ResultSet rs = selectUsers.executeQuery();
            while (rs.next()) {
                notes.add(rs.getInt("noteId"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Integer.toString(userId));
        preparedStatement.executeUpdate();
    }

    public void deleteNote(int noteId) throws SQLException {
        String sql = "DELETE FROM notes WHERE id = ?";
        Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Integer.toString(noteId));
        preparedStatement.executeUpdate();
    }

    public static void main(String[] args) {
    }
}
