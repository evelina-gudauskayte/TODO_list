package TODO;

import javafx.util.Pair;

import java.util.ArrayList;

public class JointNote extends Note {
    private ArrayList<Pair<User, Integer>> users = new ArrayList<>(); //pair of user and 0 if in process, 1 if done, -1 if not started
    public  JointNote (NoteDate date, String content, User creator){
        super(date,content,creator);
        users.add(new Pair<>(creator, -1));
    }
   /* public JointNote(Note note){
        super(note.getDate(),note.getContent(), note.getCreator());
        users.add(new Pair<>(note.getCreator(),-1));
    }

    public void addUser(User user) {
        if(user.isStatus()){
            users.add(new Pair<>(user,-1));
            user.addNote(this);
        } //need to make: else send notification with note and ask
    }

    @Override
    public String toString(){
        String str = "";
        for(Pair<User, Integer> pair: users){
            String status;
            if (pair.getValue() > 0) {
                status = "done";
            } else if (pair.getValue() < 0) {
                status = "not started";
            } else {
                status = "in the process";
            }
            str = String.format("%s%s", str, pair.getKey().getUserName() + " " + status + " the note.");
        }
        return ("Deadline at: "
                +this.getDate()
                + " Id:"
                + this.getId()
                + " \n    note: "
                + " "
                + this.getContent()
                +"\n     "
                +str);
    }*/
}
