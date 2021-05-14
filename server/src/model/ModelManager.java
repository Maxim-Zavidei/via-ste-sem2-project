package model;

import common.model.Customer;
import common.model.Employee;
import common.model.Product;
import common.model.UserList;
import java.util.ArrayList;

public class ModelManager implements Model {

    @Override
    public UserList getAllRegisteredUsers() {
        // Dummy data.
        UserList toReturn = new UserList();
        toReturn.addUser(new Customer("bob@gmail.com", "1234"));
        toReturn.addUser(new Customer("george@gmail.com", "5678"));
        toReturn.addUser(new Employee("steve@gmail.com", "9876"));
        return toReturn;
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() {
        // Dummy data.
        ArrayList<Product> toReturn = new ArrayList<>();
        toReturn.add(new Product("1", 4, "Pain au Chocolate", "nice", 5));
        toReturn.add(new Product("2", 1, "Golden Apple", "extra nice", 7.41));
        toReturn.add(new Product("3", 3, "Sugar Bombs", "niche", 3.22));
        toReturn.add(new Product("4", 7, "2 kg of Sweets", "niche extra", 1));
        return toReturn;
    }
}
