package DAL;

public class NoteDTO {
    private int id;
    private int userId;
    private String content;

    public NoteDTO(int id, int userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }
}
