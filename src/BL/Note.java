package BL;

import DAL.NoteDTO;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Note implements Comparable<Note>, Cloneable {

    static private AtomicInteger nextID = new AtomicInteger(0);

    private String content;
    private NoteDate date;
    private boolean isDone = false;
    private String id = String.valueOf(UUID.randomUUID());
    private boolean isPersonal = true;

    public Note(String content) {
        this(new NoteDate(), content);
    }

    private Note(String content, NoteDate date, boolean isDone, String id, boolean isPersonal) {
        this.content = content;
        this.date = date;
        this.isDone = isDone;
        this.id = id;
        this.isPersonal = isPersonal;
    }

    public Note(int year, int month, int day, String content) {
        this(new NoteDate(year, month, day), content);
    }

    public Note(NoteDate date, String content) {
        //this.creator = creator;
        this.date = date;
        this.content = content;
    }

    public Note(NoteDTO noteDTO) {
        this.id = noteDTO.getId();
        this.content = noteDTO.getContent();
        this.date = new NoteDate(noteDTO.getYear(), noteDTO.getMonth(), noteDTO.getDay());
    }

    @Override
    public int compareTo(Note note) {
        return this.getDate().compareTo(note.getDate());
    }

    @Override
    public String toString() {
        String status;
        if (isDone) {
            status = "done";
        } else {
            status = "not done";
        }
        int end = 10;
        if(getContent().length() < 10){
            end = getContent().length();
        }
        return (date
                + ": "
                + this.getContent().substring(0,end))
                + "...";
    }

    public Note clone() {
        try {
            return (Note) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new InternalError();
        }
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public void addContent(String content) {
        this.content = this.getContent() + "\n" + content;
    }

    public Note get_updated_note(String content){
        return new Note(content, this.date, this.isDone, this.id, this.isPersonal);
    }
    public Note get_updated_note(NoteDate date){
        return new Note(this.content, date, this.isDone, this.id, this.isPersonal);
    }
    public Note get_updated_note(String content, NoteDate date){
        return new Note(content, date, this.isDone, this.id, this.isPersonal);
    }

/*    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(NoteDate date) {
        this.date = date;
    }*/

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public NoteDate getDate() {
        return date;
    }

    public NoteDTO getNoteDTO(String userId) {
        int done = this.isDone ? 1 : 0;
        return new NoteDTO(this.id, userId, this.content, this.getDate().getYear(), this.getDate().getMonth(), this.getDate().getDayOfMonth(), 0, done);
    }

    public boolean isPersonal() {
        return isPersonal;
    }
}
