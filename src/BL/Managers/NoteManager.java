package BL.Managers;

import BL.Note;
import BL.User;

import java.util.ArrayList;

public interface NoteManager {
    void update(Note oldNote, Note newNote);

    void add(Note note);

    void deleteNote(Note note);

    Note getNote(String noteId);

    ArrayList<Note> getAllNotes();

    ArrayList<Note> getAllNotesOfUser();
}
