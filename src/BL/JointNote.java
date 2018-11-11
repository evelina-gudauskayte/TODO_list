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

    @Override
    public NoteDTO getNoteDTO(String userId) {
        int personal = this.isPersonal() ? 1 : 0;
        return new NoteDTO(this.getId(), userId, this.getContent(),this.getDate().getYear(), this.getDate().getMonth(),this.getDate().getDayOfMonth(), 1 , personal);
    }

    public ArrayList<String> getUsers() {
        return usersIds;
    }

    public void addUserId(String userId) {
        usersIds.add(userId);
    }
}
