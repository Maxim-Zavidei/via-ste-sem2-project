package common.model;

public interface UserManagement
{
  /************************************
  /* * Manage Products Methods *
  /************************************/
  void addProduct(Product product);
  void removeProduct(Product product);
  void removeProduct(String name);
  Product getProduct(String name);

  /************************************
   /* * Manage Users Methods *
   /************************************/
  void addUser(User user);
  void removeUser(User user);
  void removeUser(String email);
  User getUser(String email);
  User getUser(User user);
  User getUser(int index);

  /************************************
   /* * Getters for lists *
   /************************************/

  UsersList getUsers();
  ProductsList getProducts();
}
