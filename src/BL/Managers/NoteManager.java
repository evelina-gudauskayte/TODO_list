package BL.Managers;

import BL.Note;
import BL.NoteDate;
import BL.User;
import DAL.NoteDAO;
import DAL.NoteDTO;

import java.sql.SQLException;

public class NoteManager {
    Note note;
    User user;

    public NoteManager(Note note, User user) {
        this.note = note;
        this.user = user;
    }

    public void update(Note note, String newContent) {
    }

    public void update(Note note, String newContent, NoteDate date) {

    }

    public void update(Note note, NoteDate date) throws IllegalArgumentException {

    }

    public void deleteNote(Note note) {
        NoteDAO noteDAO = new NoteDAO();
        try {
            noteDAO.delete(new NoteDTO(note, user.getId()));
            System.out.println("Note is deleted.");
        } catch (SQLException e) {
            System.out.println("NOT deleted.");
            e.printStackTrace();
        }
    }

}
