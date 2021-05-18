package daos;

import common.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOImplTest {
    OrderDAO orderDAO;
    Order order;
    HashMap<Product, Integer> pr = new HashMap<>();
    DateTime dateTime = new DateTime();
    Customer c;
    String str = "Order 1 by bob@gmail.com made on 18/05/2021\nBaklava -> 2\nPain -> 6\n";

    @BeforeEach
    void setUp() {
        try {
            orderDAO = OrderDAOImpl.getInstance();
            Product product = new Product("1",30, "Baklava", "Baklava is very tasty", 3);
            Product product1 = new Product("2",23, "Pain", "pain", 2.5);
            pr.put(product, 2);
            pr.put(product1, 6);
            c = new Customer("bob@gmail.com", "123456", "Bob", "Bob", new DateTime(2,1,2001),'M');
            order = new Order("1", pr, c);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void create() {
        try {
            assertEquals(str, orderDAO.create(pr, dateTime, c).toString());
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