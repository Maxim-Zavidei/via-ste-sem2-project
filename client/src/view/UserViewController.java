package view;

import common.model.DateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.UserView;
import viewmodel.UserViewModel;

import java.util.Optional;

public class UserViewController extends ViewController
{
  @FXML private Label usernameLabel;
  @FXML private Button basketButton;
  /** FXML */
  @FXML private TableView<UserView> usersTable;
  @FXML private TableColumn<UserView, String> firstNameColumn;
  @FXML private TableColumn<UserView, String> lastNameColumn;
  @FXML private TableColumn<UserView, String> emailColumn;
  @FXML private TableColumn<UserView, DateTime> birthdayColumn;
  @FXML private TableColumn<UserView, String> genderColumn;
  @FXML private TableColumn<UserView, String> statusColumn;
  @FXML private Label errorLabel;

  /***/
  private ViewHandler viewHandler;
  private UserViewModel viewModel;

  /** Constructor */
  public UserViewController()
  {
  }

  /** Initializer */
  @Override protected void init()
  {
    viewHandler = getViewHandler();
    viewModel = getViewModelFactory().getUserViewModel();

    usernameLabel.textProperty().bind(viewModel.getUsernameProperty());
    basketButton.textProperty().bind(viewModel.getBasketButtonTitleProperty());

    this.usersTable.setItems(viewModel.getList());
    this.firstNameColumn
        .setCellValueFactory(cellData -> cellData.getValue().getFirstName());
    this.lastNameColumn
        .setCellValueFactory(cellData -> cellData.getValue().getLastName());
    this.emailColumn
        .setCellValueFactory(cellData -> cellData.getValue().getEmail());
    this.birthdayColumn
        .setCellValueFactory(cellData -> cellData.getValue().getBirthDate());
    this.genderColumn
        .setCellValueFactory(cellData -> cellData.getValue().getGender());
    this.statusColumn
        .setCellValueFactory(cellData -> cellData.getValue().getStatus());
    this.errorLabel.textProperty().bind(viewModel.getErrorProperty());
    try {
      viewModel.updateUsers();
    } catch (Exception e) {
      // Do nothing.
    }
  }

  @Override protected void reset()
  {
    usersTable.getSelectionModel().clearSelection();
    viewModel.reset();
  }

  /** FXML Methods */
  @FXML private void makeEmployeeButton()
  {
    try
    {
      if(usersTable.getSelectionModel().getSelectedItem().getEmail().get()!=null)
      viewModel.makeEmployee(usersTable.getSelectionModel().getSelectedItem().getEmail().get());
    }
    catch (Exception e)
    {
      viewModel.getErrorProperty().set("Please select a user to promote first!");
    }
  }

  @FXML private void removeUser()
  {
    try
    {
      if(usersTable.getSelectionModel().getSelectedItem().getEmail().get()!=null)
        if(confirmation(false))
        viewModel.deleteUser(usersTable.getSelectionModel().getSelectedItem().getEmail().get());
        else {
          usersTable.getSelectionModel().clearSelection();
          viewModel.getUserManagementViewState().setSelectedUser("");
        }
    }
    catch (Exception e)
    {
      viewModel.getErrorProperty().set("Please select a user to remove first!");
    }
  }

  @FXML private void fireEmployeeButton()
  {
    try
    {
      if(usersTable.getSelectionModel().getSelectedItem().getEmail().get()!=null)
        viewModel.fireEmployee(usersTable.getSelectionModel().getSelectedItem().getEmail().get());
    }
    catch (Exception e)
    {
      viewModel.getErrorProperty().set("Please select a user to resign first!");
    }
  }

  @FXML private void addEditUser()
  {
    try
    {
      String email = usersTable.getSelectionModel().getSelectedItem().getEmail().get();
      //System.out.println("Debug point 1");
      viewModel.getUserManagementViewState().setSelectedUser(email);
      viewModel.addEdit(email);
      if(confirmation(true)){
        //System.out.println("Selected user: "+ viewModel.getUserManagementViewState().getSelectedUser());
        viewHandler.openView(View.MANAGEUSERS);
      }

      else {
        usersTable.getSelectionModel().clearSelection();
        viewModel.getUserManagementViewState().setSelectedUser("");
      }
    }
    catch (Exception e)
    {
      viewModel.getUserManagementViewState().setSelectedUser("");
      viewModel.addEdit("");
      //System.out.println("Selected user is empty: "+ viewModel.getUserManagementViewState().getSelectedUser());
      try
      {
        viewHandler.openView(View.MANAGEUSERS);
      }
      catch (Exception exception)
      {
        viewModel.getErrorProperty().set("Something went wrong with modifying user");
      }
    }
  }

  @FXML private void deauthenticate()
  {
    //Max's code
    if (!viewModel.deauthenticate())
    {
      viewModel.getErrorProperty().set("!Could not deauthenticate the user.");
      return;
    }
    try
    {
      viewHandler.openView(View.AUTHENTICATION);
    }
    catch (Exception e)
    {
      viewModel.getErrorProperty()
          .set("!Could not logout at this time. Try later.");
    }
  }

  @FXML private void openBasketView()
  {
    try {
      viewHandler.openView(View.BASKET);
    } catch (Exception e) {
      viewModel.getErrorProperty().set("Could not open basket at this time. Try later.");
    }
  }
  @FXML private void openCatalogView(){
    try {
      viewHandler.openView(View.CATALOG);
    } catch (Exception e) {
      viewModel.getErrorProperty().set("Could not open catalog at this time. Try later.");
    }
  }
  @FXML private void openProductManagementView(){
    try {
      viewHandler.openView(View.MANAGEPRODUCTS);
    } catch (Exception e) {
      viewModel.getErrorProperty().set("Could not open manage products at this time. Try later.");
    }
  }
  private boolean confirmation(boolean action){
    int index = usersTable.getSelectionModel().getSelectedIndex();
    if (index < 0 || index >= usersTable.getItems().size()){
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    if(action)
    alert.setHeaderText("Are you sure you want to edit this user?");
    else alert.setHeaderText("Are you sure you want to delete this user?");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent())&&(result.get()==ButtonType.OK);
  }

  @FXML public void openOrdersView() {
    try {
      viewHandler.openView(View.ORDERS);
    } catch (Exception e) {
      viewModel.getErrorProperty().set("Could not open orders at this time. Try later.");
    }
  }
}
