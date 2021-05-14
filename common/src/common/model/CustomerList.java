package common.model;

import java.util.HashMap;
import java.util.Map;

public class CustomerList {
    /**
     * Instance variables
     */
    private Map<String, Customer> customers;

    /**
     * Constructor
     */
    public CustomerList() {
        customers = new HashMap<>();
    }

    /**
     * Methods
     */
    public void addCustomer(Customer customer) {
        customers.put(customer.getEmail(), customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer.getEmail());
    }

    public void removeCustomer(String name) {
        customers.remove(name);
    }

    public Customer getCustomer(String name) {
        return customers.get(name);
    }

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    public int getSize() {
        return customers.size();
    }

    public String getCustomers() {
        StringBuilder returnCustomers = new StringBuilder();
        for (Map.Entry<String, Customer> customer : customers.entrySet()) {
            returnCustomers.append(customer.getValue().toString());
        }
        return returnCustomers.toString();
    }
}
