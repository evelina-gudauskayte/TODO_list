package GUI;

import BL.Managers.LogInManager;
import BL.Managers.Session;
import BL.Note;
import DAL.Access;

public class App {
    public static void main(String[] args){
        Access access = new Access();
        LogInManager.addNewUser("Alice","password");
        Session alice = new Session("Alice","password");
        alice.getNoteManager().add(new Note("ecology"));
        Note note = new Note("unchanged");
        alice.getNoteManager().add(note);
        alice.getNoteManager().update(note,"changed");

    }
}
