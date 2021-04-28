package common.model;

import java.util.HashMap;
import java.util.Map;

public class ProductsList
{
  /**Instance variables*/
  private Map<String, Product> products;
  /************************************/

  /**Constructor*/
  public ProductsList(){
    products = new HashMap<>();
  }
  /****************************/

  /**Methods*/
  public void addProduct(Product product){
    products.put(product.getName(), product);
  }
  public void removeProduct(Product product){
    products.remove(product.getName(), product);
  }
  public void removeProduct(String name){
    products.remove(name);
  }
  public Product getProduct(String name){
    return products.get(name);
  }
  public String getProducts(){
    StringBuilder returnProducts= new StringBuilder();
    for (Map.Entry<String, Product> product : products.entrySet()){
      returnProducts.append(product.getValue().toString());
    }
    return returnProducts.toString();
  }
  /**************************************/
}
