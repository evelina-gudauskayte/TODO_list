import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

public class ToDoList {
    private TreeSet<Note> list = new TreeSet<>();

    public ToDoList() {
    }

    public void addNote(int year, int month, int day, String memo) {
        list.add(new Note(year, month, day, memo));
    }

    public void addNote(NoteDate date, String memo) {
        list.add(new Note(date, memo));
    }

    public void doneNote(String memo) {
        for (Note n : list) {
            if (n.getMemo().equals(memo)) {
                n.doneNote();
            }
        }
    }

    public void printAll(PrintStream stream) {
        Iterator<Note> itr = list.iterator();
        if (itr.hasNext()) {
            Note prev = itr.next();
            stream.println("YEAR: " + prev.getDate().getYear());
            stream.println(" MONTH: " + prev.getDate().getMonth());
            stream.println("  DAY: " + prev.getDate().getDayOfMonth());
            stream.println("   " + prev.getMemoWithColor());
            while (itr.hasNext()) {
                Note n = itr.next();
                printList(prev, n, stream);
                stream.println("   " + n.getMemoWithColor());
                prev = n;
            }
        }
    }

    public void printNotesInTheInerval(NoteDate date1, NoteDate date2, PrintStream stream) {
        stream.println("Interval:");
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            Note prev = (Note) itr.next();
            if (prev.getDate().compareTo(date1) >= 0) {
                stream.println("YEAR: " + prev.getDate().getYear());
                stream.println(" MONTH: " + prev.getDate().getMonth());
                stream.println("  DAY: " + prev.getDate().getDayOfMonth());
                stream.println("   " + prev.getMemoWithColor());
                while (itr.hasNext()) {
                    Note n = (Note) itr.next();
                    if (n.getDate().compareTo(date2) <= 0) {
                        printList(prev, n, stream);
                        stream.println("   " + n.getMemoWithColor());
                        prev = n;
                    }
                }
            }
        }
        stream.println("End of interval.");
    }

    private void printList(Note prev, Note n, PrintStream stream) { //except first note(prev)
        if (n.getDate().getYear() != prev.getDate().getYear()) {
            stream.println("YEAR: " + n.getDate().getYear());
            stream.println(" MONTH: " + n.getDate().getMonth());
            stream.println("  DAY: " + n.getDate().getDayOfMonth());
        } else if (n.getDate().getMonth() != prev.getDate().getMonth()) {
            stream.println(" MONTH: " + n.getDate().getMonth());
            stream.println("  DAY: " + n.getDate().getDayOfMonth());
        } else if (n.getDate().getDayOfMonth() != prev.getDate().getDayOfMonth()) {
            stream.println("  DAY: " + n.getDate().getDayOfMonth());
        }
    }

    public void removeNotesFromInerval(NoteDate date1, NoteDate date2) {
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            Note n = (Note) itr.next();
            if (n.getDate().compareTo(date1) >= 0) {
                if (n.getDate().compareTo(date2) <= 0) {
                    itr.remove();
                } else {
                    break;
                }
            }
        }

    }

    public void removeNote(NoteDate date, String memo) {
        Iterator<Note> itr = list.iterator();
        while (itr.hasNext()) {
            Note n = itr.next();
            if (date.getYear() == n.getDate().getYear()
                    && date.getMonth() == n.getDate().getMonth()
                    && date.getDayOfMonth() == n.getDate().getDayOfMonth()
                    && n.getMemo() == memo) {
                itr.remove();
                return;
            }
        }
    }

    public void removeNote(int id){
        list.removeIf(n -> n.getId() == id);
    }

    public void changeMemo(NoteDate date, String memo) {
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            Note n = (Note) itr.next();
            if (date.getYear() == n.getDate().getYear()
                    && date.getMonth() == n.getDate().getMonth()
                    && date.getDayOfMonth() == n.getDate().getDayOfMonth()) {
                n.changeMemo(memo);
                return;
            }
        }

    }
}
