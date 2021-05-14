package model;

import common.model.Product;
import java.util.ArrayList;

public interface Model {
    void stop();
    void authenticate(String email, String password) throws Exception;
    boolean deauthenticate();
    ArrayList<Product> getCatalogOfProducts() throws Exception;


//    void addProduct(Product product) throws Exception;
//    void removeProduct(Product product) throws Exception;
//    //void removeProduct(String name) throws Exception;
//    //Product getProduct(String name) throws Exception;
//    void addUser(User user) throws Exception;
//    void removeUser(User user) throws Exception;
//    //void removeUser(String email) throws Exception;
//    //User getUser(String email) throws Exception;
//    //User getUser(User user) throws Exception;
//    //User getUser(int index throws Exception);
}
