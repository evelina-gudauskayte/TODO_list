import java.util.GregorianCalendar; //тут так секшуально, еее

public class Main {

    public static void main(String[] args) {
        ToDoList todOlist = new ToDoList();
        todOlist.addNote(2017,8,26, "Note2");
        todOlist.doneNote("Note2");
        todOlist.addNote(2019,1,1,"Celebrate New Year.");
        todOlist.addNote(2019,1,2,"Celebrate New Year TWICE.");
        todOlist.removeNote(new NoteDate(2019,1,2),"Celebrate New Year TWICE.");
        todOlist.addNote(2021,1,2,"Celebrate New Year ONE MORE TIME.");
        //todOlist.printAll();
        //todOlist.printNotesInTheInerval(new NoteDate(2018,1,1),new NoteDate(2020,1,1));

      todOlist.removeNotesFromInerval(new NoteDate(2017,9,20),new NoteDate(2019,6,20)); //deleting

       todOlist.printAll();


      /* todOlist.addNote(2021,8,25,"Graduate from university!");
       todOlist.addNote("Push everything to GotHub.");
       todOlist.showAll();
       System.out.print("\n");
       todOlist.changeMemo(2018,8,16,"Commit to local repository then push to GitHub!");
       todOlist.addNote(2021,8,25, "Have a good time.");
       todOlist.showAll();*/
    }
}
