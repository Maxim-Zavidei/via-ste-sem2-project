package view;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.ManageProductsViewModel;
import viewmodel.ProductViewModel;

import java.io.IOException;

public class ProductsViewController extends ViewController {

    private ViewHandler viewHandler;
    private ManageProductsViewModel viewModel;

    // FXML instance variables of catalog table.
    @FXML
    private TableView<ProductViewModel> catalogTable;
    @FXML
    private TableColumn<ProductViewModel, Integer> catalogAvailabilityColumn;
    @FXML
    private TableColumn<ProductViewModel, String> catalogNameColumn;
    @FXML
    private TableColumn<ProductViewModel, String> catalogDescriptionColumn;
    @FXML
    private TableColumn<ProductViewModel, Double> catalogPriceColumn;

    @FXML
    private Label errorLabel;


    @Override
    protected void init() {
        viewHandler = getViewHandler();
        viewModel = getViewModelFactory().getManageProductsViewModel();

        // Bindings for the catalog table.
        catalogAvailabilityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        catalogDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());
        catalogNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
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

        errorLabel.textProperty().bind(viewModel.getErrorProperty());

        // Small snippet of code to change the color of the error label only when errors are displayed.
        viewModel.getErrorProperty().addListener((obs, oldVal, newVal) -> errorLabel.setStyle("-fx-text-fill:" + (newVal.charAt(0) == '!' ? "#FF0000" : "#000000")));

    }

    @Override
    protected void reset() {
        catalogTable.getSelectionModel().clearSelection();
        viewModel.reset();

    }

    @FXML
    public void addProduct() {
        try {
            viewHandler.openView(View.POPUPPRODUCTS);
        } catch (IOException e) {
            errorLabel.textProperty().setValue(e.getMessage());
        }

    }

    @FXML
    public void deleteProduct() {
        if (viewModel.deleteProduct()) catalogTable.getSelectionModel().clearSelection();

    }

    @FXML
    public void cancelManagement() {
        // TODO: Needs further development of the system.
    }
}
