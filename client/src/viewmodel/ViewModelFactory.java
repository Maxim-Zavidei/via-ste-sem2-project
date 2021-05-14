package viewmodel;

import common.model.UserManagement;
import model.Model;

public class ViewModelFactory {

    private AuthenticationViewModel authenticationViewModel;
    private RegistrationViewModel registrationViewModel;
    private ShoppingViewModel shoppingViewModel;
    private ManageProductsViewModel manageProductsViewModel;
    private AddProductPopUpViewModel addProductPopUpViewModel;
    private UserViewModel userViewModel;
    private UserManagement management;

    public ViewModelFactory(Model model) {
        this.authenticationViewModel = new AuthenticationViewModel(model);
        this.registrationViewModel = new RegistrationViewModel(model);
        this.shoppingViewModel = new ShoppingViewModel(model);
        this.userViewModel = new UserViewModel(management);
        this.manageProductsViewModel = new ManageProductsViewModel(model);
        this.addProductPopUpViewModel = new AddProductPopUpViewModel(model);
    }

    public AuthenticationViewModel getAuthenticationViewModel() {
        return authenticationViewModel;
    }

    public RegistrationViewModel getRegistrationViewModel() {
        return registrationViewModel;
    }

    public ShoppingViewModel getShoppingViewModel() {
        return shoppingViewModel;
    }

    public ManageProductsViewModel getManageProductsViewModel() {
        return manageProductsViewModel;
    }

    public AddProductPopUpViewModel getAddProductPopUpViewModel() {
        return addProductPopUpViewModel;
    }

    public UserViewModel getEmployeeViewModel() {
        return userViewModel;
    }
}
