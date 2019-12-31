package library;

public class Postavshik {

    private int postavshik_id;
    private String name;
    private String fio;
    private String address;
    private String phone;
    
    public Postavshik(int postavshik_id, String name, String fio, String address, String phone) {
        this.postavshik_id = postavshik_id;
        this.name = name;
        this.fio = fio;
        this.address = address;
        this.phone = phone;
    }

    public int getPostavshik_id() {
        return postavshik_id;
    }

    public String getName() {
        return name;
    }

    public String getFio() {
        return fio;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }



}
