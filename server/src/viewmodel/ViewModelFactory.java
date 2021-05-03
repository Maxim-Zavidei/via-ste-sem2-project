package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private LoginViewModel loginViewModel;
  private Model model;
  public ViewModelFactory(Model model){
    this.model = model;
    this.loginViewModel = new LoginViewModel(model);
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }
}
