package viewmodel;

import common.model.User;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Model;

public class BasketViewModel {

    private Model model;

    // Instance variables for storing the products of the basket table.
    private ObservableMap<String, ProductViewModel> basketMap;
    private ObjectProperty<ProductViewModel> selectedBasketProductProperty;

    // Instance variables for linking and storing the other elements of the user interface.
    private StringProperty usernameProperty;
    private ObjectProperty<Boolean> showProductManagementButtonProperty;
    private ObjectProperty<Boolean> showUserManagementButtonProperty;
    private StringProperty errorProperty;
    private StringProperty priceProperty;
    private IntegerProperty discountProperty;
    private StringProperty inputCouponProperty;
    private StringProperty finalPriceProperty;

    // Other helper instance variables.
    private boolean wasAuthenticatedUserQueried;

    public BasketViewModel(Model model) {
        this.model = model;

        // Initialize the view model instance variables responsible for storing the data of the table.
        basketMap = FXCollections.observableHashMap();
        selectedBasketProductProperty = new SimpleObjectProperty<>();

        // Initialize the instance variables responsible for storing data of the other ui elements.
        usernameProperty = new SimpleStringProperty("");
        showProductManagementButtonProperty = new SimpleObjectProperty<>(false);
        showUserManagementButtonProperty = new SimpleObjectProperty<>(false);
        errorProperty = new SimpleStringProperty("");
        priceProperty = new SimpleStringProperty("0.00");
        discountProperty = new SimpleIntegerProperty(0);
        inputCouponProperty = new SimpleStringProperty("");
        finalPriceProperty = new SimpleStringProperty("0.00");

        // Initialize the rest of the instance variables.
        wasAuthenticatedUserQueried = false;
    }

    public void reset() {
        errorProperty.set("");
        // Deselect any selected items if window reopens.
        selectedBasketProductProperty.set(null);

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
            showProductManagementButtonProperty.set(isEmployee);
            showUserManagementButtonProperty.set(isEmployee);
            wasAuthenticatedUserQueried = true;
        }

        updatePrices();
    }

    public ObservableMap<String, ProductViewModel> getBasketMap() {
        return basketMap;
    }

    public StringProperty getUsernameProperty() {
        return usernameProperty;
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

    public StringProperty getPriceProperty() {
        return priceProperty;
    }

    public IntegerProperty getDiscountProperty() {
        return discountProperty;
    }

    public StringProperty getInputCouponProperty() {
        return inputCouponProperty;
    }

    public StringProperty getFinalPriceProperty() {
        return finalPriceProperty;
    }

    public void setSelectedBasketProductProperty(ProductViewModel productViewModel) {
        selectedBasketProductProperty.set(productViewModel);
    }

    public boolean dropFromBasket() {
        ProductViewModel selectedBasketProductViewModel = selectedBasketProductProperty.get();
        if (selectedBasketProductViewModel == null) {
            errorProperty.set("!Please select a product from the basket to be removed.");
            return false;
        }
        selectedBasketProductProperty.set(null);
        basketMap.remove(selectedBasketProductViewModel.getIdProperty().getValue());
        updatePrices();
        return true;
    }

    public void clearBasket() {
        selectedBasketProductProperty.set(null);
        basketMap.clear();
        updatePrices();
    }

    public void applyCoupon() {
        String couponCode = inputCouponProperty.get();
        if (couponCode == null || couponCode.isEmpty()) {
            errorProperty.set("!Please enter a valid coupon code.");
            return;
        }
        // TODO: Needs further development of the system.
        // Some method to get the discount value based on the coupon code.
        // discountProperty.set(someMethod);
        updatePrices();
    }

    public void placeOrder() {
        clearBasket();
    }

    public boolean deauthenticate() {
        wasAuthenticatedUserQueried = false;
        return model.deauthenticate();
    }

    public Integer updateQuantity(String newQuantity) {
        ProductViewModel selectedBasketProduct = selectedBasketProductProperty.getValue();
        try {
            int toReturn = Integer.parseInt(newQuantity);
            if (toReturn < 1) throw new NumberFormatException();
            //int availableQuantity = catalogMap.get(selectedBasketProduct.getIdProperty().getValue()).getQuantityProperty().getValue();
           // if (toReturn > availableQuantity) throw new IllegalArgumentException("!Unavailable stock. The quantity must be within " + availableQuantity + " units.");
            basketMap.get(selectedBasketProduct.getIdProperty().getValue()).setQuantity(toReturn);
            updatePrices();
            return toReturn;
        } catch (NumberFormatException e) {
            errorProperty.set("!Input a valid positive number.");
        } catch (IllegalArgumentException e) {
            errorProperty.set(e.getMessage());
        }
        return selectedBasketProduct.getQuantityProperty().getValue();
    }

    private void updatePrices() {
        final double[] preliminaryPrice = {0.0};
        basketMap.forEach((key, value) -> preliminaryPrice[0] += value.getPriceProperty().getValue() * value.getQuantityProperty().getValue());
        priceProperty.set(String.format("%.2f", preliminaryPrice[0]));
        int discount = discountProperty.getValue();
        finalPriceProperty.set(String.format("%.2f", discount == 0 ? preliminaryPrice[0] : preliminaryPrice[0] * discount / 100));
    }
}
