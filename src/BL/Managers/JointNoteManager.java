package BL.Managers;

import BL.JointNote;

public class JointNoteManager extends NoteManager {

/*    public void addUserToNote(JointNote jointNote, int userId) {
        Access access = new Access();
        jointNote.addUserId(userId);
        access.addUserToJointNote(jointNote.getId(), userId);
    }*/

    public void deleteUserFromNote(JointNote jointNote, int userId) {
    }

    public void deleteNote(JointNote jointNote) {
    }

    /*public ArrayList<String> getNamesOfJointNoteUsers(JointNote jointNote) {
        Access access = new Access();
        ArrayList<String> users = new ArrayList<>();
        for (Integer i : jointNote.getUsers()) {
            users.add(access.getUser(i).getName());
        }
        return users;
    }*/
}
