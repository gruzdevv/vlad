package library;

public class Department {
    
    private int department_id;
    private String department_name;
    private int department_zam_id;
    
    public Department(int department_id, String department_name, int department_zam_id) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.department_zam_id = department_zam_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public int getDepartment_zam_id() {
        return department_zam_id;
    }

    
}
