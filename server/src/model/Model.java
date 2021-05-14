package model;

import common.model.Product;
import common.model.UserList;
import java.util.ArrayList;

public interface Model {
    UserList getAllRegisteredUsers();
    ArrayList<Product> getCatalogOfProducts();
}
