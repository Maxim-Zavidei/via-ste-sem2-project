package viewmodel;

import common.model.Product;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import viewmodel.viewstate.ProductManagementViewState;

public class EditProductPopUpViewModel {

    private Model model;
    private ProductManagementViewState viewState;

    private ObjectProperty<Integer> quantity;
    private StringProperty name;
    private StringProperty description;
    private ObjectProperty<Double> price;
    private StringProperty errorProperty;

    public EditProductPopUpViewModel(Model model, ProductManagementViewState viewState) {
        this.model = model;
        this.viewState = viewState;

        quantity = new SimpleObjectProperty<>();
        name = new SimpleStringProperty();
        description = new SimpleStringProperty();
        price = new SimpleObjectProperty<>();
        errorProperty = new SimpleStringProperty("Edit Product");
    }

    public ObjectProperty<Integer> getQuantityProperty() {
        return quantity;
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public ObjectProperty<Double> getPriceProperty() {
        return price;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public void reset() {
        errorProperty.set("Edit Product");
        quantity.set(viewState.getSelectedProduct().getQuantityProperty().getValue());
        name.set(viewState.getSelectedProduct().getNameProperty().getValue());
        description.set(viewState.getSelectedProduct().getDescriptionProperty().getValue());
        price.set(viewState.getSelectedProduct().getPriceProperty().getValue());
    }

    // Might be a solution, but I really doubt Steffan would approve of a solution where it throws view models from one to another.
    public void set(ProductViewModel productViewModel) {
        this.name = productViewModel.getNameProperty();
        this.description = productViewModel.getDescriptionProperty();
        this.price = productViewModel.getPriceProperty();
        this.quantity = productViewModel.getQuantityProperty();
        //this.id = productViewModel.getIdProperty();
    }

    public boolean editProduct() {
        try {
            //Product product = new Product(id.get(), getQuantityProperty().get(), getNameProperty().get(), getDescriptionProperty().get(), getPriceProperty().get());
            model.updateProduct(new Product(
                    viewState.getSelectedProduct().getIdProperty().getValue(), // The id of the currently selected product in the product management table.
                    quantity.getValue(), // The quantity of the currently selected product in the product management table.
                    name.getValue(), // The name of the currently selected product in the product management table.
                    description.getValue(), // The description of the currently selected product in the product management table.
                    price.getValue() // The price of the currently selected product in the product management table.
            ));
            return true;
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }

    public Integer checkerInteger(String input) {
        try {
            int toReturn = Integer.parseInt(input);
            if (toReturn < 1) throw new NumberFormatException();
            return toReturn;
        } catch (NumberFormatException e) {
            errorProperty.set("Input a valid positive number.");
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
        return 1;
    }

    public Double checkerDouble(String input) {
        try {
            double toReturn = Double.parseDouble(input);
            if (toReturn < 1) throw new NumberFormatException();
            return toReturn;
        } catch (NumberFormatException e) {
            errorProperty.set("Input a valid positive number.");
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
        return 1.00;
    }
}
