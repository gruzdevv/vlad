package library;

public class Operation {

    private int operation_id;
    private String operation_type;
    private String operation_price;
    
    public Operation(int operation_id, String operation_type, String operation_price) {
        this.operation_id = operation_id;
        this.operation_type = operation_type;
        this.operation_price = operation_price;
    }

    public int getOperation_id() {
        return operation_id;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public String getOperation_price() {
        return operation_price;
    }


    
}
