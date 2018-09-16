import java.util.Calendar;
import java.util.GregorianCalendar;

public class Note implements Comparable<Note>{
    private String memo;
    private GregorianCalendar date;

    public Note(int year, int month, int date, String memo){
        this.date = new GregorianCalendar(year,month,date);
        this.memo=memo;
    }

    public Note(GregorianCalendar date, String memo){
        this.date=date;
        this.memo=memo;
    }
    public Note(String memo){
        this.date = new GregorianCalendar();
        this.memo = memo;
    }
    public Note(){
        this.date = new GregorianCalendar();
        this.memo = "empty note";
    }
    @Override
    public int compareTo(Note note){
        int ans = this.date.compareTo(note.getDate());
        switch (ans){
            case -1: return -1;
            case 1: return 1;
            default: return 0;
        }
    }
    @Override
    public String toString(){
        return ("Deadline at: "
                + this.getDate().get(Calendar.DAY_OF_MONTH)
                + "."
                + this.getDate().get(Calendar.MONTH)
                + "."
                + this.getDate().get(Calendar.YEAR)
                +" \nnote: "
                + this.getMemo());
    }

    protected void  addMemo(String memo){
        this.memo=this.getMemo()+"\n"+memo;
    }
    protected void changeMemo(String memo){
        this.memo=memo;
    }

    public String getMemo() {
        return memo;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
