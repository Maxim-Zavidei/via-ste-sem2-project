package viewmodel;

import common.model.Order;
import common.model.Product;
import common.model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class OrdersViewModel {

    private Model model;

    // Instance variables for storing the orders of the order table.
    private ObservableList<OrdersView> orderTable;
    private ObservableList<OrdersView> orderPendingTable;
    private ObjectProperty<OrdersView> selectedOrderProperty;

    // Instance variables for storing the products of the orders, i.e. order detailed table.
    private ObservableList<OrdersDetailedView> orderDetailedTable;

    // Instance variables for linking and storing the other elements of the user interface.
    private StringProperty usernameProperty;
    private StringProperty basketButtonTitleProperty;
    private ObjectProperty<Boolean> showProductManagementButtonProperty;
    private ObjectProperty<Boolean> showUserManagementButtonProperty;
    private ObjectProperty<Boolean> toggleButtonProperty;
    private StringProperty errorProperty;


    public OrdersViewModel(Model model) {
        this.model = model;

        // Initialize the view model instance variables responsible for storing the data of the tables.
        orderTable = FXCollections.observableArrayList();
        orderPendingTable = FXCollections.observableArrayList();
        selectedOrderProperty = new SimpleObjectProperty<>();

        //to figure out how to represent the products when selecting an order from order table
        orderDetailedTable = FXCollections.observableArrayList();


        // Initialize the instance variables responsible for storing data of the other ui elements.
        usernameProperty = new SimpleStringProperty("");
        basketButtonTitleProperty = new SimpleStringProperty("Basket");
        showProductManagementButtonProperty = new SimpleObjectProperty<>(false);
        showUserManagementButtonProperty = new SimpleObjectProperty<>(false);
        toggleButtonProperty = new SimpleObjectProperty<>(false);
        errorProperty = new SimpleStringProperty("");

    }

    public void reset() {
        errorProperty.set("");

        // Deselect any selected items if window reopens.
        selectedOrderProperty.set(null);

        if (!model.wasDataQueriedFor("Orders")) {
            orderTable.clear();
            try {
                model.getAllOrders().forEach(order -> orderTable.add(new OrdersView(order)));

            } catch (Exception e) {
                errorProperty.set(e.getMessage());
            }
            boolean isEmployee = false;
            try {
                User authenticatedUser = model.getAuthenticatedUser();
                isEmployee = authenticatedUser.isEmployee();
                usernameProperty.set((isEmployee ? "Employee" : "Customer") + " ● " + authenticatedUser.getFullName());
            } catch (Exception e) {
                usernameProperty.set("");
                errorProperty.set(e.getMessage());
            }
            showProductManagementButtonProperty.set(isEmployee);
            showUserManagementButtonProperty.set(isEmployee);

            // Updates the number of products indicator in the basket button title.
            int tmp = model.getAllProductsInBasket().size();
            basketButtonTitleProperty.set(tmp == 0 ? "Basket" : "Basket (" + tmp + ")");
        }



    }


    public ObservableList<OrdersView> getOrderTable() {
        return orderTable;
    }

    public ObservableList<OrdersView> getOrderPendingTable() {
        try {
            model.getAllOrders().forEach(order -> {
                if (order.getStatus().equals("pending")) {
                    orderPendingTable.add(new OrdersView(order));
                }
            });

        }catch (Exception e){
            errorProperty.set(e.getMessage());
        }
        return orderPendingTable;
    }

    public ObservableList<OrdersDetailedView> getOrderDetailedTable() {
        return orderDetailedTable;
    }

    public ObjectProperty<OrdersView> getSelectedOrderProperty() {
        return selectedOrderProperty;
    }

    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    public StringProperty getBasketButtonTitleProperty() {
        return basketButtonTitleProperty;
    }

    public ObjectProperty<Boolean> getShowProductManagementButtonProperty() {
        return showProductManagementButtonProperty;
    }

    public ObjectProperty<Boolean> getToggleButtonProperty() {
        return toggleButtonProperty;
    }


    public ObjectProperty<Boolean> getShowUserManagementButtonProperty() {
        return showUserManagementButtonProperty;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }


    public void setSelectedOrderProperty(OrdersView orderProperty) {
        selectedOrderProperty.set(orderProperty);


    }

    public void setOrderDetailedTableProducts(){
        orderDetailedTable.clear();
        try {
            ArrayList<Order> orders = model.getAllOrders();


            for (int i = 0; i < orders.size(); i++){
                if (orders.get(i).getId().equals(selectedOrderProperty.getValue().getId().getValue())){
                System.out.println(orders.get(i).getId()+"   "+selectedOrderProperty.getValue().getId());
                    for (HashMap.Entry<Product,Integer> entry : orders.get(i).getProducts().entrySet()) {
                        orderDetailedTable.add(new OrdersDetailedView(entry.getKey(),entry.getValue()));
                    }
                }
            }
        }catch (Exception e){
            errorProperty.set(e.getMessage());
        }

    }

    public void markOrderAsCompleted(String id)
    {
        if (!(selectedOrderProperty == null)){
            try {
                model.updateOrderStatus(id,"completed");
            } catch (Exception e){
                errorProperty.set(e.getMessage());
            }
        } else {
            errorProperty.set("Please select an order first!");
        }


    }

    public boolean deauthenticate() {
        return model.deauthenticate();
    }
}
