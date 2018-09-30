import java.util.Calendar;
import java.util.GregorianCalendar;

public class Note implements Comparable<Note> {

    static public String RED = "\u001B[1;31m"; //bright red
    static public String GREEN = "\u001B[1;32m";
    static public String YELLOW = "\u001B[1;33m";
    static public String DEFAULT = "\u001B[39;49m";
    static private int nextID = 0;

    private String content;
    private NoteDate date;
    private String tag = Note.RED;
    private int noteStatus = -1;
    private int id = getNextID();
    private boolean personal = true;

    private int getNextID() {
        nextID++;
        return nextID;
    }

    public Note(String content) {
        this(new NoteDate(), content);
    }

    public Note(int year, int month, int day, String content) {
        this(new NoteDate(year, month, day), content);
    }

    public Note(NoteDate date, String content) {
        this.date = date;
        this.content = content;
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
                +date
                + " Id:"
                + id
                + " \nnote: "
                + this.tag
                + " "
                + this.getContent())
                + Note.DEFAULT
                +"\n Note is "
                +status;
    }

    public void markAsDone() {
        this.tag = Note.GREEN;
        noteStatus = 1;
    }

    public void markAsInProgress() {
        this.tag = Note.YELLOW;
        noteStatus = 0;
    }

    protected void addContent(String content) {
        this.content = this.getContent() + "\n" + content;
    }

    protected void changeContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getContentWithColor() {
        return "ID:" + id + " " + this.tag + content + Note.DEFAULT;
    }

    public int getId() {
        return id;
    }

    public NoteDate getDate() {
        return date;
    }
}
