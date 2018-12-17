package GUI;

import DAL.Access;
import DAL.RealNoteDAO;
import DAL.RealUserDAO;
import BL.Managers.*;
import Util.BadContextException;

public class App {
    public static void main(String[] args) throws BadContextException {
      // Access access = new Access();
       UserManager userManager = new UserManagerImplementation(new RealUserDAO());
       userManager.authorizeUser("Alice","password");
       NoteManager noteManager = new NoteManagerImplementation(new RealNoteDAO());
    }
}
