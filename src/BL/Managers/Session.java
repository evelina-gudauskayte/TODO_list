package BL.Managers;

import BL.Note;
import BL.User;
import DAL.Access;
import DAL.RealNoteDAO;
import DAL.RealUserDAO;

import java.util.ArrayList;

public class Session {
    private User currentUser;
    private NoteManager noteManager;
    private UserManager userManager;
    private ArrayList<Note> allNotes = new ArrayList<>();

    public Session(String username, String password) {
        this.currentUser = LogInManager.authorizeUser(username, password);
        if(isAuthorized()){
            noteManager = new NoteManager(new RealNoteDAO(), currentUser);
            userManager = new UserManager(new RealUserDAO(), currentUser);
        }
        updateAllNotes();
    }

    public NoteManager getNoteManager() {
        return noteManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public String getUserId(){
        return currentUser.getId();
    }

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    private void updateAllNotes() {
        allNotes = noteManager.getAllNotesOfUser();
    }

    public boolean isAuthorized() {
        if (this.currentUser != null) {
            return true;
        }
        return false;
    }
}
