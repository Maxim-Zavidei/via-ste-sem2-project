package view;

import common.utility.converter.IntegerToString;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import viewmodel.ProductViewModel;
import viewmodel.ShoppingViewModel;

public class ShoppingViewController extends ViewController {

    private ViewHandler viewHandler;
    private ShoppingViewModel viewModel;

    // FXML instance variables of catalog table.
    @FXML private TableView<ProductViewModel> catalogTable;
    @FXML private TableColumn<ProductViewModel, Integer> catalogAvailabilityColumn;
    @FXML private TableColumn<ProductViewModel, String> catalogNameColumn;
    @FXML private TableColumn<ProductViewModel, String> catalogDescriptionColumn;
    @FXML private TableColumn<ProductViewModel, Double> catalogPriceColumn;

    // FXML instance variables of basket table.
    @FXML private TableView<ProductViewModel> basketTable;
    @FXML private TableColumn<ProductViewModel, Integer> basketQuantityColumn;
    @FXML private TableColumn<ProductViewModel, String> basketNameColumn;
    @FXML private TableColumn<ProductViewModel, Double> basketPriceColumn;
    @FXML private TableColumn<ProductViewModel, Number> basketCostColumn;

    // The rest FXML instance variables of the view.
    @FXML private Label errorLabel;
    @FXML private Label priceLabel;
    @FXML private Label discountLabel;
    @FXML private TextField inputCouponField;
    @FXML private Label finalPriceLabel;

    @Override
    protected void init() {
        viewHandler = getViewHandler();
        viewModel = getViewModelFactory().getShoppingViewModel();

        // Bindings for the catalog table.
        catalogAvailabilityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        catalogNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        catalogDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());
        catalogPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        // Links the catalog hash map of the view model to the displaying observable product list of the catalog table.
        // On any product additions or removals from the view model hash map the list would behave accordingly by adding or removing the same product.
        viewModel.getCatalogMap().addListener((MapChangeListener.Change<? extends String, ? extends ProductViewModel> change) -> {
            // First condition verifies the change was not triggered by a replacement of an already existing product with put() method called on the catalog hash map.
            if (change.wasRemoved() ^ change.wasAdded()) if (change.wasRemoved()) {
                catalogTable.getItems().remove(change.getValueRemoved());
            } else {
                catalogTable.getItems().add(change.getValueAdded());
            }
        });
        catalogTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> viewModel.setSelectedCatalogProductProperty(newVal));

        // Bindings for the basket table.
        // Responsible for handling the behaviour of the text fields when the user, for editing purposes, double clicks on the quantity of any product in the basket.
        basketQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return integer == null ? "1" : integer.toString();
            }

            @Override
            public Integer fromString(String s) {
                // The call to the method below handles all the check logic when the user tries to enter a new quantity value in the text field presented to him.
                return viewModel.updateQuantity(s);
            }
        }));
        basketQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        basketNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        basketPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        // Automatically updates the values in the total cost column for each product based on its current quantity and price.
        basketCostColumn.setCellValueFactory(cellData -> {
            ObjectProperty<Integer> cellProductQuantity = cellData.getValue().getQuantityProperty();
            ObjectProperty<Double> cellProductPrice = cellData.getValue().getPriceProperty();
            return Bindings.createDoubleBinding(() -> cellProductQuantity.getValue() * cellProductPrice.getValue(), cellProductQuantity, cellProductPrice);
        });
        // Links the basket hash map of the view model to the displaying observable product list of the basket table.
        // On any product additions or removals from the view model hash map the list would behave accordingly by adding or removing the same product.
        viewModel.getBasketMap().addListener((MapChangeListener.Change<? extends String, ? extends ProductViewModel> change) -> {
            // First condition verifies the change was not triggered by a replacement of an already existing product with put() method called on the basket hash map.
            if (change.wasRemoved() ^ change.wasAdded()) if (change.wasRemoved()) {
                basketTable.getItems().remove(change.getValueRemoved());
            } else {
                basketTable.getItems().add(change.getValueAdded());
            }
        });
        basketTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> viewModel.setSelectedBasketProductProperty(newVal));

        // Bindings for the rest of the user interface elements.
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
        priceLabel.textProperty().bind(viewModel.getPriceProperty());
        Bindings.bindBidirectional(discountLabel.textProperty(), viewModel.getDiscountProperty(), new IntegerToString());
        Bindings.bindBidirectional(inputCouponField.textProperty(), viewModel.getInputCouponProperty());
        finalPriceLabel.textProperty().bind(viewModel.getFinalPriceProperty());

        // Small snippet of code to change the color of the error label only when errors are displayed.
        viewModel.getErrorProperty().addListener((obs, oldVal, newVal) -> errorLabel.setStyle("-fx-text-fill:" + (newVal.charAt(0) == '!' ? "#FF0000" : "#000000")));
    }

    public void reset() {
        // Deselect any items upon reopening the window.
        catalogTable.getSelectionModel().clearSelection();
        basketTable.getSelectionModel().clearSelection();
        errorLabel.setStyle("-fx-text-fill:#000000");
        viewModel.reset();
    }

    @FXML
    private void addToBasket() {
        viewModel.addToBasket();
    }

    @FXML
    private void dropFromBasket() {
        if (viewModel.dropFromBasket()) basketTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void clearBasket() {
        basketTable.getSelectionModel().clearSelection();
        viewModel.clearBasket();
    }

    @FXML
    private void applyCoupon() {
        viewModel.applyCoupon();
    }

    @FXML
    private void placeOrder() {
        viewModel.placeOrder();
    }

    @FXML
    private void cancelOrder() {
        viewModel.cancelOrder();
    }
}
