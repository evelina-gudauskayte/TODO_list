package BL.Managers;

import BL.JointNote;
import BL.Note;
import BL.NoteDate;
import BL.User;
import DAL.NoteDAO;
import DAL.NoteDTO;
import Util.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class NoteManager {
    private final NoteDAO<NoteDTO> noteDAO;
    private final String userId;

    public NoteManager(NoteDAO<NoteDTO> noteDAO, User user) {
        this.noteDAO = noteDAO;
        this.userId = user.getId();
    }

    public void update(Note note, String newContent) {
        update(note, note.get_updated_note(newContent));
    }

    public void update(Note note, String newContent, NoteDate date) {
        update(note, note.get_updated_note(newContent,date));
    }

    public void update(Note note, NoteDate date) {
        update(note, note.get_updated_note(date));
    }

    private void update(Note oldNote, Note newNote) {
        if (oldNote.getId().equals(newNote.getId()))
            Logger.getInstance().log(() -> noteDAO.update(oldNote.getNoteDTO(userId), newNote.getNoteDTO(userId)), "Note updated");
    }

    public void add(Note note) {
        Logger.getInstance().log(() -> noteDAO.add(note.getNoteDTO(userId)), "Note added");
        System.out.println("Added");
    }

    public void addJointNote(JointNote note, ArrayList<String> ids) {
        Logger.getInstance().log(() -> noteDAO.addJointNote(note.getNoteDTO(userId), ids), "Joint note added");
    }

    public void deleteNote(Note note) {
        Logger.getInstance().log(() -> noteDAO.delete(note.getNoteDTO(userId)), "Note is deleted");
        System.out.println("Note is deleted.");
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            for (NoteDTO noteDTO : noteDAO.getAll()) {
                if (!noteDTO.IsJoint()) {
                    notes.add(new Note(noteDTO));
                } else {
                    notes.add(new JointNote(noteDTO, noteDAO.getIdsOfUsersOfJointNote(noteDTO)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public ArrayList<Note> getAllNotesOfUser() {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            for (NoteDTO noteDTO : noteDAO.getNotesOfUser(userId)) {
                if (!noteDTO.IsJoint()) {
                    notes.add(new Note(noteDTO));
                } else {
                    notes.add(new JointNote(noteDTO, noteDAO.getIdsOfUsersOfJointNote(noteDTO)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }


//    public static ArrayList<JointNote> getJointNotes(){
//        NoteDAO noteDAO = new NoteDAO();
//        ArrayList<JointNote> notes= new ArrayList<>();
//        try {
//            for(NoteDTO noteDTO: noteDAO.getSome(NoteDTO::IsJoint)){
//                notes.add(new JointNote(noteDTO, noteDAO.getIdsOfUsersOfJointNote(noteDTO)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return notes;
//    }
}
