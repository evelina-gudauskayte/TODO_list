import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        TODOlist todOlist = new TODOlist();
        todOlist.addNote(2017,8,26, "Note2");
        todOlist.doneNote("Note2");
        todOlist.addNote(2019,1,1,"Celebrate New Year.");
        todOlist.addNote(2019,1,2,"Celebrate New Year TWICE.");
        todOlist.addNote(2021,1,2,"Celebrate New Year ONE MORE TIME.");
        todOlist.showAll();
        //todOlist.showInterval(2018,1,1,2020,1,1);

     /*todOlist.removeInterval(2017,9,20,2019,6,20); //deleting
       todOlist.removeNote(2017,8,26, "Note2");
       System.out.println("\nAfter deleting:\n");
       todOlist.showAll();*/


      /* todOlist.addNote(2021,8,25,"Graduate from university!");
       todOlist.addNote("Push everything to GotHub.");
       todOlist.showAll();
       System.out.print("\n");
       todOlist.changeMemo(2018,8,16,"Commit to local repository then push to GitHub!");
       todOlist.addNote(2021,8,25, "Have a good time.");
       todOlist.showAll();*/
    }
}
