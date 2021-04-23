package common.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String id;
    private int quantity;
    private String name;
    private String description;

    public Product(String id, int quantity, String name, String description) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
