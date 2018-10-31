package BL.Managers;

import BL.JointNote;
import BL.Note;
import BL.NoteDate;
import BL.User;
import DAL.DAO;
import DAL.NoteDAO;
import DAL.NoteDTO;
import DAL.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class NoteManager {
    private final DAO<NoteDTO> noteDAO;

    public NoteManager(DAO<NoteDTO> dao){
        noteDAO = dao;
    }

    public void update(Note note, String newContent) {
    }

    public void update(Note note, String newContent, NoteDate date) {

    }

    public void update(Note note, NoteDate date) throws IllegalArgumentException {

    }

    public void add(Note note, User user) {
        NoteDAO noteDAO = new NoteDAO();
        try {
            noteDAO.add(new NoteDTO(note, user.getId()));
            System.out.println("Added");
        } catch (SQLException e) {
            System.out.println("NOT added");
            e.printStackTrace();
        }

    }

    public void addJointNote(JointNote note, User user, ArrayList<String> ids) {
        NoteDAO noteDAO = new NoteDAO(); ///сделать приватно
        try {
            noteDAO.addJointNote(new NoteDTO(note, user.getId()), ids);
            System.out.println("Added"); //logging
        } catch (SQLException e) {
            System.out.println("NOT added");
            e.printStackTrace();
        }

    }

    public void deleteNote(Note note, User user) {
        NoteDAO noteDAO = new NoteDAO();
        try {
            noteDAO.delete(new NoteDTO(note, user.getId()));
            System.out.println("Note is deleted.");
        } catch (SQLException e) {
            System.out.println("NOT deleted.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Note> getAllNotes() {
        NoteDAO noteDAO = new NoteDAO();
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

    public ArrayList<Note> getAllNotesofUser(User user) {
        NoteDAO noteDAO = new NoteDAO();
        ArrayList<Note> notes = new ArrayList<>();
        try {
            for (NoteDTO noteDTO : noteDAO.getNotesOfUser(new UserDTO(user))) {
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
