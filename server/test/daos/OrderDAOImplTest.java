package daos;

import common.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOImplTest {
    OrderDAO orderDAO;
    UserDAO userDAO;
    Order order;
    HashMap<Product, Integer> pr = new HashMap<>();
    DateTime dateTime = new DateTime();
    Customer c = new Customer("bob@gmail.com", "Aaaa1234", "Bob", "Bob", new DateTime(2, 3, 2001), 'M');
    String str = "Order 1 by bob@gmail.com made on 18/05/2021\nBaklava -> 2\nPain -> 6\n";

    @BeforeEach
    void setUp() {
        try {

            orderDAO = OrderDAOImpl.getInstance();
            Product product = new Product("1",3, "Baklava", "Baklava is very tasty", 2.5);
            Product product1 = new Product("2",4, "Pain au Chocolate", "nice", 5);
            pr.put(product, 2);
            pr.put(product1, 6);
            order = new Order("1", pr, c);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void create() {
        try {
            //userDAO.create(c);
            assertEquals(str, orderDAO.create(pr, dateTime, c, "pending", "").toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void update() {
    }

    @Test
    void updateProductOrder() {
    }

    @Test
    void delete() {
    }

    @Test
    void addToProductOrder() {
    }
}