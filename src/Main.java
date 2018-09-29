import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
public class Main {

    public static void main(String[] args) {

        User user = new User("Evelina","malvina");
        ToDoList todOlist = new ToDoList();
        todOlist.addNote(2017,8,26, "Note2");
        todOlist.doneNote("Note2");
        todOlist.addNote(2019,1,1,"Celebrate New Year.");
        todOlist.addNote(2019,1,2,"Celebrate New Year TWICE.");
        todOlist.removeNote(new NoteDate(2019,1,2),"Celebrate New Year TWICE.");
        todOlist.addNote(2021,1,2,"Celebrate New Year ONE MORE TIME.");

        todOlist.removeNote(4);
        todOlist.printAll(System.out);
       // todOlist.printNotesInTheInerval(new NoteDate(2018,1,1),new NoteDate(2020,1,1),System.out);

     // todOlist.removeNotesFromInerval(new NoteDate(2017,9,20),new NoteDate(2019,6,20)); //deleting

   //    todOlist.printAll();


      /* todOlist.addNote(2021,8,25,"Graduate from university!");
       todOlist.addNote("Push everything to GotHub.");
       todOlist.showAll();
       System.out.print("\n");
       todOlist.changeMemo(2018,8,16,"Commit to local repository then push to GitHub!");
       todOlist.addNote(2021,8,25, "Have a good time.");
       todOlist.showAll();*/
    }
}
