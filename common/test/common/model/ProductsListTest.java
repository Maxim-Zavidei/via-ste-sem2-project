package common.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductsListTest {
    ProductsList productsList;

    @BeforeEach
    void setUp() {
        productsList = new ProductsList();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct() {
        /**Regular value*/
        productsList.addProduct(new Product("1", 5, "Baklava", "Baklava is Balkan", 20));
       // assertEquals(productsList.);
    }

    @Test
    void removeProduct() {
    }

    @Test
    void testRemoveProduct() {
    }

    @Test
    void getProduct() {
    }

    @Test
    void getProducts() {
    }
}