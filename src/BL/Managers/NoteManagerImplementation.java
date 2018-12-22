package BL.Managers;

import BL.JointNote;
import BL.Note;
import BL.NoteDate;
import BL.User;
import DAL.NoteDAO;
import DAL.NoteDTO;
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
            Logger.getInstance().log(() -> noteDAO.update(newNote.getNoteDTO(Context.getInstance().getCurrentUser().getId())), "Note updated");
    }

    @Override
    public void update(Note note) {
        Logger.getInstance().log(()->noteDAO.update(note.getNoteDTO(Context.getInstance().getCurrentUser().getId())), "updated");
    }


    public void add(Note note) {
        if (note instanceof JointNote) {
            addJointNote((JointNote) note);
        } else {
            Logger.getInstance().log(() -> noteDAO.add(note.getNoteDTO(user.getId())), "Note added");
        }
    }

    private void addJointNote(JointNote note) {
        Logger.getInstance().log(() -> noteDAO.addJointNote(note.getNoteDTO(user.getId()), note.getUsersIds()), "Joint note added");
    }

    public void deleteNote(Note note) {
        Logger.getInstance().log(() -> noteDAO.delete(note.getNoteDTO(user.getId())), "Note is deleted");
        System.out.println("Note is deleted.");
    }

    public Note getNote(String noteId) {
        NoteDTO note = Logger.getInstance().logWithReturn(() -> noteDAO.get(noteId), "Get note operation");
        return new Note(note);
    }

//    public ArrayList<Note> getAllNotes() {
//        ArrayList<NoteDTO> noteDTOS = Logger.getInstance().logWithReturn(noteDAO::getAll, "Get all notes");
//        return createNotesFromNotesDTO(noteDTOS);
//    }

    public ArrayList<Note> getAllNotesOfUser() {
        ArrayList<NoteDTO> noteDTOS = Logger.getInstance().logWithReturn(() -> noteDAO.getNotesOfUser(user.getId()), "Get all users notes");
        return createNotesFromNotesDTO(noteDTOS);
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
