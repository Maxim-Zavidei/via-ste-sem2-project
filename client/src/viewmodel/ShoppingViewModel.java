package viewmodel;

import common.model.Product;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Model;

public class ShoppingViewModel {

    private Model model;

    // Instance variables for storing the products of the catalog table.
    private ObservableMap<String, ProductViewModel> catalogMap;
    private ObjectProperty<ProductViewModel> selectedCatalogProductProperty;

    // Instance variables for storing the products of the basket table.
    private ObservableMap<String, ProductViewModel> basketMap;
    private ObjectProperty<ProductViewModel> selectedBasketProductProperty;

    // Instance variables for linking and storing the other elements of the user interface.
    private StringProperty errorProperty;
    private StringProperty priceProperty;
    private IntegerProperty discountProperty;
    private StringProperty inputCouponProperty;
    private StringProperty finalPriceProperty;

    public ShoppingViewModel(Model model) {
        this.model = model;

        // Initialize the view model instance variables responsible for storing the data of the tables.
        catalogMap = FXCollections.observableHashMap();
        selectedCatalogProductProperty = new SimpleObjectProperty<>();
        basketMap = FXCollections.observableHashMap();
        selectedBasketProductProperty = new SimpleObjectProperty<>();

        // Initialize the instance variables responsible for storing data of the other ui elements.
        errorProperty = new SimpleStringProperty("Sweeten Your life!");
        priceProperty = new SimpleStringProperty("0.00");
        discountProperty = new SimpleIntegerProperty(0);
        inputCouponProperty = new SimpleStringProperty("");
        finalPriceProperty = new SimpleStringProperty("0.00");
    }

    public void reset() {
        // Refresh the catalog table with all the available products every time the window reopens.
        catalogMap.clear();
        try {
            model.getCatalogOfProducts().forEach((product) -> catalogMap.put(product.getId(), new ProductViewModel(product)));
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
        // Deselect any selected items if window reopens.
        selectedCatalogProductProperty.set(null);
        selectedBasketProductProperty.set(null);
        errorProperty.set("Sweeten Your life!");
        updatePrices();
    }

    public ObservableMap<String, ProductViewModel> getCatalogMap() {
        return catalogMap;
    }

    public ObservableMap<String, ProductViewModel> getBasketMap() {
        return basketMap;
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

    public void setSelectedCatalogProductProperty(ProductViewModel productViewModel) {
        selectedCatalogProductProperty.set(productViewModel);
    }

    public void setSelectedBasketProductProperty(ProductViewModel productViewModel) {
        selectedBasketProductProperty.set(productViewModel);
    }

    public void addToBasket() {
        ProductViewModel selectedCatalogProductViewModel = selectedCatalogProductProperty.get();
        if (selectedCatalogProductViewModel == null) {
            errorProperty.set("!Please select a product from the catalog to be added to the cart.");
            return;
        }
        String selectedCatalogProductId = selectedCatalogProductViewModel.getIdProperty().getValue();
        if (basketMap.get(selectedCatalogProductId) == null) {
            basketMap.put(selectedCatalogProductId, new ProductViewModel(new Product(
                    selectedCatalogProductId,
                    1,
                    selectedCatalogProductViewModel.getNameProperty().getValue(),
                    selectedCatalogProductViewModel.getDescriptionProperty().getValue(),
                    selectedCatalogProductViewModel.getPriceProperty().getValue()
            )));
            updatePrices();
        } else {
            errorProperty.set("!Product is already in the cart.");
        }
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
        // TODO: Needs further development of the system.
        clearBasket();
    }

    public boolean deauthenticate() {
        return model.deauthenticate();
    }

    public Integer updateQuantity(String newQuantity) {
        ProductViewModel selectedBasketProduct = selectedBasketProductProperty.getValue();
        try {
            int toReturn = Integer.parseInt(newQuantity);
            if (toReturn < 1) throw new NumberFormatException();
            int availableQuantity = catalogMap.get(selectedBasketProduct.getIdProperty().getValue()).getQuantityProperty().getValue();
            if (toReturn > availableQuantity) throw new IllegalArgumentException("!Unavailable stock. The quantity must be within " + availableQuantity + " units.");
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
