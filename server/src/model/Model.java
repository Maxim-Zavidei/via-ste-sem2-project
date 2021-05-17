package model;

import common.model.Product;
import common.model.User;
import common.model.UserList;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model {
    UserList getAllRegisteredUsers();
    void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws IllegalArgumentException, IllegalStateException;
    User getAuthenticatedUser(String email) throws IllegalStateException;
    ArrayList<Product> getCatalogOfProducts();
    void createDummyData();
    void addProduct(Product product) throws IllegalArgumentException, IllegalStateException;
}
