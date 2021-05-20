package viewmodel;

import common.model.Product;
import common.model.User;
import common.utility.observer.event.ObserverEvent;
import common.utility.observer.listener.LocalListener;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Model;

public class CatalogViewModel implements LocalListener<String, Object> {

    private Model model;

    // Instance variables for storing the products of the catalog table.
    private ObservableMap<String, ProductViewModel> catalogMap;
    private ObjectProperty<ProductViewModel> selectedCatalogProductProperty;

    // Instance variables for linking and storing the other elements of the user interface.
    private StringProperty usernameProperty;
    private StringProperty basketButtonTitleProperty;
    private ObjectProperty<Boolean> showProductManagementButtonProperty;
    private ObjectProperty<Boolean> showUserManagementButtonProperty;
    private StringProperty errorProperty;

    // Other helper instance variables.
    private boolean wasAuthenticatedUserQueried;
    private ProductViewModel newNotificationProduct;
    private ObjectProperty<Boolean> showNotificationProperty;

    public CatalogViewModel(Model model) {
        this.model = model;

        // Initialize the view model instance variables responsible for storing the data of the tables.
        catalogMap = FXCollections.observableHashMap();
        selectedCatalogProductProperty = new SimpleObjectProperty<>();

        // Initialize the instance variables responsible for storing data of the other ui elements.
        usernameProperty = new SimpleStringProperty("");
        basketButtonTitleProperty = new SimpleStringProperty("Basket");
        showProductManagementButtonProperty = new SimpleObjectProperty<>(false);
        showUserManagementButtonProperty = new SimpleObjectProperty<>(false);
        errorProperty = new SimpleStringProperty("");

        // Initialize the rest of the instance variables.
        wasAuthenticatedUserQueried = false;
        newNotificationProduct = null;
        showNotificationProperty = new SimpleObjectProperty<>(false);

        model.addListener(this);
    }

    public void reset() {
        errorProperty.set("");

        // Deselect any selected items if window reopens.
        selectedCatalogProductProperty.set(null);

        // Refresh the catalog table with all the available products every time the window reopens.
        catalogMap.clear();
        try {
            model.getCatalogOfProducts().forEach((product) -> catalogMap.put(product.getId(), new ProductViewModel(product)));
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }

        // Configure properly the product and user management buttons and the username label based if the user is an employee or not.
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
            showProductManagementButtonProperty.set(isEmployee);
            showUserManagementButtonProperty.set(isEmployee);
            wasAuthenticatedUserQueried = true;
        }

        // Updates the number of products indicator in the basket button title.
        int tmp = model.getAllProductsInBasket().size();
        basketButtonTitleProperty.set(tmp == 0 ? "Basket" : "Basket (" + tmp + ")");
    }

    public ObservableMap<String, ProductViewModel> getCatalogMap() {
        return catalogMap;
    }

    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    public StringProperty getBasketButtonTitleProperty() {
        return basketButtonTitleProperty;
    }

    public ObjectProperty<Boolean> getShowProductManagementButtonProperty() {
        return showProductManagementButtonProperty;
    }

    public ObjectProperty<Boolean> getShowUserManagementButtonProperty() {
        return showUserManagementButtonProperty;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public ProductViewModel getNewNotificationProduct() {
        return newNotificationProduct;
    }

    public ObjectProperty<Boolean> getShowNotificationProperty() {
        return showNotificationProperty;
    }

    public void setSelectedCatalogProductProperty(ProductViewModel productViewModel) {
        selectedCatalogProductProperty.set(productViewModel);
    }

    public void addToBasket() {
        ProductViewModel selectedCatalogProductViewModel = selectedCatalogProductProperty.get();
        if (selectedCatalogProductViewModel == null) {
            errorProperty.set("Please select a product from the catalog to be added to the cart.");
            return;
        }
        try {
            model.addProductToBasket(new Product(
                    selectedCatalogProductViewModel.getIdProperty().getValue(),
                    1,
                    selectedCatalogProductViewModel.getNameProperty().getValue(),
                    selectedCatalogProductViewModel.getDescriptionProperty().getValue(),
                    selectedCatalogProductViewModel.getPriceProperty().getValue()
            ));
            basketButtonTitleProperty.set("Basket (" + model.getAllProductsInBasket().size() + ")");
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
    }

    public boolean deauthenticate() {
        wasAuthenticatedUserQueried = false;
        return model.deauthenticate();
    }

    @Override
    public void propertyChange(ObserverEvent<String, Object> event) {
        Platform.runLater(() -> {
            switch (event.getPropertyName()) {
                case "newProduct" : {
                    newNotificationProduct = new ProductViewModel((Product) event.getValue2());
                    showNotificationProperty.set(true);
                    break;
                }
            }
        });
    }
}
