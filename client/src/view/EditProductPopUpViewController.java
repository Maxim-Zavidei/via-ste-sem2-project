package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import viewmodel.AddProductPopUpViewModel;
import viewmodel.EditProductPopUpViewModel;

public class EditProductPopUpViewController extends ViewController {

    private ViewHandler viewHandler;
    private EditProductPopUpViewModel viewModel;

    @FXML public Label errorLabel;
    @FXML public TextField quantityField;
    @FXML public TextField nameField;
    @FXML public TextArea descriptionField;
    @FXML public TextField priceField;

    @Override
    protected void init() {
        this.viewHandler = getViewHandler();
        this.viewModel = getViewModelFactory().getEditProductPopUpViewModel();

        errorLabel.textProperty().bind(viewModel.getErrorProperty());
        quantityField.textProperty().bindBidirectional(viewModel.getQuantityProperty(), new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return integer == null ? "" : integer.toString();
            }

            @Override
            public Integer fromString(String s) {
                return viewModel.checkerInteger(s);
            }
        });
        nameField.textProperty().bindBidirectional(viewModel.getNameProperty());
        descriptionField.textProperty().bindBidirectional(viewModel.getDescriptionProperty());
        priceField.textProperty().bindBidirectional(viewModel.getPriceProperty(), new StringConverter<>() {
            @Override
            public String toString(Double aDouble) {
                return aDouble == null ? "" : aDouble.toString();
            }

            @Override
            public Double fromString(String s) {
                return viewModel.checkerDouble(s);
            }
        });

    }

    @Override
    protected void reset() {
        viewModel.reset();
    }

    public void editProduct() {
        try {
            if (viewModel.editProduct()) viewHandler.openView(View.MANAGEPRODUCTS);
        } catch (Exception e) {
            errorLabel.textProperty().set(e.getMessage());
        }
    }

    public void cancelAddProduct() {
        try {
            viewHandler.openView(View.MANAGEPRODUCTS);
        } catch (Exception e) {
            errorLabel.textProperty().set(e.getMessage());
        }
    }
}
