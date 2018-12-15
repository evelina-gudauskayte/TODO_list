package GUI;

import BL.JointNote;
import BL.Note;
import DAL.Access;
import DAL.RealNoteDAO;
import DAL.RealUserDAO;
import BL.Managers.*;
import Util.BadContextExeption;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws BadContextExeption {
      //LogInManager.addNewUser("Alice","password");
       Access access = new Access();
       UserManager userManager = new UserManagerImplementation(new RealUserDAO());
       userManager.authorizeUser("Alice","password");
       NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
       Note note = new Note("Another stupid Alice's note ");
       noteManager.add(note);
    }
}
