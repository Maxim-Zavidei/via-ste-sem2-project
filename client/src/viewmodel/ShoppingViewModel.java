package viewmodel;

import common.model.Product;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import java.util.stream.Collectors;

public class ShoppingViewModel {

    private Model model;

    // Instance variables for storing the products of the catalog table.
    private ObservableList<ProductViewModel> catalogList;
    private ObjectProperty<ProductViewModel> selectedCatalogProductProperty;

    // Instance variables for storing the products of the basket table.
    private ObservableList<ProductViewModel> basketList;
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
        // Magical code to initialize the catalog table with products in it upon opening the window for the first time.
        catalogList = FXCollections.observableArrayList(model.getCatalogOfProducts().stream().map(ProductViewModel::new).collect(Collectors.toList()));
        selectedCatalogProductProperty = new SimpleObjectProperty<>();
        basketList = FXCollections.observableArrayList();
        selectedBasketProductProperty = new SimpleObjectProperty<>();

        // Initialize the instance variables responsible for storing data of the other ui elements.
        errorProperty = new SimpleStringProperty("Sweeten Your life!");
        priceProperty = new SimpleStringProperty("0.00");
        discountProperty = new SimpleIntegerProperty(0);
        inputCouponProperty = new SimpleStringProperty("");
        finalPriceProperty = new SimpleStringProperty("0.00");
    }

    public void reset() {
        // Refresh the catalog list with all the available product every time the window reopens.
        catalogList.clear();
        model.getCatalogOfProducts().forEach((product) -> catalogList.add(new ProductViewModel(product)));
        // Deselect any selected items if window reopens.
        selectedCatalogProductProperty.set(null);
        selectedBasketProductProperty.set(null);
        errorProperty.set("Sweeten Your life!");
        updatePrices();
    }

    public ObservableList<ProductViewModel> getCatalogList() {
        return catalogList;
    }

    public ObservableList<ProductViewModel> getBasketList() {
        return basketList;
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
        basketList.add(new ProductViewModel(new Product(
            selectedCatalogProductViewModel.getIdProperty().getValue(),
            1,
            selectedCatalogProductViewModel.getNameProperty().getValue(),
            selectedCatalogProductViewModel.getDescriptionProperty().getValue(),
            selectedCatalogProductViewModel.getPriceProperty().getValue()
        )));
        updatePrices();
    }

    public void changeQuantity() {
        // TODO: Needs consultation from our ui designer on how it should work.
        updatePrices();
    }

    public boolean dropFromBasket() {
        ProductViewModel selectedBasketProductViewModel = selectedBasketProductProperty.get();
        if (selectedBasketProductViewModel == null) {
            errorProperty.set("!Please select a product from the basket to be removed.");
            return false;
        }
        selectedBasketProductProperty.set(null);
        basketList.remove(selectedBasketProductViewModel);
        updatePrices();
        return true;
    }

    public void clearBasket() {
        selectedBasketProductProperty.set(null);
        basketList.clear();
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

    public void cancelOrder() {
        // TODO: Needs consultation from our ui designer on how it should work.
        clearBasket();
    }

    private void updatePrices() {
        double preliminaryPrice = 0.0;
        for (ProductViewModel product : basketList) preliminaryPrice += product.getPriceProperty().getValue() * product.getQuantityProperty().getValue();
        priceProperty.set(String.format("%.2f", preliminaryPrice));
        int discount = discountProperty.getValue();
        finalPriceProperty.set(String.format("%.2f", discount == 0 ? preliminaryPrice : preliminaryPrice * discount / 100));
    }
}
