package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import viewmodel.RegistrationViewModel;

public class RegistrationViewController extends ViewController {

    private ViewHandler viewHandler;
    private RegistrationViewModel viewModel;

    // FXML instance variables of the view.
    @FXML private Label errorLabel;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker birthdaySelector;
    @FXML private RadioButton maleRadioButton;
    @FXML private RadioButton femaleRadioButton;

    @Override
    protected void init() {
        viewHandler = getViewHandler();
        viewModel = getViewModelFactory().getRegistrationViewModel();

        // Bindings for the user interface elements.
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
        Bindings.bindBidirectional(emailField.textProperty(), viewModel.getEmailProperty());
        Bindings.bindBidirectional(passwordField.textProperty(), viewModel.getPasswordProperty());
        Bindings.bindBidirectional(firstNameField.textProperty(), viewModel.getFirstNameProperty());
        Bindings.bindBidirectional(lastNameField.textProperty(), viewModel.getLastNameProperty());
        Bindings.bindBidirectional(birthdaySelector.valueProperty(), viewModel.getBirthdayPickerProperty());
        maleRadioButton.selectedProperty().bindBidirectional(viewModel.maleGenderButtonProperty());
        femaleRadioButton.selectedProperty().bindBidirectional(viewModel.femaleGenderButtonProperty());
    }

    public void reset() {
        viewModel.reset();
    }

    @FXML
    private void register() {
        if (viewModel.register()) try {
            viewHandler.openView(View.AUTHENTICATION);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Could not register at this time. Try later.");
        }
    }

    @FXML
    private void cancelRegistration() {
        try {
            viewHandler.openView(View.AUTHENTICATION);
        } catch (Exception e) {
            viewModel.getErrorProperty().set("Could not cancel the registration at this time. Try later.");
        }
    }
}
