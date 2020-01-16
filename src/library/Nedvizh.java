package library;

public class Nedvizh {

    public Nedvizh(int nedvizh_id, String nedvizh_name, int raion_id, int street_id, int typened_id, String nedvizh_price) {
        this.nedvizh_id = nedvizh_id;
        this.nedvizh_name = nedvizh_name;
        this.raion_id = raion_id;
        this.street_id = street_id;
        this.typened_id = typened_id;
        this.nedvizh_price = nedvizh_price;
    }

    public int getNedvizh_id() {
        return nedvizh_id;
    }

    public String getNedvizh_name() {
        return nedvizh_name;
    }

    public int getRaion_id() {
        return raion_id;
    }

    public int getStreet_id() {
        return street_id;
    }

    public int getTypened_id() {
        return typened_id;
    }

    public String getNedvizh_price() {
        return nedvizh_price;
    }

    private int nedvizh_id;
    private String nedvizh_name;
    private int raion_id;
    private int street_id;
    private int typened_id;
    private String nedvizh_price;
}