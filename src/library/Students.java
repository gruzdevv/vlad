package library;

public class Students {

    private int student_id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String phone;
    private String city_name;
    private String mother_name;
    private String mother_phone;
    private String father_name;
    private String father_phone;

    public Students(int student_id, String lastname, String firstname, String middlename, String phone, String city_name, String mother_name, String mother_phone, String father_name, String father_phone) {
        this.student_id = student_id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.phone = phone;
        this.city_name = city_name;
        this.mother_name = mother_name;
        this.mother_phone = mother_phone;
        this.father_name = father_name;
        this.father_phone = father_phone;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public String getMother_phone() {
        return mother_phone;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getFather_phone() {
        return father_phone;
    }

//    public Students(int Id, String Title, String Author, int Year, int Pages, int Code){
//        this.id = Id;
//    	this.title=Title;
//        this.author = Author;
//        this.year=Year;
//        this.pages=Pages;
//        this.code=Code;
//    }
//
//    public int getId() {
//    	return id;
//    }
//    public String getTitle() {
//        return title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public int getPages() {
//        return pages;
//    }
//    
//    public int getCode() {
//        return code;
//    }
}
