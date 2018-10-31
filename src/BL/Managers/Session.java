package BL.Managers;

import BL.JointNote;
import BL.Note;
import BL.User;
import DAL.oldDAO;
import DAL.NoteDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Session {
    private User currentUser;
    private ArrayList<Note> allNotes = new ArrayList<>();

    public Session(String username, String password) {
        this.currentUser = LogInManager.authorizeUser(username,password);
        updateAllNotes();
    }

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    private void updateAllNotes() {
        ArrayList<NoteDTO> noteDTOArrayList = oldDAO.getAllUsersNotes(currentUser.getId());
        for (NoteDTO noteDTO : noteDTOArrayList) {
            if (noteDTO.getIsJoint() == 0) {
                allNotes.add(new Note(noteDTO));
            } else {
                allNotes.add(new JointNote(noteDTO, oldDAO.getUsersIdsOfJointNote(noteDTO.getId())));
            }
        }
    }

    public String getUsernameById(String id) {
        return oldDAO.getUser(id).getName();
    }

    public boolean isAuthorized() {
        if (this.currentUser != null) {
            return true;
        }
        return false;
    }

    public void addNote(Note note) {
        oldDAO.addNote(new NoteDTO(note, currentUser.getId()));
        allNotes.add(note);
        System.out.println("Note added to base");
    }

    public void addJointNote(JointNote note) {
        oldDAO.addJointNote(new NoteDTO((Note)note, currentUser.getId()), note.getUsers());
        allNotes.add(note);
        System.out.println("Note added to base");
    }

    public boolean deleteUser() {
        try {
            oldDAO.deleteUser(currentUser.getId());
            currentUser = null;
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public void logOut() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void deleteNote(String noteId) {
        try {
            oldDAO.deleteNote(noteId);
            Iterator<Note> iterator = allNotes.iterator();
            while (iterator.hasNext()) {
                Note n = iterator.next();
                if (n.getId().equals(noteId)) {
                    iterator.remove();
                    System.out.println("Note deleted");
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        //LogInManager.addNewUser("Bob", "password");
        //LogInManager.addNewUser("Alice", "password");
        User Bob = LogInManager.authorizeUser("Bob", "password");
        Session session = new Session(Bob);
        ArrayList<String> ids = new ArrayList<>();
        ids.add("463fa061-5d91-4669-96f6-7c1847eb3768");
        ids.add("8448b7a4-67fe-45a1-bdd0-4de00ef6d234");
        session.addJointNote(new JointNote("Joint note for Alice and Masha", ids));
    }
}
