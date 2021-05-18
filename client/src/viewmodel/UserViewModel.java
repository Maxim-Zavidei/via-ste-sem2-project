package viewmodel;

import common.model.Customer;
import common.model.Employee;
import common.model.User;
import common.model.UserList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import view.ViewState;

import java.util.ArrayList;

public class UserViewModel
{
  /**Instance variables for list, selected item, management model*/
  private Model model;
  private ObservableList<UserView> list;
  private boolean wasAuthenticatedUserQueried;

  /**Instance variable for other ui elements*/
  private StringProperty errorProperty;

  public UserViewModel(Model model){
    // Initialize variables for list, selected item, management model
    this.model = model;
    this.list = FXCollections.observableArrayList();

    this.wasAuthenticatedUserQueried = false;

    // Initialize variable for other ui elements
    this.errorProperty = new SimpleStringProperty();
  }

  /**Methods, methods, methods*/

  public ObservableList<UserView> getList() {
    return list;
  }

  public void reset(){
    errorProperty.set("");
//    if (!wasAuthenticatedUserQueried) {
//      try {
//        User authenticatedUser = model.getAuthenticatedUser();
//
//      } catch (Exception e) {
//
//        errorProperty.set(e.getMessage());
//      }
//      wasAuthenticatedUserQueried = true;
//    }
    try
    {
      updateUsers();
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

  public StringProperty getErrorProperty() {
    return errorProperty;
  }

  public void makeEmployee(String selectedUser)
  {
    //Totally bad approach, need ideas! (Maybe some extra variables in User or its subclasses?
    try
    {
      Employee newEmp = (Employee) model.getAllRegisteredUsers().getUser(selectedUser);
      model.getAllRegisteredUsers().removeUser(selectedUser);
      model.getAllRegisteredUsers().addUser(newEmp);
    }
    catch (Exception e)
    {
      errorProperty.set("Error in promoting customer");
    }
  }

  public void deleteUser(String selectedUser)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if(list.get(i).getEmail().get().equals(selectedUser)){
        list.remove(i);
        try
        {
          model.getAllRegisteredUsers().removeUser(selectedUser);
        }
        catch (Exception e)
        {
          errorProperty.set("Error in fetching user data");
        }
      }
    }
  }

  public void fireEmployee(String selectedUser)
  {
    //Totally bad approach, need ideas! (Maybe some extra variables in User or its subclasses?
    try
    {
      Customer newCust = (Customer) model.getAllRegisteredUsers().getUser(selectedUser);
      model.getAllRegisteredUsers().removeUser(selectedUser);
      model.getAllRegisteredUsers().addUser(newCust);
    }
    catch (Exception e)
    {
      errorProperty.set("Error in resigning employee");
    }
  }

  public void updateUsers() throws Exception
  {
    list.clear();
    ArrayList<User> users = model.getAllRegisteredUsers().getAllUsers();
    System.out.println(users);
    for (int i = 0; i < users.size(); i++)
    {
      list.add(new UserView(users.get(i)));
    }
  }
  public boolean deauthenticate() {
    wasAuthenticatedUserQueried = false;
    return model.deauthenticate();
  }

  public void backToUsers()
  {
  }

  public void addEdit(String selectedUser)
  {
  }
}
