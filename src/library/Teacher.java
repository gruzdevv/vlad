
package library;

public class Teacher {
    
    private int teacher_id;
    private String teacher_name;
    private String subject;
    private String teacher_phone;
    private String teacher_email;
    private int department_id;
    
    public Teacher(int teacher_id, String teacher_name, String subject, String teacher_phone, String teacher_email, int department_id) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.subject = subject;
        this.teacher_phone = teacher_phone;
        this.teacher_email = teacher_email;
        this.department_id = department_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher_phone() {
        return teacher_phone;
    }

    public String getTeacher_email() {
        return teacher_email;
    }

    public int getDepartment_id() {
        return department_id;
    }
    
}
