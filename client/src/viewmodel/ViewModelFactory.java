package viewmodel;

import common.model.UserManagement;
import model.Model;

public class ViewModelFactory {

    private Model model;
    private UserManagement management;
    private ShoppingViewModel shoppingViewModel;
    private UserViewModel userViewModel;
    private ManageProductsViewModel manageProductsViewModel;

    public ViewModelFactory(Model model) {
        this.model = model;
        this.management = model.getManagement();
        this.shoppingViewModel = new ShoppingViewModel(model);
        this.userViewModel = new UserViewModel(management);
        this.manageProductsViewModel = new ManageProductsViewModel(model);
    }

    public UserViewModel getEmployeeViewModel()
    {
        return userViewModel;
    }

    public ShoppingViewModel getShoppingViewModel() {
        return shoppingViewModel;
    }

    public ManageProductsViewModel getManageProductsViewModel() {
        return manageProductsViewModel;
    }
}
