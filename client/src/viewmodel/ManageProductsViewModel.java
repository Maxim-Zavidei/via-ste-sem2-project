package viewmodel;

import common.model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Model;

public class ManageProductsViewModel {

    private Model model;

    // Instance variables for storing the products of the catalog table.
    private ObservableMap<String, ProductViewModel> catalogMap;
    private ObjectProperty<ProductViewModel> selectedCatalogProductProperty;


    // Instance variables for linking and storing the other elements of the user interface.
    private StringProperty usernameProperty;
    private ObjectProperty<Boolean> showUserManagementButtonProperty;
    private StringProperty errorProperty;

    // Other helper instance variables.
    private boolean wasAuthenticatedUserQueried;

    public ManageProductsViewModel(Model model) {
        this.model = model;

        // Initialize the view model instance variables responsible for storing the data of the tables.
        catalogMap = FXCollections.observableHashMap();
        selectedCatalogProductProperty = new SimpleObjectProperty<>();

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
        selectedCatalogProductProperty.set(null);
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
        selectedCatalogProductProperty.set(productViewModel);
    }

    public ProductViewModel editProduct(){
        ProductViewModel selectedCatalogProductViewModel = selectedCatalogProductProperty.get();
        if (selectedCatalogProductViewModel == null) {
            errorProperty.set("!Please select a product from the catalog to be edited.");
            return null;
        }
        selectedCatalogProductProperty.set(null);
        return selectedCatalogProductViewModel;
    }

    public boolean deleteProduct() {
        ProductViewModel selectedCatalogProductViewModel = selectedCatalogProductProperty.get();
        if (selectedCatalogProductViewModel == null) {
            errorProperty.set("!Please select a product from the catalog to be removed.");
            return false;
        }
        selectedCatalogProductProperty.set(null);
        catalogMap.remove(selectedCatalogProductViewModel.getIdProperty().getValue());
        return true;
    }

    public boolean deauthenticate() {
        wasAuthenticatedUserQueried = false;
        return model.deauthenticate();
    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        System.out.println("Add triggered!!!");
//
//        Platform.runLater(() -> {
//            Product product = (Product) evt.getNewValue();
//            if ("Add".equals(evt.getPropertyName())) {
//                catalogMap.put(product.getId(), new ProductViewModel(product));
//                // for testing
//                System.out.println("Add triggered!!!");
//            }
//        });
//    }
}
