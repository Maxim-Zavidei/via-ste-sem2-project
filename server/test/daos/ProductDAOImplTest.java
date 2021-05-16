package daos;

import common.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOImplTest {
    Product product;
    ProductDAO productDAO;

    @BeforeEach
    void setUp() {
        try {
            productDAO = ProductDAOImpl.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        try {
            assertEquals("id='1', quantity=3, name='Baklava', description='Baklava is very tasty', price=2.5", productDAO.create(3, "Baklava", "Baklava is very tasty", 2.5).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void read() {
        try {
            assertEquals("[id='1', quantity=3, name='Baklava', description='Baklava is very tasty', price=2.5]", productDAO.read().toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}