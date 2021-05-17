package model;

import common.model.Product;
import common.model.User;
import mediator.Client;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {

    private Client client;

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
    public ArrayList<Product> getCatalogOfProducts() throws Exception {
        return client.getCatalogOfProducts();
    }

    @Override
    public void addProduct(Product product) throws Exception {
        client.addProduct(product);
    }
}
