package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import viewmodel.CatalogViewModel;
import viewmodel.ProductViewModel;



public class CatalogViewController extends ViewController {

   @FXML public StackPane rootPane;
   @FXML public BorderPane borderPane;

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
        notificationProduct();
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

    public void notificationProduct(){

        //trying an image background
        Image image = new Image("file:assets/cakeArt.png");

        ImageInput imageInput = new ImageInput();
        imageInput.setX(250);
        imageInput.setY(100);
        imageInput.setSource(image);

        //blur.setInput(imageInput);

        BoxBlur blur = new BoxBlur(3, 3, 3);

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("Okay");
        JFXDialog dialog = new JFXDialog(rootPane,dialogLayout,JFXDialog.DialogTransition.CENTER);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> dialog.close());

        //unprofessional styling, but it works
        button.setStyle("-fx-background-color: #461d5e; -fx-text-fill: white;");
        dialog.setStyle("-fx-background-color: #ffb1b1cc;");
        dialogLayout.setStyle("-fx-background-color: #ffb1b1cc;");

        dialogLayout.setHeading(new Label("Check out the new cakes, NOW!"));
        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event) ->{
            borderPane.setEffect(null);

        });



        borderPane.setEffect(blur);


    }
}
