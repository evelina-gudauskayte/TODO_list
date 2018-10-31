package DAL;

import BL.JointNote;
import BL.Note;

import java.util.function.Predicate;

public class NoteDTO {
    private String id;
    private String userId;
    private String content;
    private int year;
    private int month;
    private int day;
    private int isJoint = 0; //0=no, 1-yes
    private int isDone = 0;

    public NoteDTO(String id, String userId, String content, int year, int month, int day, int isJoint, int isDone) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.year = year;
        this.month = month;
        this.day = day;
        this.isJoint = isJoint;
        this.isDone = isDone;
    }

    public NoteDTO(Note note, String userId) {
        id = note.getId();
        content = note.getContent();
        year = note.getDate().getYear();
        month = note.getDate().getMonth();
        day = note.getDate().getDayOfMonth();
        this.userId = userId;
    }

    public NoteDTO(JointNote note, String userId) {
        id = note.getId();
        content = note.getContent();
        year = note.getDate().getYear();
        month = note.getDate().getMonth();
        day = note.getDate().getDayOfMonth();
        this.isJoint = 1;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public boolean IsJoint() {
        if (this.isJoint == 0) {
            return false;
        }
        return true;
    }

    public boolean IsDone() {
        if (this.isDone == 0) {
            return false;
        }
        return true;
    }


    private static Predicate<NoteDTO> JointNotesPredicate() {
        return NoteDTO::IsJoint;
    }

    private static Predicate<NoteDTO> DoneNotesPredicate() {
        return NoteDTO::IsDone;
    }
}
