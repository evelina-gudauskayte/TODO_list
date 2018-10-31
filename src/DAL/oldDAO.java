package DAL;

import java.sql.*;
import java.util.ArrayList;

public class oldDAO {
    private static Connection connection = Access.connect();


    public static ArrayList<NoteDTO> getAllUsersNotes(String userId) {
        ArrayList<NoteDTO> allNotes = new ArrayList<>();
        String sql = "select id, content, year, month, day, isJoint, isDone from notes where userId = ?";
       // Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotes.add(new NoteDTO(rs.getString("id"),
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

    public static ArrayList<String> getUsersIdsOfJointNote(String noteId) {
        String sql = "SELECT userId FROM jointNotes WHERE  noteId = ?";
        ArrayList<String> usersIds = new ArrayList<>();
        try (PreparedStatement selectUsers = connection.prepareStatement(sql)) {
            selectUsers.setString(1, noteId);
            ResultSet rs = selectUsers.executeQuery();
            while (rs.next()) {
                usersIds.add(rs.getString("userId"));
            }
            return usersIds;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersIds;
    }

    /*public static ArrayList<String> getAllUsernames() {
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



    public static ArrayList<Integer> getJointNotesOfUser(int userId) {
        ArrayList<Integer> notes = new ArrayList<>();
        String sql = "SELECT noteId FROM jointNotes WHERE  userId = ? AND isNoticed = 1";
        if (selectNotesFromJointNotes(userId, notes, sql)) return notes;
        return notes;
    }

    public static ArrayList<Integer> getNotificationsOfUser(int userId) {
        ArrayList<Integer> notifications = new ArrayList<>();
        String sql = "SELECT noteId FROM jointNotes WHERE  userId = ? AND isNoticed = 0";
        if (selectNotesFromJointNotes(userId, notifications, sql)) return notifications;
        return notifications;
    }

    private static boolean selectNotesFromJointNotes(int userId, ArrayList<Integer> notes, String sql) {
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


    public static void main(String[] args) {
    }*/
}
