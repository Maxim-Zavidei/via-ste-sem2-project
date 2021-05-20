package view;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.EditProductPopUpViewModel;
import viewmodel.ManageProductsViewModel;
import viewmodel.ProductViewModel;

public class ProductsViewController extends ViewController {

    private ViewHandler viewHandler;
    private ManageProductsViewModel viewModel;

    // FXML instance variables of catalog table.
    @FXML private TableView<ProductViewModel> catalogTable;
    @FXML private TableColumn<ProductViewModel, Integer> catalogAvailabilityColumn;
    @FXML private TableColumn<ProductViewModel, String> catalogNameColumn;
    @FXML private TableColumn<ProductViewModel, String> catalogDescriptionColumn;
    @FXML private TableColumn<ProductViewModel, Double> catalogPriceColumn;

    // The rest FXML instance variables of the view.
    @FXML private Label errorLabel;

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

        // Bindings for the rest of the user interface elements.
        errorLabel.textProperty().bind(viewModel.getErrorProperty());

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
        } catch (Exception e) {
            errorLabel.textProperty().setValue(e.getMessage());
        }
    }

    @FXML
    public void editProduct() {
        try {
            // I don't want to judge anything.
            // Please don't do this.
            // Don't throw view models from here to there like that, that's the highway to spaghetti code.
            // Use the view state to transfer the selected product to another window.
            // Also because Steffan might have a hearth attack xDDD
//            ProductViewModel pvm = viewModel.editProduct();
//            if(pvm != null)
//            {
//                getViewModelFactory().getEditProductPopUpViewModel().set(pvm);
                viewHandler.openView(View.EDITPRODUCTS);
//            }


        } catch (Exception e) {
            errorLabel.textProperty().setValue(e.getMessage());
        }
    }

    @FXML
    public void deleteProduct() {
        if (viewModel.deleteProduct()) catalogTable.getSelectionModel().clearSelection();

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
    private void openCatalogView() {
        try {
            viewHandler.openView(View.CATALOG);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Could not manage basket at this time. Try later.");
        }
    }


    @FXML
    private void openUserManagementView() {
        try {
            viewHandler.openView(View.USERS);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Could not open manage users at this time. Try later.");
        }
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
