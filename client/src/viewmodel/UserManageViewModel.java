package viewmodel;

import common.model.*;
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
  private StringProperty addEditProperty;
  private StringProperty saveAddButtonProperty;
  private ViewState viewState;

  public UserManageViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    // Initialize the instance variables responsible for storing data of the ui elements.
    addEditProperty = new SimpleStringProperty("Add");
    saveAddButtonProperty = new SimpleStringProperty("Add");
    errorProperty = new SimpleStringProperty("");
    emailProperty = new SimpleStringProperty("");
    passwordProperty = new SimpleStringProperty("");
    firstNameProperty = new SimpleStringProperty("");
    lastNameProperty = new SimpleStringProperty("");
    birthdayPickerProperty = new SimpleObjectProperty<>();
    maleGenderButtonProperty = new SimpleObjectProperty<>();
    femaleGenderButtonProperty = new SimpleObjectProperty<>();
    loadFromSelected();
  }

  private void loadFromSelected(){
    if (!viewState.getSelectedUser().equals(""))
    {
      try
      {
        User user = model.getAllRegisteredUsers().getUser(
            viewState.getSelectedUser());
        addEditProperty.set("Edit");
        saveAddButtonProperty.set("Save");
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
        errorProperty.set("Something went wrong getting the selected user data");
      }
    }
    else {
      // Clear the fields and any errors whenever the window reopens.
      errorProperty.set("");
      emailProperty.set("");
      passwordProperty.set("");
      firstNameProperty.set("");
      lastNameProperty.set("");
      addEditProperty = new SimpleStringProperty("Add");
      saveAddButtonProperty = new SimpleStringProperty("Add");

      // Default selection of date picker is the present day.
      birthdayPickerProperty.set(LocalDate.now());

      // Default selection for radio buttons.
      maleGenderButtonProperty.set(true);
      femaleGenderButtonProperty.set(false);
    }
    errorProperty.set("");
  }

  public void reset() {
    loadFromSelected();
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

  public StringProperty getAddEditProperty() {return addEditProperty;}
  public StringProperty getSaveAddButtonProperty() { return saveAddButtonProperty;}

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
    if(viewState.getSelectedUser().equals(""))
    {
      char gender;
      DateTime newCustBirthday = new DateTime(birthdayPickerProperty.get());
      if(maleGenderButtonProperty.get())gender = 'M';
      else gender = 'F';
      Customer newCust = new Customer(emailProperty.get(), passwordProperty.get(), firstNameProperty.get(),
          lastNameProperty.get(), newCustBirthday, gender);
      try
      {
        model.addUser(newCust);
        errorProperty.set("New customer has been created! :)");
      }
      catch (Exception e)
      {
        System.out.println(e.getMessage());
        errorProperty.set("Error creating the new customer");
      }
    }
    else{
      try
      {
        char gender;
        DateTime custBirthday = new DateTime();
        String firstName="";
        String lastName=""; 
        String password=""; 
        String email="";
        String oldEmail="";
        if(!emailProperty.get().equals("") || emailProperty.get()!=null) oldEmail = viewState.getSelectedUser();
        if(!firstNameProperty.get().equals("") || firstNameProperty.get()!=null)firstName=firstNameProperty.get();
        if(!lastNameProperty.get().equals("") || lastNameProperty.get()!=null)lastName=lastNameProperty.get();
        if(!passwordProperty.get().equals("") || passwordProperty.get()!=null)password=passwordProperty.get();
        if(!emailProperty.get().equals("") || emailProperty.get()!=null)email=emailProperty.get();
        if(maleGenderButtonProperty.get() && maleGenderButtonProperty.get()!=null)gender = 'M';
        else gender = 'F';
        if(birthdayPickerProperty.get()!=null)custBirthday = new DateTime(birthdayPickerProperty.get());
        User user;
        if(model.getAllRegisteredUsers().getUser(oldEmail).isEmployee())
        {
          user = new Employee(email, password, firstName, lastName, custBirthday, gender);
        }
        else {
          user = new Customer(email, password, firstName, lastName, custBirthday, gender);
        }
        model.updateUser(oldEmail, user);
        errorProperty.set("User has been modified!");
      }
      catch (Exception e)
      {
        System.out.println(e.getMessage());
        errorProperty.set("Error in editing the existing customer");
      }
    }
  }
}
