package TODO;

import java.io.PrintStream;

public class Session {
    private User currentUser;
    private PrintStream stream;

    public Session(User user, PrintStream stream){
        this.currentUser=user;
        this.stream=stream;
    }

    void printAllNotes(){
        ToDoList.getToDoList().printNotes(currentUser, stream, p->true);
    }
    void printSingleNotes(){ToDoList.getToDoList().printNotes(currentUser,stream,p->p.getClass()==Note.class);}
    void printJointNotes(){
        ToDoList.getToDoList().printNotes(currentUser, stream, p->p.getClass()==JointNote.class);
    }
    void printPersonalNotes(){
        ToDoList.getToDoList().printNotes(currentUser, stream, p->p.getClass()==JointNote.class && p.isPersonal());
    }
    void printPersonalJointNotes(){
        ToDoList.getToDoList().printNotes(currentUser, stream, p->p.getClass()==JointNote.class && p.isPersonal());
    }
    void printWorkingNotes(){
        ToDoList.getToDoList().printNotes(currentUser, stream, p-> !p.isPersonal());
    }
    void printWorkingJointNotes(){
        ToDoList.getToDoList().printNotes(currentUser, stream, p->p.getClass()==JointNote.class && !p.isPersonal());
    }
}
