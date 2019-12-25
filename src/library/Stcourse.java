package library;

public class Stcourse {
    
    private int student_course_id;
    private int student_id;
    private int course_id;
    private int gr_id;
    
    public Stcourse(int student_course_id, int student_id, int course_id, int gr_id) {
        this.student_course_id = student_course_id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.gr_id = gr_id;
    }

    public int getStudent_course_id() {
        return student_course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public int getGr_id() {
        return gr_id;
    }
}
