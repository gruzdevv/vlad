package library;

public class Worker {

    private int worker_id;
    private String fio;
    private String post;
    private String address;
    private String phone;

    public Worker(int worker_id, String fio, String post, String address, String phone) {
        this.worker_id = worker_id;
        this.fio = fio;
        this.post = post;
        this.address = address;
        this.phone = phone;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public String getFio() {
        return fio;
    }

    public String getPost() {
        return post;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

}
