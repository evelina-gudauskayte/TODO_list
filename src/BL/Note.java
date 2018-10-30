package BL;

import DAL.NoteDTO;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Note implements Comparable<Note> {

    static private AtomicInteger nextID = new AtomicInteger(0);

    private String content;
    private NoteDate date;
    private int noteStatus = 0;
    private String id = String.valueOf(UUID.randomUUID());
    private boolean isPersonal = true;

    public Note(String content) {
        this(new NoteDate(), content);
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
        this.date = new NoteDate(noteDTO.getYear(),noteDTO.getMonth(), noteDTO.getDay());
    }

    @Override
    public int compareTo(Note note) {
        return this.getId().compareTo(note.getId());
    }

    @Override
    public String toString() {
        String status;
        if (noteStatus > 0) {
            status = "done";
        } else if (noteStatus < 0) {
            status = "not started";
        } else {
            status = "in the process";
        }
        return ("Deadline at: "
                + date
                + "\n    Id:"
                + id
                + "\n     note: "
                + this.getContent())
                + "\n      Note is "
                + status;
    }

    public void markAsDone() {
        noteStatus = 1;
    }

    public void addContent(String content) {
        this.content = this.getContent() + "\n" + content;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public NoteDate getDate() {
        return date;
    }

   /* public User getCreator() {
        return creator;
    }*/

    public boolean isPersonal() {
        return isPersonal;
    }
}
