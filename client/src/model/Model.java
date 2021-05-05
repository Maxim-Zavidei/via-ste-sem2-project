package model;

import common.model.UserManagement;
import common.model.Product;
import common.utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject {
    ArrayList<Product> getCatalogOfProducts();
    UserManagement getManagement();
     void addProduct(Product product);
}
