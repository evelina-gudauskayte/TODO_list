package TODO;

import java.io.PrintStream;
import java.util.*;

public class ToDoList {
    private TreeSet<Note> list = new TreeSet<>();
    private User creator;
    public ToDoList(User creator){
        this.creator=creator;
    }

    public void addNote(int year, int month, int day, String content) {
        this.addNote(new NoteDate(year, month, day), content);
    }

    public void addNote(NoteDate date, String content) {
        list.add(new Note(date, content,this.creator));
    }

    public void addNote(Note note) {
        list.add(note);
    }

    public void addUserToNote(int id, User user) {
        for (Note n : list) {
            if (n.getId() == id && n instanceof JointNote){
                ((JointNote) n).addUser(user);
            }
        }
    }

    public void markAsDone(int id) {
        for (Note n : list) {
            if (n.getId() == id) {
                n.markAsDone();
            }
        }
    }

    public void printAllNotes(PrintStream stream) {
        for (Note n : list) {
            stream.println(n);
        }
    }

    public void printSingleNotes(PrintStream stream) {
        for (Note n : list) {
            if (n.getClass() == Note.class) {
                stream.println(n);
            }
        }
    }

    public void printJointNotes(PrintStream stream) {
        for (Note n : list) {
            if (n.getClass() == JointNote.class) {
                stream.println(n);
            }
        }
    }

    public void printNotesInTheInerval(NoteDate date1, NoteDate date2, PrintStream stream) {
        for (Note n : list) {
            if (n.getDate().compareTo(date2) > 0) {
                break;
            }
            if (n.getDate().compareTo(date1) >= 0) {
                stream.println(n);
            }
        }
    }

    public void removeNotesFromInerval(NoteDate date1, NoteDate date2) {
        Iterator<Note> iterator = list.iterator();
        while (iterator.hasNext()) {
            Note n = iterator.next();
            if (n.getDate().compareTo(date2) > 0) {
                break;
            }
            if (n.getDate().compareTo(date1) >= 0) {
                iterator.remove();
            }
        }

    }

    public void removeNote(NoteDate date, String content) {
        Iterator<Note> itr = list.iterator();
        while (itr.hasNext()) {
            Note n = itr.next();
            if (date.getYear() == n.getDate().getYear()
                    && date.getMonth() == n.getDate().getMonth()
                    && date.getDayOfMonth() == n.getDate().getDayOfMonth()
                    && n.getContent().equals(content)) {
                itr.remove();
                return;
            }
        }
    }

    public void removeNote(int id) {
        list.removeIf(n -> n.getId() == id);
    }

    public void changecontent(int id, String content) {
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            Note n = (Note) itr.next();
            if (n.getId() == id) {
                n.changeContent(content);
                return;
            }
        }

    }
}
