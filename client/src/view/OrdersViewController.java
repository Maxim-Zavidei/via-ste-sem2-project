package view;

import common.model.DateTime;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.OrdersDetailedView;
import viewmodel.OrdersView;
import viewmodel.OrdersViewModel;

public class OrdersViewController extends ViewController {


    private ViewHandler viewHandler;
    private OrdersViewModel viewModel;


    // FXML instance variables of order table.
    @FXML
    private TableView<OrdersView> orderTable;
    @FXML
    private TableColumn<OrdersView, String> orderIDColumn;
    @FXML
    private TableColumn<OrdersView, DateTime> orderDateColumn;
    @FXML
    private TableColumn<OrdersView, String> orderEmailColumn;
    @FXML
    private TableColumn<OrdersView, String> orderCommentColumn;
    @FXML
    private TableColumn<OrdersView, String> orderStatusColumn;

    // FXML instance variables of order detailed table.
    // not pronto
    @FXML
    private TableView<OrdersDetailedView> orderDetailedTable;
    @FXML
    private TableColumn<OrdersDetailedView, String> orderDetailedQuantityColumn;
    @FXML
    private TableColumn<OrdersDetailedView, String> orderDetailedNameColumn;

    // The rest FXML instance variables of the view.
    @FXML
    private Label usernameLabel;
    @FXML
    private ToggleButton toggleOrderButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Button basketButton;
    @FXML
    private Button manageProductsButton;
    @FXML
    private Button manageUsersButton;


    @Override
    protected void init() {
        viewHandler = getViewHandler();
        viewModel = getViewModelFactory().getOrdersViewModel();

        // Bindings for the order table.
        orderIDColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
        orderDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
        orderEmailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
        orderCommentColumn.setCellValueFactory(cellData -> cellData.getValue().getComment());
        orderStatusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
        orderTable.setItems(viewModel.getOrderTable());
        orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            viewModel.setSelectedOrderProperty(newVal);
            viewModel.setOrderDetailedTableProducts();
        });

        // Bindings for the order detailed table.
        orderDetailedQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        orderDetailedNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        orderDetailedTable.setItems(viewModel.getOrderDetailedTable());

        // Bindings for the rest of the user interface elements.
        usernameLabel.textProperty().bind(viewModel.getUsernameProperty());
        basketButton.textProperty().bind(viewModel.getBasketButtonTitleProperty());
        manageProductsButton.visibleProperty().bind(viewModel.getShowProductManagementButtonProperty());
        manageUsersButton.visibleProperty().bind(viewModel.getShowUserManagementButtonProperty());
        toggleOrderButton.selectedProperty().bindBidirectional(viewModel.getToggleButtonProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());


    }

    @Override
    protected void reset() {
        // Deselect any items upon reopening the window.
        orderTable.getSelectionModel().clearSelection();
        viewModel.reset();

    }

    @FXML
    private void openBasketView() {
        try {
            viewHandler.openView(View.BASKET);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Can not manage the basket at this time. Try later.");
        }
    }

    @FXML
    private void openProductManagementView() {
        try {
            viewHandler.openView(View.MANAGEPRODUCTS);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Can not manage products at this time. Try later.");
        }
    }

    @FXML
    private void openUserManagementView() {
        try {
            viewHandler.openView(View.USERS);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Can not manage users at this time. Try later.");
        }
    }

    @FXML
    private void openCatalogView() {
        try {
            viewHandler.openView(View.CATALOG);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Can not view the catalog at this time. Try later.");
        }
    }

    @FXML
    private void deauthenticate() {
        if (!viewModel.deauthenticate()) {
            viewModel.getErrorProperty().set("Could not deauthenticate the user.");
            return;
        }
        try {
            viewHandler.openView(View.AUTHENTICATION);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Could not logout at this time. Try later.");
        }
    }

    @FXML
    public void changeOrderTable(ActionEvent actionEvent) {
    }

    @FXML
    public void markAsCompleted(ActionEvent actionEvent) {
    }
}
