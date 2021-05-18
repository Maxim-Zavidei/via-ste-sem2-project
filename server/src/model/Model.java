package model;

import common.model.Product;
import common.model.User;
import common.model.UserList;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model {
    UserList getAllRegisteredUsers() throws IllegalStateException;
    void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws IllegalArgumentException, IllegalStateException;
    User getUser(String email) throws IllegalArgumentException, IllegalStateException;
    void updateUser(String oldEmail, String newEmail, String password, String firstName, String lastName, LocalDate birthday, char gender) throws IllegalArgumentException, IllegalStateException;
    void removeUser(String email) throws IllegalArgumentException, IllegalStateException;
    ArrayList<Product> getCatalogOfProducts() throws IllegalStateException;
    void addProduct(Product product) throws IllegalStateException;
    void updateProduct(Product product) throws IllegalStateException;
    void removeProduct(Product product) throws IllegalStateException;
}
