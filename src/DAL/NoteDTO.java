package DAL;

public class NoteDTO {
    private int id;
    private int userId;
    private String content;
    private int year;
    private int month;
    private int day;
    private int isJoint; //0=no, 1-yes

     NoteDTO(  int id, int userId,
                    String content,
                    int year,
                    int month,
                    int day,
                    int isJoint) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.year = year;
        this.month = month;
        this.day = day;
        this.isJoint = isJoint;
    }
     NoteDTO(int userId, String content, int year, int month, int say, int isJoint) {
        this.id = -1; //tmp note
        this.userId = userId;
        this.content = content;
        this.year = year;
        this.month = month;
        this.day = say;
        this.isJoint = isJoint;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getIsJoint() {
        return isJoint;
    }
}