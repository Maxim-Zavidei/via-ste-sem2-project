package model;

import common.model.Product;
import common.model.User;
import common.model.UserList;
import mediator.Client;
import mediator.ClientTarget;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {

    private ClientTarget client;

    public ModelManager() throws Exception {
        try {
            client = new Client();
        } catch (Exception e) {
            throw new Exception("Could not reach the server.");
        }
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
        return client.deauthenticate();
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

    @Override
    public void removeUser(String email) throws Exception {
        client.removeUser(email);
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() throws Exception {
        return client.getCatalogOfProducts();
    }

    @Override
    public void addProduct(Product product) throws Exception {
        client.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        client.updateProduct(product);
    }

    @Override
    public void removeProduct(Product product) throws Exception {
        client.removeProduct(product);
    }
}
