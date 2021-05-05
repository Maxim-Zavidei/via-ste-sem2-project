package view;

import common.model.DateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.UserView;
import viewmodel.UserViewModel;

public class UserViewController extends ViewController
{
  /**FXML */
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

  /**Constructor*/
  public UserViewController(){}


  /**Initializer*/
  @Override protected void init()
  {
    viewHandler = getViewHandler();
    viewModel = getViewModelFactory().getEmployeeViewModel();
    this.usersTable.setItems(viewModel.getList());
    this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
    this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
    this.emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
    this.birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().getBirthDate());
    this.genderColumn.setCellValueFactory(cellData -> cellData.getValue().getGender());
    this.statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
    this.errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }

  @Override protected void reset()
  {
    usersTable.getSelectionModel().clearSelection();
    errorLabel.setStyle("-fx-text-fill:#000000");
    viewModel.reset();
  }
  /**FXML Methods*/
  @FXML private void makeEmployeeButton(){
    viewModel.makeEmployee(usersTable.getSelectionModel().getSelectedItem());
  }
  @FXML private void deleteUserButton(){
    viewModel.deleteUser(usersTable.getSelectionModel().getSelectedItem());
  }
  @FXML private void fireEmployeeButton(){
    viewModel.fireEmployee(usersTable.getSelectionModel().getSelectedItem());
  }
}
