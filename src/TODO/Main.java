package TODO;

import DataBase.*;

public class Main {

    public static void main(String[] args) {

        User alice = new User("Alice","password");
        User bob = new User("Bob","password");
        AddUser add = new AddUser();
        Select select = new Select();;

        ToDoList.getToDoList().addNote(new NoteDate(1012,06,12), "first note", alice);
        ToDoList.getToDoList().addNote(new NoteDate(2012,14,12), "sec note", alice);
        ToDoList.getToDoList().addNote(new NoteDate(2019,10,29), "third note", alice);
        ToDoList.getToDoList().printNotes(alice,System.out,p->p.getDate().compareTo(new NoteDate())<0);
        select.selectAll();
        /*Authorization authorization = new Authorization();
        if(authorization.authorize(alice)){
            System.out.println("Entered");
            //создаем сессию
        }else{
            System.out.println("Wrong username or password");
        }*/
    }
}
