package common.model;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {

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

    public Order(HashMap<Product, Integer> products, Customer customer){
        this("",products,customer);
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public DateTime getDate() {
        return date;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    /**Edit order
     * adding new product*/
    public void addNewProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    /**Edit order
     * editing product's quantity*/
    public void editProductQuantity(Product product, int quantity) {
        products.put(product, quantity);
    }

    /**Remove product from order*/
    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public String toString() {
        String output = "Order " + id + " by " + customer.getEmail() + " made on " + date.toString() +"\n";
        for (HashMap.Entry<Product,Integer> entry : products.entrySet()) {
            output += entry.getKey().getName() + " -> " + entry.getValue() + "\n";
        }
        return output;
    }
}
