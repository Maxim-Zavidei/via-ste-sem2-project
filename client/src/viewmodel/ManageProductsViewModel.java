package viewmodel;

import common.model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Model;
import viewmodel.viewstate.ProductManagementViewState;

public class ManageProductsViewModel {

    private Model model;
    private ProductManagementViewState viewState;

    // Instance variables for storing the products of the catalog table.
    private ObservableMap<String, ProductViewModel> catalogMap;

    // Instance variables for linking and storing the other elements of the user interface.
    private StringProperty usernameProperty;
    private ObjectProperty<Boolean> showUserManagementButtonProperty;
    private StringProperty errorProperty;

    // Other helper instance variables.
    private boolean wasAuthenticatedUserQueried;

    public ManageProductsViewModel(Model model, ProductManagementViewState viewState) {
        this.model = model;
        this.viewState = viewState;

        // Initialize the view model instance variables responsible for storing the data of the tables.
        catalogMap = FXCollections.observableHashMap();

        errorProperty = new SimpleStringProperty("");

        // Initialize the instance variables responsible for storing data of the other ui elements.
        usernameProperty = new SimpleStringProperty("");
        showUserManagementButtonProperty = new SimpleObjectProperty<>(false);
        errorProperty = new SimpleStringProperty("");

        // Initialize the rest of the instance variables.
        wasAuthenticatedUserQueried = false;
    }

    public void reset() {
        errorProperty.set("");
        // Refresh the catalog table with all the available products every time the window reopens.
        catalogMap.clear();
        try {
            model.getCatalogOfProducts().forEach((product) -> catalogMap.put(product.getId(), new ProductViewModel(product)));
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }

        // Deselect any selected items if window reopens.
        viewState.setSelectedProduct(null);
        // Configure properly the product and user management and the username label based if the user is an employee or not.
        if (!wasAuthenticatedUserQueried) {
            boolean isEmployee = false;
            try {
                User authenticatedUser = model.getAuthenticatedUser();
                isEmployee = authenticatedUser.isEmployee();
                usernameProperty.set((isEmployee ? "Employee" : "Customer") + " ● " + authenticatedUser.getFullName());
            } catch (Exception e) {
                usernameProperty.set("");
                errorProperty.set(e.getMessage());
            }
            showUserManagementButtonProperty.set(isEmployee);
            wasAuthenticatedUserQueried = true;
        }

    }

    public ObservableMap<String, ProductViewModel> getCatalogMap() {
        return catalogMap;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public void setSelectedCatalogProductProperty(ProductViewModel productViewModel) {
        viewState.setSelectedProduct(productViewModel);
    }

    public ProductViewModel editProduct(){
        ProductViewModel selectedCatalogProductViewModel = viewState.getSelectedProduct();
        if (selectedCatalogProductViewModel == null) {
            errorProperty.set("!Please select a product from the catalog to be edited.");
            return null;
        }
        viewState.setSelectedProduct(null);
        return selectedCatalogProductViewModel;
    }

    public boolean deleteProduct() {
        ProductViewModel selectedCatalogProductViewModel = viewState.getSelectedProduct();
        if (selectedCatalogProductViewModel == null) {
            errorProperty.set("!Please select a product from the catalog to be removed.");
            return false;
        }
        viewState.setSelectedProduct(null);
        catalogMap.remove(selectedCatalogProductViewModel.getIdProperty().getValue());
        return true;
    }

    public boolean deauthenticate() {
        wasAuthenticatedUserQueried = false;
        return model.deauthenticate();
    }
}
