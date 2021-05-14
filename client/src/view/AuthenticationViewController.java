package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.AuthenticationViewModel;

public class AuthenticationViewController extends ViewController {

    private ViewHandler viewHandler;
    private AuthenticationViewModel viewModel;

    // FXML instance variables of the view.
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private Label errorLabel;

    @Override
    protected void init() {
        viewHandler = getViewHandler();
        viewModel = getViewModelFactory().getAuthenticationViewModel();

        // Bindings for the user interface elements.
        Bindings.bindBidirectional(emailField.textProperty(), viewModel.getEmailProperty());
        Bindings.bindBidirectional(passwordField.textProperty(), viewModel.getPasswordProperty());
        Bindings.bindBidirectional(errorLabel.textProperty(), viewModel.getErrorProperty());
    }

    public void reset() {
        viewModel.reset();
    }

    @FXML
    private void authenticate() {
        if (viewModel.authenticate()) try {
            viewHandler.openView(View.SHOPPING);
        } catch (Exception e) {
            errorLabel.textProperty().set("Could not authenticate at this time. Try later.");
        }
    }

    @FXML
    private void openRegistrationView() {
        try {
            viewHandler.openView(View.REGISTRATION);
        } catch (Exception e) {
            errorLabel.textProperty().set("Could not register at this time. Try later.");
        }
    }
}
