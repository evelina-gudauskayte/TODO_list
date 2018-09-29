import java.util.Calendar;
import java.util.GregorianCalendar;

public class Note implements Comparable<Note> {

    static public String RED = "\u001B[1;31m"; //bright red
    static public String GREEN = "\u001B[1;32m";
    static public String DEFAULT = "\u001B[39;49m";
    static private int nextID = 0;

    private String memo;
    private NoteDate date;
    private String tag = Note.RED;
    private int id = getNextID();

    private int getNextID(){
        nextID++;
        return nextID;
    }

    public Note(int year, int month, int date, String memo) {
        this.date = new NoteDate(year, month, date);
        this.memo = memo;
    }

    public Note(NoteDate date, String memo){
        this.date=date;
        this.memo=memo;
    }

    @Override
    public int compareTo(Note note) {
        int ans = this.date.compareTo(note.getDate());
        return ans;
    }

    @Override
    public String toString() {
        return ("Deadline at: "
                + this.getDate().getDayOfMonth()
                + "."
                + this.getDate().getMonth()
                + "."
                + this.getDate().getYear()
                +" Id:"
                +id
                + " \nnote: "
                + this.tag
                + " "
                + this.getMemo())
                + Note.DEFAULT;
    }

    public void doneNote() {
        this.tag = Note.GREEN;
    }

    protected void addMemo(String memo) {
        this.memo = this.getMemo() + "\n" + memo;
    }

    protected void changeMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public String getMemoWithColor() {
        return "ID:"+id+" "+this.tag + memo + Note.DEFAULT;
    }

    public int getId() {
        return id;
    }

    public NoteDate getDate() {
        return date;
    }
}
