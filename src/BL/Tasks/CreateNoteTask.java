package BL.Tasks;

import BL.JointNote;
import BL.Listeners.ProgressListener;
import BL.Managers.NoteManager;
import BL.Managers.UserManager;
import BL.Note;
import BL.NoteDate;
import javafx.concurrent.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreateNoteTask extends Task<Boolean> {
    private ArrayList<ProgressListener> listeners = new ArrayList<>();
    private NoteDate date;
    private ArrayList<String> names;
    private String content;
    private NoteManager noteManager;
    private UserManager userManager;

    public CreateNoteTask(NoteDate date, ArrayList<String> names, String content, NoteManager noteManager, UserManager userManager) {
        this.date = date;
        this.names = names;
        this.content = content;
        this.noteManager = noteManager;
        this.userManager = userManager;
    }

    @Override
    protected Boolean call() throws Exception {
        if (names.size() != 0) {
            ArrayList<String> ids = new ArrayList<>();
            int counter = 1;
            for (String name : names) {
                ids.add(userManager.getIdByUsername(name));

                updateListeners(counter, names.size());
                Thread.sleep(1000);
                counter++;
            }
            noteManager.add(new JointNote(date, content, ids));
        } else {
            noteManager.add(new Note(date, content));
        }
        return true;
    }

    private void updateListeners(int progress, int total) {
        for (ProgressListener listener : listeners) {
            listener.inProgressChange(progress, total);
        }
    }

    public void addListener(ProgressListener listener) {
        listeners.add(listener);
    }
}
