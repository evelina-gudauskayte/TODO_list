package BL.Managers;

import BL.JointNote;
import BL.Note;
import BL.User;
import DAL.Access;
import DAL.NoteDAO;

import java.util.ArrayList;

public class Session {
    private User currentUser;
    private NoteManager noteManager = new NoteManager(new NoteDAO());
    private UserManager userManager = new UserManager();
    private ArrayList<Note> allNotes = new ArrayList<>();

    public Session(String username, String password) {
        this.currentUser = LogInManager.authorizeUser(username, password);
        updateAllNotes();
    }

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    private void updateAllNotes() {
        allNotes = noteManager.getAllNotesofUser(currentUser);
    }

    public boolean isAuthorized() {
        if (this.currentUser != null) {
            return true;
        }
        return false;
    }

    public void addNote(Note note) {
        noteManager.add(note, currentUser);
    }

    public void deleteNote(Note note) {
        noteManager.deleteNote(note, currentUser);
    }

    public void addJointNote(JointNote jointNote) {
        noteManager.addJointNote(jointNote, currentUser, jointNote.getUsers());
    }

    public static void main(String args[]) {
        Access access = new Access();
        //LogInManager.addNewUser("Bob", "password");
        //LogInManager.addNewUser("Alice", "password");
        Session session = new Session("Bob", "password");
        ArrayList<String> ids = new ArrayList<>();
        ids.add("463fa061-5d91-4669-96f6-7c1847eb3768");
        ids.add("8448b7a4-67fe-45a1-bdd0-4de00ef6d234");
        session.addJointNote(new JointNote("Second Joint Note for BITCHES", ids));
    }
}
