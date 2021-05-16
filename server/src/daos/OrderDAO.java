package daos;

import common.model.Customer;
import common.model.DateTime;
import common.model.Order;
import common.model.Product;

import java.sql.SQLException;
import java.util.HashMap;

public interface OrderDAO {
    Order create(HashMap<Product, Integer> products, DateTime date, Customer customer) throws SQLException;
    void update() throws  SQLException;
    void delete() throws SQLException;
    void addToProductOrder(HashMap<Product, Integer> products, String orderId) throws SQLException;
}
