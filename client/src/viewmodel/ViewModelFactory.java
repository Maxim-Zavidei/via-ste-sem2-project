package viewmodel;

import model.Model;
import viewmodel.viewstate.ProductManagementViewState;
import viewmodel.viewstate.UserManagementViewState;

public class ViewModelFactory {

    // Authentication & Registration view models.
    private AuthenticationViewModel authenticationViewModel;
    private RegistrationViewModel registrationViewModel;

    // Catalog & Basket view models.
    private CatalogViewModel catalogViewModel;
    private BasketViewModel basketViewModel;

    // Product management view models.
    private ManageProductsViewModel manageProductsViewModel;
    private AddProductPopUpViewModel addProductPopUpViewModel;
    private EditProductPopUpViewModel editProductPopUpViewModel;

    // User management view models.
    private UserViewModel userViewModel;
    private UserManageViewModel userManageViewModel;

    public ViewModelFactory(Model model) {
        this.authenticationViewModel = new AuthenticationViewModel(model);
        this.registrationViewModel = new RegistrationViewModel(model);
        this.catalogViewModel = new CatalogViewModel(model);
        this.basketViewModel = new BasketViewModel(model);

        ProductManagementViewState productManagementViewState = new ProductManagementViewState();
        this.manageProductsViewModel = new ManageProductsViewModel(model, productManagementViewState);
        this.addProductPopUpViewModel = new AddProductPopUpViewModel(model);
        this.editProductPopUpViewModel = new EditProductPopUpViewModel(model, productManagementViewState);

        UserManagementViewState userManagementViewState = new UserManagementViewState();
        this.userViewModel = new UserViewModel(model, userManagementViewState);
        this.userManageViewModel = new UserManageViewModel(model, userManagementViewState);
    }

    public AuthenticationViewModel getAuthenticationViewModel() {
        return authenticationViewModel;
    }

    public RegistrationViewModel getRegistrationViewModel() {
        return registrationViewModel;
    }

    public CatalogViewModel getCatalogViewModel() {
        return catalogViewModel;
    }

    public BasketViewModel getBasketViewModel() {
        return basketViewModel;
    }

    public ManageProductsViewModel getManageProductsViewModel() {
        return manageProductsViewModel;
    }

    public AddProductPopUpViewModel getAddProductPopUpViewModel() {
        return addProductPopUpViewModel;
    }

    public EditProductPopUpViewModel getEditProductPopUpViewModel() {
        return editProductPopUpViewModel;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public UserManageViewModel getUserManageViewModel() {
        return userManageViewModel;
    }
}
