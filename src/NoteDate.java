import java.util.Calendar;
import java.util.GregorianCalendar;

public class NoteDate implements Comparable<NoteDate> {
    private GregorianCalendar date;

    public NoteDate() {
        date = new GregorianCalendar();
    }

    public NoteDate(int year, int month, int day) {
        date = new GregorianCalendar(year, month, day);
    }

    @Override
    public int compareTo(NoteDate date2 ){
        return this.date.compareTo(date2.date);
    }

    @Override
    public String toString(){
        return ("Year:"
                +this.date.get(Calendar.YEAR)
                +"\n Month:"
                +this.date.get(Calendar.MONTH)
                +"\n  Day:"
                +this.date.get(Calendar.DAY_OF_MONTH));
    }

    public int getYear(){
        return this.date.get(Calendar.YEAR);
    }

    public int getMonth(){
        return this.date.get(Calendar.MONTH);
    }

    public int getDayOfMonth(){
        return this.date.get(Calendar.DAY_OF_MONTH);
    }
}
