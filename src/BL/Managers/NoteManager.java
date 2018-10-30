package BL.Managers;

import BL.Note;
import BL.NoteDate;
import DAL.NoteDAO;

public class NoteManager {

    private NoteDAO noteDAO = new NoteDAO();
    public void update(Note note, String newContent) {
    }

    public void update(Note note, String newContent, NoteDate date) {

    }

    public void update(Note note, NoteDate date) throws IllegalArgumentException  {

    }

    public void deleteNote(Note note) {

    }

}
