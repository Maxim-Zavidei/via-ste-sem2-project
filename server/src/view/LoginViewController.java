package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewmodel.LoginViewModel;

public class LoginViewController extends ViewController
{
  @FXML private TextField userNameTextField;
  @FXML private PasswordField passwordTextField;
  @FXML private Label errorLabel;
  private ViewHandler viewHandler;
  private LoginViewModel viewModel;


  public LoginViewController()
  {
  }


  @Override protected void init()
  {
    viewHandler = getViewHandler();
    viewModel = getViewModelFactory().getLoginViewModel();
    userNameTextField.textProperty().bindBidirectional(viewModel.getUsername());
    passwordTextField.textProperty().bindBidirectional(viewModel.getPassword());
    errorLabel.textProperty().bind(viewModel.getError());
  }

  public void reset()
  {
    viewModel.reset();
  }

  @FXML private void goToPassword()
  {
    passwordTextField.requestFocus();
  }

  @FXML private void onLogin()
  {
    //TODO
    /*

    try
    {
      if(viewModel.login())
        viewHandler.openView(View.CHAT);
    }
    catch (Exception ignored)
    {

    }*/
  }

  @FXML private void onSignUp()
  {
    //TODO
    //viewHandler.openView(View.SIGNUP);
  }
}
