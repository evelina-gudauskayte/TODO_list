package BL;

import DAL.NoteDTO;
import javafx.util.Pair;

import java.util.ArrayList;

public class JointNote extends Note {
    private ArrayList<Integer> users = new ArrayList<>(); //pair of user and 0 if in process, 1 if done, -1 if not started
    public  JointNote (NoteDate date, String content, ArrayList<Integer> users){
        super(date,content);
        this.users = users;
    }
    public JointNote(NoteDTO noteDTO, ArrayList<Integer> users ){
        super(noteDTO);
        this.users=users;
    }
   /*

    public void addUser(User user) {
        if(user.isStatus()){
            users.add(new Pair<>(user,-1));
            user.addNote(this);
        } //need to make: else send notification with note and ask
    }
    }*/
}
