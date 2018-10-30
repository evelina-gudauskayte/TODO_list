package BL;

import DAL.NoteDTO;

import java.util.ArrayList;

public class JointNote extends Note {
    private ArrayList<String> usersIds = new ArrayList<>();
    public  JointNote (NoteDate date, String content, ArrayList<String> usersIds){
        super(date,content);
        this.usersIds = usersIds;
    }
    public JointNote(String content, ArrayList<String> usersIds){
        super(content);
        this.usersIds = usersIds;
    }
    public JointNote(NoteDTO noteDTO, ArrayList<String> usersIds ){
        super(noteDTO);
        this.usersIds=usersIds;
    }

    public ArrayList<String> getUsers() {
        return usersIds;
    }
    public void addUserId(String userId) {
        usersIds.add(userId);
    }
}
