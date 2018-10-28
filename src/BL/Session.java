package BL;

import DAL.Access;
import DAL.NoteDTO;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Session {
    private User currentUser;
    private ArrayList<Note> allNotes = new ArrayList<>();
    Access access = new Access();

    public Session(User user) { //обработку неправильных данных
        this.currentUser = user;
        updateAllNotes();
    }

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    private void updateAllNotes(){
        Access access = new Access();
        ArrayList<NoteDTO> noteDTOArrayList = access.getAllUsersNotes(currentUser.getId());
        for(NoteDTO noteDTO: noteDTOArrayList){
            if(noteDTO.getIsJoint()==0){
            allNotes.add(new Note(noteDTO));
            }else{
                allNotes.add(new JointNote(noteDTO, access.getUsersOfJointNote(noteDTO.getId())));
            }
        }
    }

    public boolean isAuthorized() {
        if (this.currentUser != null) {
            return true;
        }
        return false;
    }

    public void addNote(Note note) {
        access.addNote(currentUser.getId(), note.getContent(), note.getDate().getYear(), note.getDate().getMonth(), note.getDate().getDayOfMonth());
        allNotes.add(note);
    }

    public boolean deleteUser() {
        try {
            access.deleteUser(currentUser.getId());
            currentUser = null;
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public void logOut(){
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void deleteNote(int noteId){
        try {
            access.deleteNote(noteId);
            Iterator<Note> iterator = allNotes.iterator();
            while (iterator.hasNext()){
                Note n = iterator.next();
                if(n.getId()==noteId){
                    iterator.remove();
                    break;
                }
            }
            System.out.println("Note deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
