package command;

import TODO.User;

public class DeleteNoteCommand implements Command{
    private User user;
    int id;

    public DeleteNoteCommand(User user, int id) {
        this.user = user;
        this.id = id;
    }

    @Override
    public void execute() {
        //user.removeNote(id);
    }
}
