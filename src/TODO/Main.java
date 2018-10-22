package TODO;

import Dialogs.MainFrame;

public class Main {

    public static void main(String[] args) {

        User alice = new User("Alice","password");
        User bob = new User("Bob","password");
//        Select select = new Select();;

        ToDoList.getToDoList().addNote(new NoteDate(1012,06,12), "first note", alice);
        ToDoList.getToDoList().addNote(new NoteDate(2012,14,12), "sec note", alice);
        ToDoList.getToDoList().addNote(new NoteDate(2019,10,29), "third note", alice);

        ToDoList.getToDoList().addNote(new NoteDate(2014,06,12), "first note", bob);
        ToDoList.getToDoList().addNote(new NoteDate(2013,14,12), "sec note", bob);
        ToDoList.getToDoList().addNote(new NoteDate(2019,10,29), "third note", bob);
        ToDoList.getToDoList().addNote(new NoteDate(2412,06,12), "future future future future future future note", bob);
        ToDoList.getToDoList().addNote(new NoteDate(2812,14,12), "sec note", bob);
//        ToDoList.getToDoList().printNotes(alice,System.out,p->p.getDate().compareTo(new NoteDate())<0);
        MainFrame mainFrame = new MainFrame();
        mainFrame.launch();
        /*Authorization authorization = new Authorization();
        if(authorization.authorize(alice)){
            System.out.println("Entered");
            //создаем сессию
        }else{
            System.out.println("Wrong username or password");
        }*/
    }
}
