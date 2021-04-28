package common.model;

public interface EmployeeManagement
{
  /************************************
  /* * Manage Products Methods *
  /************************************/
  void addProduct(Product product);
  void removeProduct(Product product);
  void removeProduct(String name);
  Product getProduct(String name);

  /************************************
   /* * Manage Employees Methods *
   /************************************/
  void addEmployee(Employee employee);
  void removeEmployee(Employee employee);
  void removeEmployee(String name);
  Employee getEmployee(String name);
}
