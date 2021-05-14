package model;

import common.model.Product;
import mediator.Client;
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
    public ArrayList<Product> getCatalogOfProducts() throws Exception {
        return client.getCatalogOfProducts();
    }

}
