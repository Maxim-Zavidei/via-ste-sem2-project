package viewmodel;

import common.model.Customer;
import common.model.Employee;
import common.model.UserManagement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserViewModel
{
  /**Instance variables for list, selected item, management model*/
  private UserManagement model;
  private ObservableList<UserView> list;
  private ObjectProperty<UserView> selectedEmployee;

  /**Instance variable for other ui elements*/
  private StringProperty errorProperty;

  public UserViewModel(UserManagement model){
    // Initialize variables for list, selected item, management model
    this.model = model;
    this.list = FXCollections.observableArrayList();
    this.selectedEmployee = new SimpleObjectProperty<>();

    // Initialize variable for other ui elements
    this.errorProperty = new SimpleStringProperty();
  }

  /**Methods, methods, methods*/
  public void setSelectedEmployee(UserView selectedEmployee){
    this.selectedEmployee.set(selectedEmployee);
  }

  public ObservableList<UserView> getList() {
    return list;
  }

  public void reset(){
    errorProperty.set("");
    setSelectedEmployee(null);
    updateUsers();
  }

  public StringProperty getErrorProperty() {
    return errorProperty;
  }

  public void makeEmployee(UserView selectedItem)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if(list.get(i).getFirstName().get().equals(selectedItem.getFirstName().get())
      && list.get(i).getLastName().get().equals(selectedItem.getLastName().get())
      && list.get(i).getBirthDate().get().equals(selectedItem.getBirthDate().get()))
      {
        Employee newEmployee = new Employee(selectedItem.getFirstName().get(), selectedItem.getLastName().get(), selectedItem.getEmail().get());
        list.remove(selectedItem);
        list.add(new UserView(newEmployee));
      }
    }
  }

  public void deleteUser(UserView selectedItem)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if(list.get(i).getFirstName().get().equals(selectedItem.getFirstName().get())
          && list.get(i).getLastName().get().equals(selectedItem.getLastName().get())
          && list.get(i).getBirthDate().get().equals(selectedItem.getBirthDate().get()))
        list.remove(selectedItem);
    }
  }

  public void fireEmployee(UserView selectedItem)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if(list.get(i).getFirstName().get().equals(selectedItem.getFirstName().get())
          && list.get(i).getLastName().get().equals(selectedItem.getLastName().get())
          && list.get(i).getBirthDate().get().equals(selectedItem.getBirthDate().get()))
      {
        Customer newCustomer = new Customer(selectedItem.getEmail().get(), "", selectedItem.getFirstName().get(), selectedItem.getLastName().get(), null, 'u');
        list.remove(selectedItem);
        list.add(new UserView(newCustomer));
      }
    }
  }

  public void updateUsers(){
    list.clear();
    // TO DO: Later
//    for (User user : model.)
//    for (int i = 0; i < model.getUsers().getSize(); i++)
//    {
//      list.add(new UserView(model.getUsers().getUser(i)));
//    }
  }
}
