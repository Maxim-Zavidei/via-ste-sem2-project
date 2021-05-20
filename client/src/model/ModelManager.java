package model;

import common.model.*;
import mediator.Client;
import mediator.ClientTarget;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {

    private ClientTarget client;
    private ProductList basket;

    public ModelManager() throws Exception {
        try {
            client = new Client();
        } catch (Exception e) {
            throw new Exception("Could not reach the server.");
        }
        basket = new ProductList();
    }

    @Override
    public void stop() {
        client.stop();
    }

    @Override
    public void authenticate(String email, String password) throws Exception {
        client.authenticate(email, password);
    }

    @Override
    public boolean deauthenticate() {
        boolean toReturn = client.deauthenticate();
        if (toReturn) basket.clear();
        return toReturn;
    }

    @Override
    public void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws Exception {
        client.register(email, password, firstName, lastName, birthday, gender);
    }

    @Override
    public User getAuthenticatedUser() throws Exception {
        return client.getAuthenticatedUser();
    }

    @Override
    public UserList getAllRegisteredUsers() throws Exception {
        return client.getAllRegisteredUsers();
    }

    @Override
    public void updateUser(String oldEmail, String newEmail, String password, String firstName, String lastName, LocalDate birthday, char gender, boolean isEmployee) throws Exception {
        client.updateUser(oldEmail, newEmail, password, firstName, lastName, birthday, gender, isEmployee);
    }

    @Override public void updateUser(String email, User user) throws Exception
    {
        client.updateUser(email, user);
    }

    @Override
    public void removeUser(String email) throws Exception {
        client.removeUser(email);
    }

    @Override public void addUser(User user) throws Exception
    {
        client.addUser(user);
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() throws Exception {
        return client.getCatalogOfProducts();
    }

    @Override
    public Product getProductById(String productId) throws Exception {
        return client.getProductById(productId);
    }

    @Override
    public void addProduct(int quantity, String name, String description, double price) throws Exception {
        client.addProduct(quantity,name, description, price);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        client.updateProduct(product);
    }

    @Override
    public void removeProduct(Product product) throws Exception {
        client.removeProduct(product);
    }

    @Override
    public ArrayList<Product> getAllProductsInBasket() {
        return basket.getAllProducts();
    }

    @Override
    public void clearBasket() {
        basket.clear();
    }

    @Override
    public void addProductToBasket(Product product) throws IllegalStateException {
        basket.addProduct(product);
    }

    @Override
    public void replaceProductInBasket(Product product) throws IllegalStateException {
        basket.replaceProduct(product);
    }

    @Override
    public void removeProductFromBasket(String productId) throws IllegalStateException {
        basket.removeProduct(productId);
    }

    @Override
    public void placeOrder(Order order) throws Exception {
client.placeOrder(order);
    }
}
