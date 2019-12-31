package library;

/**
 *
 * @author grafinina
 */
public class Postavka {

    private int postavka_id;
    private int provider_id;
    private int flower_id;
    private String data_postavka;
    private int postavka_count;
    private int price;
    
    public Postavka(int postavka_id, int provider_id, int flower_id, String data_postavka, int postavka_count, int price) {
        this.postavka_id = postavka_id;
        this.provider_id = provider_id;
        this.flower_id = flower_id;
        this.data_postavka = data_postavka;
        this.postavka_count = postavka_count;
        this.price = price;
    }

    public int getPostavka_id() {
        return postavka_id;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public int getFlower_id() {
        return flower_id;
    }

    public String getData_postavka() {
        return data_postavka;
    }

    public int getPostavka_count() {
        return postavka_count;
    }

    public int getPrice() {
        return price;
    }
    
}
