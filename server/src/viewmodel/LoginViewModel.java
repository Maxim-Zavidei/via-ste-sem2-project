package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel
{
  private Model model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty error;
  public LoginViewModel(Model model){
    this.model = model;
    this.username = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
  }
  public void reset(){
    username.set("");
    password.set("");
    error.set("");
  }

  private void validateUser() throws IllegalArgumentException
  {
    if(username.get().equals("") || password.get().equals("")) throw new IllegalArgumentException();
  }

  public boolean login()
  {
    return false;
    //TODO
  }

  public void signUp()
  {
    //TODO
  }

  public StringProperty getError()
  {
    return error;
  }

  public StringProperty getPassword()
  {
    return password;
  }

  public StringProperty getUsername()
  {
    return username;
  }
}
