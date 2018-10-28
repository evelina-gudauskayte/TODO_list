package BL;

import DAL.NoteDTO;

import java.util.concurrent.atomic.AtomicInteger;

public class Note implements Comparable<Note> {

    static private AtomicInteger nextID = new AtomicInteger(0);

    private String content;
    private NoteDate date;
    private int noteStatus = -1;
    private int id = nextID.incrementAndGet();
    private boolean isPersonal = true;
    //private User creator; //or we can hold an id of user

    public Note(String content, User creator) {
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
        //this.creator = creator;
        this.date = new NoteDate(noteDTO.getYear(),noteDTO.getMonth(), noteDTO.getDay());
    }

    @Override
    public int compareTo(Note note) {
        //compare date and content
      /*  if(this.date.compareTo(note.getDate())==0){
            if(this.content.compareTo(note.getcontent())==0){
                return 0;
            }else{
                return this.content.compareTo(note.getcontent());
            }
        }else{
            return (this.date.compareTo(note.getDate()));
        }*/
        return Integer.compare(this.getId(), note.getId());
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

    public void markAsInProgress() {
        noteStatus = 0;
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

    public int getId() {
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
