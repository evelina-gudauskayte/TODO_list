package GUI;

import BL.Note;
import DAL.Access;
import DAL.NoteDTO;
import DAL.RealNoteDAO;
import DAL.RealUserDAO;
import BL.Managers.*;
import Util.BadContextException;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws BadContextException {
        Note note = new Note("note");
        note.changeStatus();
        NoteDTO noteDTO = note.getNoteDTO("user");
        System.out.println(note.isDone());
        System.out.println(noteDTO.IsDone());
    }
}
