package common.model;

public class UserManager implements UserManagement
{
  /************************************
   /* * Initialize both classes *
   /************************************/
  private ProductsList products;
  private UsersList users;

  /************************************
   /* * Manager Constructor *
   /************************************/
  public UserManager(){
    products = new ProductsList();
    users = new UsersList();
  }
  public UserManager(ProductsList products, UsersList users){
    this.products = products;
    this.users = users;
  }

  /************************************
   /* * Manage Products Methods *
   /************************************/
  @Override public void addProduct(Product product)
  {
    products.addProduct(product);
  }

  @Override public void removeProduct(Product product)
  {
    products.removeProduct(product);
  }

  @Override public void removeProduct(String name)
  {
    products.removeProduct(name);
  }

  @Override public Product getProduct(String name)
  {
    return products.getProduct(name);
  }

  /************************************
   /* * Manage Users Methods *
   /************************************/

  @Override public void addUser(User user)
  {
    users.addUser(user);
  }

  @Override public void removeUser(User user)
  {
    users.removeUser(user);
  }

  @Override public void removeUser(String email)
  {
    users.removeUser(email);
  }

  @Override public User getUser(String email)
  {
    return users.getUser(email);
  }
  @Override public User getUser(User user){
    return users.getUser(user);
  }
  @Override public User getUser(int index){
    return users.getUser(index);
  }

  /************************************
   /* * Getters for lists *
   /************************************/

  public UsersList getUsers()
  {
    return users;
  }
  public ProductsList getProducts()
  {
    return products;
  }
}
