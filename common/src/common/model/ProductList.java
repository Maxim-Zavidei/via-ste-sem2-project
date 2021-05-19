package common.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductList {

    private HashMap<String, Product> productList;

    public ProductList() {
        productList = new HashMap<>();
    }

    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(productList.values());
    }

    public void clear() {
        productList.clear();
    }

    public void addProduct(Product product) throws IllegalStateException {
        if (productList.get(product.getId()) != null) throw new IllegalStateException("Product is already in the basket.");
        productList.put(product.getId(), product);
    }

    public void replaceProduct(Product product) throws IllegalStateException {
        if (productList.get(product.getId()) == null) throw new IllegalStateException("No such product could be found in the basket.");
        productList.remove(product.getId());
        productList.put(product.getId(), product);
    }

    public void removeProduct(String productId) throws IllegalStateException {
        if (productList.get(productId) == null) throw new IllegalStateException("No such product could be found in the basket.");
        productList.remove(productId);
    }
}
