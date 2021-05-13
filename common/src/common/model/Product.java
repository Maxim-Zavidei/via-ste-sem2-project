package common.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String id;
    private int quantity;
    private String name;
    private String description;
    private double price;

    public Product(String id, int quantity, String name, String description, double price) {
        if (id.equals("")) throw new IllegalArgumentException("Id cannot be empty");
        if (name.equals("")) throw new IllegalArgumentException("Name cannot be empty");
        if (description.equals("")) throw new IllegalArgumentException("Description cannot be empty");
        if (price < 1) throw new IllegalArgumentException("Price cannot be less than 1");
        if (quantity < 1) throw new IllegalArgumentException("Quantity cannot be less than 1");

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

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price;
    }
}
