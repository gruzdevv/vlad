package library;

public class Raion {

    public Raion(int raion_id, String raion_name) {
        this.raion_id = raion_id;
        this.raion_name = raion_name;
    }

    public int getRaion_id() {
        return raion_id;
    }

    public String getRaion_name() {
        return raion_name;
    }
    
    private int raion_id;
    private String raion_name;
}