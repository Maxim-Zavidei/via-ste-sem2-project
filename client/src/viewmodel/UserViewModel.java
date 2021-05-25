package viewmodel;

import common.model.Customer;
import common.model.Employee;
import common.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Model;
import viewmodel.viewstate.UserManagementViewState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class UserViewModel
{
  /**Instance variables for list, selected item, management model*/
  private Model model;
  private ObservableList<UserView> list;
  private UserManagementViewState userManagementViewState;
  private boolean wasAuthenticatedUserQueried;

  /**Instance variable for other ui elements*/
  private StringProperty errorProperty;
  private StringProperty usernameProperty;
  private StringProperty basketButtonTitleProperty;

  public UserViewModel(Model model, UserManagementViewState userManagementViewState){
    // Initialize variables for list, selected item, management model
    this.model = model;
    this.list = FXCollections.observableArrayList();
    this.userManagementViewState = userManagementViewState;

    this.wasAuthenticatedUserQueried = false;

    // Initialize variable for other ui elements
    this.errorProperty = new SimpleStringProperty();
    usernameProperty = new SimpleStringProperty("");
    basketButtonTitleProperty = new SimpleStringProperty("Basket");
  }

  /**Methods, methods, methods*/

  public ObservableList<UserView> getList() {
    return list;
  }

  public void reset(){
    errorProperty.set("");
    if (!wasAuthenticatedUserQueried) {
      try {
        User authenticatedUser = model.getAuthenticatedUser();

      } catch (Exception e) {

        errorProperty.set(e.getMessage());
      }
      wasAuthenticatedUserQueried = true;
    }
    try
    {
      updateUsers();
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }


    boolean isEmployee = false;
    try {
      User authenticatedUser = model.getAuthenticatedUser();
      isEmployee = authenticatedUser.isEmployee();
      usernameProperty.set((isEmployee ? "Employee" : "Customer") + " ● " + authenticatedUser.getFullName());
    } catch (Exception e) {
      usernameProperty.set("");
      errorProperty.set(e.getMessage());
    }

    // Updates the number of products indicator in the basket button title.
    int tmp = model.getAllProductsInBasket().size();
    basketButtonTitleProperty.set(tmp == 0 ? "Basket" : "Basket (" + tmp + ")");
  }

  public StringProperty getErrorProperty() {
    return errorProperty;
  }

  public void makeEmployee(String selectedUser)
  {
      try
      {
        if(!model.getAllRegisteredUsers().getUser(selectedUser).isEmployee())
        {
          Customer newEmp = (Customer) model.getAllRegisteredUsers().getUser(selectedUser);
          model.updateUser(selectedUser, newEmp.getEmail(), newEmp.getPassword(),
              newEmp.getFirstName(), newEmp.getLastName(),LocalDate.of(newEmp.getBirthday().getYear(), newEmp.getBirthday().getMonth(),
                  newEmp.getBirthday().getDay()),
              newEmp.getGender(), true);
          updateUsers();
        }
        else throw new IllegalStateException("User is already employee! ");
      }
      catch (Exception e)
      {
        errorProperty.set(e.getMessage());
      }
  }

  public void deleteUser(String selectedUser)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if(list.get(i).getEmail().get().equals(selectedUser))
      {
        try
        {
          if (model.getAuthenticatedUser().getEmail().equals(selectedUser))
            throw new IllegalStateException("You cannot delete yourself!");
          else{
            list.remove(i);
            try
            {
              model.removeUser(selectedUser);
              updateUsers();
            }
            catch (Exception e)
            {
              errorProperty.set("Error in deleting user data");
            }
          }
        }
        catch (Exception e)
        {
          errorProperty.set(e.getMessage());
        }
      }
    }
  }

  public void fireEmployee(String selectedUser)
  {
    try
    {
      if (model.getAllRegisteredUsers().getUser(selectedUser).isEmployee())
      {
        if(model.getAuthenticatedUser().getEmail().equals(selectedUser))
          throw new IllegalStateException("You cannot fire yourself!");
        Employee newCust = (Employee) model.getAllRegisteredUsers().getUser(selectedUser);
        model.updateUser(selectedUser, newCust.getEmail(), newCust.getPassword(),
            newCust.getFirstName(), newCust.getLastName(), LocalDate
                .of(newCust.getBirthday().getYear(), newCust.getBirthday().getMonth(),
                    newCust.getBirthday().getDay()), newCust.getGender(), false);
        updateUsers();
      }
      else throw new IllegalStateException("User is not an employee! Why firing customers?");
    }
    catch (Exception e)
    {
      errorProperty.set(e.getMessage());
    }
  }

  public void updateUsers() throws Exception
  {
    list.clear();
    errorProperty.set("");
    ArrayList<User> users = model.getAllRegisteredUsers().getAllUsers();
    for (int i = 0; i < users.size(); i++)
    {
      list.add(new UserView(users.get(i)));
    }
  }

  public StringProperty getBasketButtonTitleProperty() {
    return basketButtonTitleProperty;
  }

  public StringProperty getUsernameProperty() {
    return usernameProperty;
  }
  public boolean deauthenticate() {
    wasAuthenticatedUserQueried = false;
    return model.deauthenticate();
  }

  public void addEdit(String selectedUser)
  {
    userManagementViewState.setSelectedUser(selectedUser);
  }

  public UserManagementViewState getUserManagementViewState()
  {
    return userManagementViewState;
  }
}
