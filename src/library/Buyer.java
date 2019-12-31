package library;

public class Buyer {

    private int buyer_id;
    private String fio;
    private String pol;
    private String address;
    private String phone;
    
    public Buyer(int buyer_id, String fio, String pol, String address, String phone) {
        this.buyer_id = buyer_id;
        this.fio = fio;
        this.pol = pol;
        this.address = address;
        this.phone = phone;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public String getFio() {
        return fio;
    }

    public String getPol() {
        return pol;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

}
