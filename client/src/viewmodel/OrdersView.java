package viewmodel;

import common.model.DateTime;
import common.model.Order;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrdersView {
    private StringProperty id;
    private ObjectProperty<DateTime> date;
    private StringProperty email;
    private StringProperty comment;
    private StringProperty status;

    public OrdersView(Order order){
        id = new SimpleStringProperty(order.getId());
        date = new SimpleObjectProperty<>(order.getDate());
        email = new SimpleStringProperty(order.getCustomer().getEmail());
        comment = new SimpleStringProperty(order.getComment());
        status = new SimpleStringProperty(order.getStatus());

    }



    public StringProperty getId(){
        return id;
    }



    public ObjectProperty<DateTime> getDate() {
        return date;
    }



    public StringProperty getEmail() {
        return email;
    }



    public StringProperty getComment() {
        return comment;
    }



    public StringProperty getStatus() {
        return status;
    }
}
