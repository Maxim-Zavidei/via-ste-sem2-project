package common.model;

public class EmployeeManager implements EmployeeManagement
{
  /************************************
   /* * Initiliaze both classes *
   /************************************/
  private ProductsList products;
  private EmployeeList employees;

  /************************************
   /* * Manager Constructor *
   /************************************/
  public EmployeeManager(){
    products = new ProductsList();
    employees = new EmployeeList();
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
   /* * Manage Employees Methods *
   /************************************/

  @Override public void addEmployee(Employee employee)
  {
    employees.addEmployee(employee);
  }

  @Override public void removeEmployee(Employee employee)
  {
    employees.removeEmployee(employee);
  }

  @Override public void removeEmployee(String name)
  {
    employees.removeEmployee(name);
  }

  @Override public Employee getEmployee(String name)
  {
    return employees.getEmployee(name);
  }
}
