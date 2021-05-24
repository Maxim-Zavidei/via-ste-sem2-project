package viewmodel;

import common.model.Order;
import common.model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;

public class OrdersDetailedView {
    private StringProperty quantity;
    private StringProperty name;

    public OrdersDetailedView(Product product, Integer integer) {

        quantity = new SimpleStringProperty(integer+"");
        name = new SimpleStringProperty(product.getName());


    }

    public StringProperty getNameProperty() {
        return name;
    }

    public StringProperty getQuantityProperty() {
        return quantity;
    }
}
