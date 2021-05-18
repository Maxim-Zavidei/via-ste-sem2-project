package view;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.CatalogViewModel;
import viewmodel.ProductViewModel;

public class CatalogViewController extends ViewController {

    private ViewHandler viewHandler;
    private CatalogViewModel viewModel;

    // FXML instance variables of catalog table.
    @FXML private TableView<ProductViewModel> catalogTable;
    @FXML private TableColumn<ProductViewModel, Integer> catalogAvailabilityColumn;
    @FXML private TableColumn<ProductViewModel, String> catalogNameColumn;
    @FXML private TableColumn<ProductViewModel, String> catalogDescriptionColumn;
    @FXML private TableColumn<ProductViewModel, Double> catalogPriceColumn;

    // The rest FXML instance variables of the view.
    @FXML private Label usernameLabel;
    @FXML private Button manageProductsButton;
    @FXML private Button manageUsersButton;
    @FXML private Label errorLabel;

    @Override
    protected void init() {
        viewHandler = getViewHandler();
        viewModel = getViewModelFactory().getCatalogViewModel();

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

        // Bindings for the rest of the user interface elements.
        usernameLabel.textProperty().bind(viewModel.getUsernameProperty());
        manageProductsButton.visibleProperty().bind(viewModel.getShowProductManagementButtonProperty());
        manageUsersButton.visibleProperty().bind(viewModel.getShowUserManagementButtonProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
    }

    public void reset() {
        // Deselect any items upon reopening the window.
        catalogTable.getSelectionModel().clearSelection();
        viewModel.reset();
    }

    @FXML
    private void openBasketView() {
        try {
            viewHandler.openView(View.BASKET);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Could not manage basket at this time. Try later.");
        }
    }

    @FXML
    private void openProductManagementView() {
        try {
            viewHandler.openView(View.MANAGEPRODUCTS);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Could not manage products at this time. Try later.");
        }
    }

    @FXML
    private void openUserManagementView() {
        try {
            viewHandler.openView(View.USERS);
        } catch (Exception e) {
            viewModel.getErrorProperty().set(e.getMessage());
        }
    }

    @FXML
    private void addToBasket() {
        viewModel.addToBasket();
    }

    @FXML
    private void deauthenticate() {
        //model.clear basket
        if (!viewModel.deauthenticate()) {
            viewModel.getErrorProperty().set("!Could not deauthenticate the user.");
            return;
        }
        try {
            viewHandler.openView(View.AUTHENTICATION);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("!Could not logout at this time. Try later.");
        }
    }
}
