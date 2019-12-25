
package library;

public class Course {

    private int course_id;
    private String course_name;
    private int department_id;
    
    public Course(int course_id, String course_name, int department_id) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.department_id = department_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public int getDepartment_id() {
        return department_id;
    }
    
}
