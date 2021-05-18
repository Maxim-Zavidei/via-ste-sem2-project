package viewmodel;

import common.model.Customer;
import common.model.DateTime;
import common.model.User;
import common.model.UserList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.time.LocalDate;

public class UserManageViewModel {

  private Model model;

  // Instance variables for linking and storing elements of the user interface.
  private StringProperty errorProperty;
  private StringProperty emailProperty;
  private StringProperty passwordProperty;
  private StringProperty firstNameProperty;
  private StringProperty lastNameProperty;
  private ObjectProperty<LocalDate> birthdayPickerProperty;
  private ObjectProperty<Boolean> maleGenderButtonProperty;
  private ObjectProperty<Boolean> femaleGenderButtonProperty;
  private ViewState viewState;

  public UserManageViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    // Initialize the instance variables responsible for storing data of the ui elements.
    /*if (!viewState.getSelectedUser().equals(""))
    {
      try
      {
        UserList list = model.getAllRegisteredUsers();
        User user = list.getUser(viewState.getSelectedUser());
        emailProperty.set(user.getEmail());
        firstNameProperty.set(user.getFirstName());
        lastNameProperty.set(user.getLastName());
        passwordProperty.set(user.getPassword());
        birthdayPickerProperty.setValue(LocalDate
            .of(user.getBirthday().getYear(), user.getBirthday().getMonth(),
                user.getBirthday().getDay()));
        if (user.getGender() == 'M')
        {
          maleGenderButtonProperty.set(true);
          femaleGenderButtonProperty.set(false);
        }
        else
        {
          maleGenderButtonProperty.set(false);
          femaleGenderButtonProperty.set(true);
        }
      }
      catch (Exception e)
      {
        errorProperty.set("Something went wrong fetching the user list data");
      }
    }
    else{*/
      errorProperty = new SimpleStringProperty("");
      emailProperty = new SimpleStringProperty("");
      passwordProperty = new SimpleStringProperty("");
      firstNameProperty = new SimpleStringProperty("");
      lastNameProperty = new SimpleStringProperty("");
      birthdayPickerProperty = new SimpleObjectProperty<>();
      maleGenderButtonProperty = new SimpleObjectProperty<>();
      femaleGenderButtonProperty = new SimpleObjectProperty<>();
    //}
  }
  public void reset() {
    // Clear the fields and any errors whenever the window reopens.
    errorProperty.set("");
    emailProperty.set("");
    passwordProperty.set("");
    firstNameProperty.set("");
    lastNameProperty.set("");

    // Default selection of date picker is the present day.
    birthdayPickerProperty.set(LocalDate.now());

    // Default selection for radio buttons.
    maleGenderButtonProperty.set(true);
    femaleGenderButtonProperty.set(false);
  }

  public StringProperty getErrorProperty() {
    return errorProperty;
  }

  public StringProperty getEmailProperty() {
    return emailProperty;
  }

  public StringProperty getPasswordProperty() {
    return passwordProperty;
  }

  public StringProperty getFirstNameProperty() {
    return firstNameProperty;
  }

  public StringProperty getLastNameProperty() {
    return lastNameProperty;
  }

  public ObjectProperty<LocalDate> getBirthdayPickerProperty() {
    return birthdayPickerProperty;
  }

  public ObjectProperty<Boolean> maleGenderButtonProperty() {
    return maleGenderButtonProperty;
  }

  public ObjectProperty<Boolean> femaleGenderButtonProperty() {
    return femaleGenderButtonProperty;
  }

  public void modify()
  {
    
    /*if(viewState.getSelectedUser().equals(""))
    {
      char gender;
      DateTime newCustBirthday = new DateTime(birthdayPickerProperty.get());
      if(maleGenderButtonProperty.get())gender = 'M';
      else gender = 'F';
      Customer newCust = new Customer(emailProperty.get(), passwordProperty.get(), firstNameProperty.get(),
          lastNameProperty.get(), newCustBirthday, gender);
      try
      {
        model.getAllRegisteredUsers().addUser(newCust);
      }
      catch (Exception e)
      {
        errorProperty.set("Error creating the new customer");
      }
    }
    else{
      
      try
      {
        char gender;
        DateTime custBirthday = new DateTime(birthdayPickerProperty.get());
        if(maleGenderButtonProperty.get())gender = 'M';
        else gender = 'F';
        User user = model.getAllRegisteredUsers().getUser(viewState.getSelectedUser());
        User backUp = user;
        //Creating backUp and changing everything possible on this backUp and last statement updating the user
        if(!firstNameProperty.get().equals(""))backUp.setFirstName(firstNameProperty.get());
        if(!lastNameProperty.get().equals(""))backUp.setLastName(lastNameProperty.get());
        if(!passwordProperty.get().equals(""))backUp.setPassword(passwordProperty.get());
        if(backUp.getGender()!=gender)backUp.setGender(gender);
        if(backUp.getBirthday()!=custBirthday)backUp.setBirthday(LocalDate.of(custBirthday.getYear(), custBirthday.getMonth(), custBirthday.getDay()));
        //Would that work to change values? Would see
        user = backUp;
      }
      catch (Exception e)
      {
        errorProperty.set("Error in editing the existing customer");
      }
    }*/
    //Insert the data into the right places for name, birthday, etc.
    //Needs user list from model or something to pick up the user with that email (String selectedUser)
  }
}
