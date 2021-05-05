package common.model;

import java.util.ArrayList;

public class UsersList
{
  private CustomerList customers;
  private EmployeeList employees;
  private ArrayList<User> users;

  public UsersList(CustomerList customerList, EmployeeList employees)
  {
    this.users = new ArrayList<>();
    this.customers = customerList;
    this.employees = employees;
    merge();
  }

  public UsersList()
  {
    this.users = new ArrayList<>();
    this.customers = new CustomerList();
    this.employees = new EmployeeList();
    merge();
  }

  private void merge()
  {
    for (int i = 0; i < employees.getSize(); i++)
    {
      users.add(employees.getEmployee(i));
    }
    for (int i = 0; i < customers.getSize(); i++)
    {
      users.add(customers.getCustomer(i));
    }
  }

  public ArrayList<User> getUsers()
  {
    return users;
  }

  public int getSize()
  {
    return users.size();
  }

  public void addUser(User user)
  {
    users.add(user);
  }

  public void removeUser(User user)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).equals(user))
        users.remove(user);
    }
  }

  public void removeUser(String email)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getEmail().equals(email))
        users.remove(i);
    }
  }

  public User getUser(String email)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getEmail().equals(email))
        return users.get(i);
    }
    return null;
  }

  public User getUser(int index){
    return users.get(index);
  }
  public User getUser(User user)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if(users.get(i).equals(user))return users.get(i);
    }
    return null;
  }
}