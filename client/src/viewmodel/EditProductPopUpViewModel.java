package viewmodel;

import common.model.Product;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class EditProductPopUpViewModel {

    private Model model;

    private StringProperty id;
    private ObjectProperty<Integer> quantity;
    private StringProperty name;
    private StringProperty description;
    private ObjectProperty<Double> price;
    private StringProperty errorProperty;

    public EditProductPopUpViewModel(Model model) {
        this.model = model;

        //id = new SimpleStringProperty();
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
       quantity.set(null);
        name.set("");
        description.set("");
        errorProperty.set("Edit Product");
        price.set(null);
    }

    public void set(ProductViewModel productViewModel){
        this.name = productViewModel.getNameProperty();
        this.description = productViewModel.getDescriptionProperty();
        this.price = productViewModel.getPriceProperty();
        this.quantity = productViewModel.getQuantityProperty();
        this.id = productViewModel.getIdProperty();
    }

    public void editProduct() {
        try {
            Product product = new Product(id.get(), getQuantityProperty().get(), getNameProperty().get(), getDescriptionProperty().get(), getPriceProperty().get());
             model.updateProduct(product);
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }

    }

    public Integer checkerInteger(String input) {
        try {
            int toReturn = Integer.parseInt(input);
            if (toReturn < 1) throw new NumberFormatException();
            return toReturn;
        } catch (NumberFormatException e) {
            errorProperty.set("!Input a valid positive number.");
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
            errorProperty.set("!Input a valid positive number.");
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
        return 1.00;
    }
}
