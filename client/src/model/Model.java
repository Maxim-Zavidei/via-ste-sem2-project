package model;

import common.model.UserManagement;
import common.model.Product;
import java.util.ArrayList;

public interface Model {
    ArrayList<Product> getCatalogOfProducts();
    UserManagement getManagement();
}
