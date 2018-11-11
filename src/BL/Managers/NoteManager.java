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

    public NoteManager(NoteDAO<NoteDTO> noteDAO) {
        this.noteDAO = noteDAO;
    }

    public void update(Note note, String newContent) {
    }

    public void update(Note note, String newContent, NoteDate date) {

    }

    public void update(Note note, NoteDate date) throws IllegalArgumentException {

    }

    public void add(Note note, User user) {
        Logger.getInstance().log(() -> noteDAO.add(note.getNoteDTO(user.getId())), "Note added");
        System.out.println("Added");
    }

    public void addJointNote(JointNote note, User user, ArrayList<String> ids) {
        Logger.getInstance().log(() -> noteDAO.addJointNote(note.getNoteDTO(user.getId()), ids), "Joint note added");
    }

    public void deleteNote(Note note, User user) {
        Logger.getInstance().log(() -> noteDAO.delete(note.getNoteDTO(user.getId())), "Note is deleted");
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

    public ArrayList<Note> getAllNotesOfUser(User user) {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            for (NoteDTO noteDTO : noteDAO.getNotesOfUser(user.getId())) {
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
