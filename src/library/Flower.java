package library;

public class Flower {

    private int flower_id;
    private String name;
    private String height;
    private String price;
    private String flower_count;
    
    public Flower(int flower_id, String name, String height, String price, String flower_count) {
        this.flower_id = flower_id;
        this.name = name;
        this.height = height;
        this.price = price;
        this.flower_count = flower_count;
    }

    public int getFlower_id() {
        return flower_id;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getPrice() {
        return price;
    }

    public String getFlower_count() {
        return flower_count;
    }
    

}