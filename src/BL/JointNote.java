package BL;

import DAL.NoteDTO;
import javafx.util.Pair;

import java.util.ArrayList;

public class JointNote extends Note {
    private ArrayList<Integer> users = new ArrayList<>();
    public  JointNote (NoteDate date, String content, ArrayList<Integer> users){
        super(date,content);
        this.users = users;
    }
    public JointNote(NoteDTO noteDTO, ArrayList<Integer> users ){
        super(noteDTO);
        this.users=users;
    }

    public ArrayList<Integer> getUsers() {
        return users;
    }
    public void addUserId(int userId) {
        users.add(userId);
    }
}
