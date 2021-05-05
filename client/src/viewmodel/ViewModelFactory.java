package viewmodel;

import common.model.UserManagement;
import model.Model;

public class ViewModelFactory {

    private Model model;
    private UserManagement management;
    private ShoppingViewModel shoppingViewModel;
    private UserViewModel userViewModel;

    public ViewModelFactory(Model model) {
        this.model = model;
        this.management = model.getManagement();
        this.shoppingViewModel = new ShoppingViewModel(model);
        this.userViewModel = new UserViewModel(management);
    }

    public UserViewModel getEmployeeViewModel()
    {
        return userViewModel;
    }

    public ShoppingViewModel getShoppingViewModel() {
        return shoppingViewModel;
    }
}
