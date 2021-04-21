package model;

import java.util.HashMap;

public class Order {
    private String id;
    private HashMap<Product, Integer> products = new HashMap<Product, Integer>();
   private DateTime date;
   private Customer customer;

    public Order(String id, HashMap<Product, Integer> products, Customer customer)
    {
        this.id = id;
        this.products = products;
        this.customer = customer;
    }

    public void addNewProduct(Product product, int quantity){
        if(products.containsKey(product)){
            products.put(product, quantity);
        }
        else{
            products.put(product, quantity);
        }
    }
    public void editProductQuantity(Product product, int quantity){
        //TODO decide what to return
        products.put(product, quantity);
        //return products;
    }
    public void removeProduct(Product product){
        products.remove(product);
        //return products;
    }
}
