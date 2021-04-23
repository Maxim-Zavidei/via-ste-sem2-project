package common.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String id;
    private int quantity;
    private String name;
    private String description;
    private double price;

    public Product(String id, int quantity, String name, String description, double price) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
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

    public double getPrice() {
        return price;
    }
}
