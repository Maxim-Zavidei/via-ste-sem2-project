package viewmodel;

import common.model.Product;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class AddProductPopUpViewModel {
    private Model model;

    private StringProperty id;
    private ObjectProperty<Integer> quantity;
    private StringProperty name;
    private StringProperty description;
    private ObjectProperty<Double> price;

    private StringProperty errorProperty;

    public AddProductPopUpViewModel(Model model) {
        this.model = model;

        //id = new SimpleStringProperty();
        quantity = new SimpleObjectProperty<>();
        name = new SimpleStringProperty();
        description = new SimpleStringProperty();
        price = new SimpleObjectProperty<>();

        errorProperty = new SimpleStringProperty("Add Product");
    }

//    public StringProperty getIdProperty() {
//        return id;
//    }

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

    public void reset(){
        quantity.set(null);
        name.set("");
        description.set("");
        price.set(null);
    }
    public void addProduct(){
        Product product = new Product("123", getQuantityProperty().get(), getNameProperty().get(),getDescriptionProperty().get(),getPriceProperty().get());
        model.addProduct(product);

    }



}
