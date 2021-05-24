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
    private ProductManagementViewModel productManagementViewModel;
    private ProductEditingViewModel productEditingViewModel;

    // User management view models.
    private UserViewModel userViewModel;
    private UserManageViewModel userManageViewModel;

    //Order management view model
    private OrdersViewModel ordersViewModel;

    public ViewModelFactory(Model model) {
        this.authenticationViewModel = new AuthenticationViewModel(model);
        this.registrationViewModel = new RegistrationViewModel(model);
        this.catalogViewModel = new CatalogViewModel(model);
        this.basketViewModel = new BasketViewModel(model);

        ProductManagementViewState productManagementViewState = new ProductManagementViewState();
        this.productManagementViewModel = new ProductManagementViewModel(model, productManagementViewState);
        this.productEditingViewModel = new ProductEditingViewModel(model, productManagementViewState);

        UserManagementViewState userManagementViewState = new UserManagementViewState();
        this.userViewModel = new UserViewModel(model, userManagementViewState);
        this.userManageViewModel = new UserManageViewModel(model, userManagementViewState);

        this.ordersViewModel = new OrdersViewModel(model);
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

    public ProductManagementViewModel getProductManagementViewModel() {
        return productManagementViewModel;
    }

    public ProductEditingViewModel getProductEditingViewModel() {
        return productEditingViewModel;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public UserManageViewModel getUserManageViewModel() {
        return userManageViewModel;
    }

    public OrdersViewModel getOrdersViewModel() {
        return ordersViewModel;
    }
}
