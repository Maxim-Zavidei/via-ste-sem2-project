package view;

import common.utility.converter.IntegerToString;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML private TableColumn<ProductViewModel, Double> basketCostColumn;

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
        catalogTable.setItems(viewModel.getCatalogList());
        catalogTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> viewModel.setSelectedCatalogProductProperty(newVal));

        // Bindings for the basket table.
        basketQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        basketNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        basketPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        basketCostColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantityProperty().getValue() * cellData.getValue().getPriceProperty().getValue()));
        basketTable.setItems(viewModel.getBasketList());
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
    private void changeQuantity() {
        viewModel.changeQuantity();
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
