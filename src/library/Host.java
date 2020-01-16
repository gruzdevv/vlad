package library;

public class Host {

    private int host_id;
    private String host_name;
    private String host_phone;
    private String host_email;
    
    public Host(int host_id, String host_name, String host_phone, String host_email) {
        this.host_id = host_id;
        this.host_name = host_name;
        this.host_phone = host_phone;
        this.host_email = host_email;
    }

    public int getHost_id() {
        return host_id;
    }

    public String getHost_name() {
        return host_name;
    }

    public String getHost_phone() {
        return host_phone;
    }

    public String getHost_email() {
        return host_email;
    }



}
