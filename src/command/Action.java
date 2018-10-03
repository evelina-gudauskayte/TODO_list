package command;

import java.util.ArrayList;

public class Action {
    private ArrayList<Command> history = new ArrayList<>();

    public void storeAndExecute(final Command command){
        history.add(command);
        command.execute();
    }
}
