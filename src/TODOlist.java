import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class TODOlist {
    private ArrayList<Note> list = new ArrayList<>();
    private boolean sort = true;

;    public TODOlist() {
    }

    private void sortList(){
        list.sort(Note::compareTo);
    }
    private Note isExist(int year, int month, int day){
    Iterator itr = list.iterator();
        while (itr.hasNext()){
            Note n = (Note)itr.next();
            if(year==n.getDate().get(Calendar.YEAR)
                    && month == n.getDate().get(Calendar.MONTH)
                    && day == n.getDate().get(Calendar.DAY_OF_MONTH)){
                //n.addMemo(memo);
                return n;
            }
        }
        return null;
    }

    public void addNote(int year, int month, int day , String memo) {
        list.add(new Note(year,month,day,memo));
        this.sortList();
    }
    public void doneNote(String memo){
        for(Note n : list){
            if(n.getMemo().equals(memo)){
                n.doneNote();
            }
        }
    }

    public void showAll(){
        Iterator itr = list.iterator();
        if(itr.hasNext()){
            Note prev = (Note)itr.next();
            System.out.println("YEAR: "+prev.getDate().get(Calendar.YEAR));
            System.out.println(" MONTH: "+prev.getDate().get(Calendar.MONTH));
            System.out.println("  DAY: "+prev.getDate().get(Calendar.DAY_OF_MONTH));
            System.out.println("   "+prev.getMemoToPrint());
            while (itr.hasNext()){
                Note n = (Note)itr.next();
                printList(prev, n);
                System.out.println("   "+n.getMemoToPrint());
                prev = n;
            }
        }
    }
    public void showInterval(int year1, int month1, int day1,int year2, int month2, int day2){
        GregorianCalendar date1 = new GregorianCalendar(year1,month1,day1);
        GregorianCalendar date2 = new GregorianCalendar(year2,month2,day2);
        System.out.println("Interval:");
        Iterator itr = list.iterator();
        while (itr.hasNext()){
            Note prev = (Note)itr.next();
            if(prev.getDate().compareTo(date1)>=0){
                System.out.println("YEAR: "+prev.getDate().get(Calendar.YEAR));
                System.out.println(" MONTH: "+prev.getDate().get(Calendar.MONTH));
                System.out.println("  DAY: "+prev.getDate().get(Calendar.DAY_OF_MONTH));
                System.out.println("   "+prev.getMemo());
                while (itr.hasNext()){
                    Note n = (Note)itr.next();
                    if(n.getDate().compareTo(date2)<=0) {
                        printList(prev, n);
                        System.out.println("   " + n.getMemo());
                        prev = n;
                    }
                }
            }
        }
        System.out.println("End of interval.");
    }

    private void printList(Note prev, Note n) { //except first note(prev)
        if (n.getDate().get(Calendar.YEAR) != prev.getDate().get(Calendar.YEAR)) {
            System.out.println("YEAR: " + n.getDate().get(Calendar.YEAR));
            System.out.println(" MONTH: " + n.getDate().get(Calendar.MONTH));
            System.out.println("  DAY: " + n.getDate().get(Calendar.DAY_OF_MONTH));
        }else if (n.getDate().get(Calendar.MONTH) != prev.getDate().get(Calendar.MONTH)) {
            System.out.println(" MONTH: " + n.getDate().get(Calendar.MONTH));
            System.out.println("  DAY: " + n.getDate().get(Calendar.DAY_OF_MONTH));
        }else if (n.getDate().get(Calendar.DAY_OF_MONTH) != prev.getDate().get(Calendar.DAY_OF_MONTH)) {
            System.out.println("  DAY: " + n.getDate().get(Calendar.DAY_OF_MONTH));
        }
    }

    public void removeInterval(int year1, int month1, int day1,int year2, int month2, int day2){
        GregorianCalendar date1 = new GregorianCalendar(year1,month1,day1);
        GregorianCalendar date2 = new GregorianCalendar(year2,month2,day2);
        Iterator itr = list.iterator();
        while (itr.hasNext()){
            Note n = (Note)itr.next();
            if(n.getDate().compareTo(date1) >= 0 ){
                if(n.getDate().compareTo(date2) <= 0 ){
                    itr.remove();
                }else{if(sort)break;}
            }
        }

    }
    public void removeNote(int year, int month, int day, String memo){
        Iterator itr = list.iterator();
        while (itr.hasNext()){
            Note n = (Note)itr.next();
            if(year==n.getDate().get(Calendar.YEAR)
                    && month == n.getDate().get(Calendar.MONTH)
                    && day == n.getDate().get(Calendar.DAY_OF_MONTH)
                    && n.getMemo()==memo){
                itr.remove();
                return;
            }
        }
    }

    public void changeMemo(int year, int month, int day, String memo){
        Iterator itr = list.iterator();
        while (itr.hasNext()){
            Note n = (Note)itr.next();
            if(year==n.getDate().get(Calendar.YEAR)
                    && month == n.getDate().get(Calendar.MONTH)
                    && day == n.getDate().get(Calendar.DAY_OF_MONTH)){
                n.changeMemo(memo);
                return;
            }
        }

    }
    public void changeSortStatemant(){
        if(sort){sort = false;}else sort = true;
    }
}
