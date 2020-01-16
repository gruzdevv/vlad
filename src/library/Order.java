package library;

public class Order {
    
    private int order_id;
    private int animal_id;
    private int operation_id;
    private String order_date;
    
    public Order(int order_id, int animal_id, int operation_id, String order_date) {
        this.order_id = order_id;
        this.animal_id = animal_id;
        this.operation_id = operation_id;
        this.order_date = order_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public int getOperation_id() {
        return operation_id;
    }

    public String getOrder_date() {
        return order_date;
    }

}