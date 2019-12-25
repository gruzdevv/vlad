package library;

public class City {
    
    private int city_id;
    private String city_name;
    private int distance;
    
    public City(int city_id, String city_name, int distance) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.distance = distance;
    }
    
    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public int getDistance() {
        return distance;
    }


}
