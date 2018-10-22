package DAL;

import java.util.ArrayList;

public class JointNoteDTO extends NoteDTO{
    ArrayList<Integer> users;
    public JointNoteDTO(int id, int userId, String content,ArrayList<Integer> users) {
        super(id, userId, content);
        this.users=users;
    }
}
