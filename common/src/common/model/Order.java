package common.model;

import java.util.HashMap;

public class Order {

    private String id;
    private HashMap<Product, Integer> products = new HashMap<>();
    private DateTime date;
    private Customer customer;

    /**Constructor*/
    public Order(String id, HashMap<Product, Integer> products, Customer customer) {
        this.id = id;
        this.products = products;
        this.customer = customer;
        this.date = new DateTime();
    }

    /**Edit order
     * adding new product*/
    public void addNewProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    /**Edit order
     * editing product's quantity*/
    public void editProductQuantity(Product product, int quantity) {
        //TODO decide what to return
        products.put(product, quantity);
        //return products;
    }

    /**Remove product from order*/
    public void removeProduct(Product product) {
        products.remove(product);
        //return products;
    }
}
