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
            if(year==n.getDate().get(Calendar.YEAR) && month == n.getDate().get(Calendar.MONTH) && day == n.getDate().get(Calendar.DAY_OF_MONTH)){
                //n.addMemo(memo);
                return n;
            }
        }
        return null;
    }

    public void addNote(GregorianCalendar date, String memo){
        Note n = isExist(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH));
        if(n == null){
            list.add(new Note(date, memo));
        }else{n.addMemo(memo);}
        if(sort)this.sortList();
    }
    public void addNote(int year, int month, int day , String memo) {
        Note n = isExist(year,month,day);
        if(n==null){
            list.add(new Note(year,month,day,memo));
        }else{n.addMemo(memo);}
        if(sort)this.sortList();
    }
    public void addNote() {
        GregorianCalendar date = new GregorianCalendar();
        String memo = "empty note";
        Note n = isExist(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH));
        if(n == null){
            list.add(new Note(date, memo));
        }else{n.addMemo(memo);}
        if(sort)this.sortList();
    }
    public void addNote(String memo) {
        GregorianCalendar date = new GregorianCalendar();
        Note n = isExist(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH));
        if(n == null){
            list.add(new Note(date, memo));
        }else {n.addMemo(memo);}
        if(sort)this.sortList();
    }

    public void showAll(){
        for (Note n : list){
            System.out.println(n);
        }
    }
    public void showInterval(int year1, int month1, int day1,int year2, int month2, int day2){
        GregorianCalendar date1 = new GregorianCalendar(year1,month1,day1);
        GregorianCalendar date2 = new GregorianCalendar(year2,month2,day2);
        for(Note n : list){
            if(n.getDate().compareTo(date1) >= 0 ){
                if(n.getDate().compareTo(date2) <= 0 ){
                    System.out.println(n);
                }else{if(sort)break;}
            }
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
            if(year==n.getDate().get(Calendar.YEAR) && month == n.getDate().get(Calendar.MONTH) && day == n.getDate().get(Calendar.DAY_OF_MONTH) && n.getMemo()==memo){
                itr.remove();
                return;
            }
        }
    }

    public void changeMemo(int year, int month, int day, String memo){
        Iterator itr = list.iterator();
        while (itr.hasNext()){
            Note n = (Note)itr.next();
            if(year==n.getDate().get(Calendar.YEAR) && month == n.getDate().get(Calendar.MONTH) && day == n.getDate().get(Calendar.DAY_OF_MONTH)){
                n.changeMemo(memo);
                return;
            }
        }

    }
    public void changeSortStatemant(){
        if(sort){sort = false;}else sort = true;
    }
}
