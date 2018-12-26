package BL.Managers;

import BL.JointNote;
import BL.Note;
import BL.NoteDate;
import BL.User;
import DAL.NoteDAO;
import DAL.NoteDTO;
import DAL.RealUserDAO;
import Util.BadContextException;
import Util.Logger;

import java.util.ArrayList;

public class NoteManagerImplementation implements NoteManager {
    private final NoteDAO<NoteDTO> noteDAO;
    User user = Context.getInstance().getCurrentUser();

    public NoteManagerImplementation(NoteDAO<NoteDTO> noteDAO) throws BadContextException {
        if (user == null) throw new BadContextException();
        this.noteDAO = noteDAO;
    }

    public void update(Note note, String newContent) {
        update(note, note.getUpdatedNote(newContent));
    }

    public void update(Note note, String newContent, NoteDate date) {
        update(note, note.getUpdatedNote(newContent, date));
    }

    public void update(Note note, NoteDate date) {
        update(note, note.getUpdatedNote(date));
    }

    public void update(Note oldNote, Note newNote) {
        if (oldNote.getId().equals(newNote.getId()))
            //Logger.getInstance().log(() -> noteDAO.update(oldNote.getNoteDTO(user.getId()), newNote.getNoteDTO(user.getId())), "Note updated");
            Logger.getInstance().log(() -> noteDAO.update(newNote.getNoteDTO(Context.getInstance().getCurrentUser().getId())), "Update note");
    }

    @Override
    public void update(Note note) {
        NoteDTO noteDTO = note.getNoteDTO(Context.getInstance().getCurrentUser().getId());
        Logger.getInstance().log(() -> noteDAO.update(noteDTO), "Update note");
    }

    public void add(Note note) {
        if (note instanceof JointNote) {
            addJointNote((JointNote) note);
        } else {
            Logger.getInstance().log(() -> noteDAO.add(note.getNoteDTO(user.getId())), "Add note");
        }
    }

    private void addJointNote(JointNote note) {
        Logger.getInstance().log(() -> noteDAO.addJointNote(note.getNoteDTO(user.getId()), note.getUsersIds()), "Add joint note");
    }

    public void deleteNote(Note note) {
        Logger.getInstance().log(() -> noteDAO.delete(note.getNoteDTO(user.getId())), "Delete note");
        System.out.println("Note is deleted.");
    }

    public Note getNote(String noteId) {
        NoteDTO note = Logger.getInstance().logWithReturn(() -> noteDAO.get(noteId), "Get note operation");
        return new Note(note);
    }



    @Override
    public String getNoteAuthor(Note note) {
        NoteDTO noteDTO = Logger.getInstance().logWithReturn(()->noteDAO.get(note.getId()), "get");
        UserManager userManager = new UserManagerImplementation(new RealUserDAO());
        String username = Logger.getInstance().logWithReturn(()-> userManager.getUsernameById(noteDTO.getUserId()), "Get username");
        return username;
    }

//    public ArrayList<Note> getAllNotes() {
//        ArrayList<NoteDTO> noteDTOS = Logger.getInstance().logWithReturn(noteDAO::getAll, "Get all notes");
//        return createNotesFromNotesDTO(noteDTOS);
//    }

    public ArrayList<Note> getAllNotesOfUser() {
        ArrayList<NoteDTO> noteDTOS = Logger.getInstance().logWithReturn(() -> noteDAO.getNotesOfUser(user.getId()), "Get all users notes");
        return createNotesFromNotesDTO(noteDTOS);
    }

    @Override
    public ArrayList<Note> getUnnoticed() {
        ArrayList<NoteDTO> list = Logger.getInstance().logWithReturn(()->noteDAO.getNotesToNotice(Context.getInstance().getCurrentUser().getId()), "Get unnoticed notes");
        return createNotesFromNotesDTO(list);
    }

    @Override
    public void setNoticed(JointNote note) {
        Logger.getInstance().log(()->noteDAO.setNoticed(note.getId(), Context.getInstance().getCurrentUser().getId()),"Update note(change status)");
    }

    private ArrayList<Note> createNotesFromNotesDTO(ArrayList<NoteDTO> noteDTOS) {
        ArrayList<Note> notes = new ArrayList<>();
        for (NoteDTO noteDTO : noteDTOS) {
            if (!noteDTO.IsJoint()) {
                notes.add(new Note(noteDTO));
            } else {
                ArrayList<String> ids = Logger.getInstance().logWithReturn(() -> noteDAO.getIdsOfUsersOfJointNote(noteDTO), "Get note's users");
                notes.add(new JointNote(noteDTO, ids));
            }
        }
        return notes;
    }
}
