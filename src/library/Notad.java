package library;

public class Notad {
    
    private int notad_id;
    private String date;
    private int student_id;
    private int count;
    
    public Notad(int notad_id, String date, int student_id, int count) {
        this.notad_id = notad_id;
        this.date = date;
        this.student_id = student_id;
        this.count = count;
    }

    public int getNotad_id() {
        return notad_id;
    }

    public String getDate() {
        return date;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getCount() {
        return count;
    }
    
}
