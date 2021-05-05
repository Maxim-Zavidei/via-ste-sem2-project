package model;

import common.model.UserManagement;
import common.model.UserManager;
import common.model.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model {

    private UserManagement management;
    private PropertyChangeSupport property;
    public ModelManager(){
        this.management = new UserManager();
        property = new PropertyChangeSupport(this);

    }
    @Override
    public ArrayList<Product> getCatalogOfProducts() {
        // Temporary dummy data.
        ArrayList<Product> toReturn = new ArrayList<>();
        toReturn.add(new Product("1", 4, "Pain au Chocolate", "nice", 5));
        toReturn.add(new Product("2", 1, "Golden Apple", "extra nice", 7.4));
        toReturn.add(new Product("3", 3, "Sugar Bombs", "niche", 3.2));
        toReturn.add(new Product("4", 7, "2 kg of Sweets", "niche extra", 1));
        return toReturn;
    }

    public UserManagement getManagement()
    {
        return management;
    }

    public void addProduct(Product product){
        management.addProduct(product);
        property.firePropertyChange("Add",null, product);
        // for testing
        System.out.println("Fired the product!!!");
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(propertyName,listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {

        property.removePropertyChangeListener(propertyName, listener);
    }
}
