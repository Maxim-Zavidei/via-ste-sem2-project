package common.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
    }
    @Test
    public void createProduct(){
        /**Regular value*/
        product = new Product("1", 5, "Baklava", "Baklava is Balkan", 20);
        assertEquals("id='1', quantity=5, name='Baklava', description='Baklava is Balkan', price=20.0", product.toString());
        /**Null value*/
        /**Empty value*/
    }
    @Test
    public void getters(){
        /**Regular value*/
        product = new Product("1", 5, "Baklava", "Baklava is Balkan", 20);
        assertEquals("1", product.getId());
        assertEquals(5, product.getQuantity());
        assertEquals("Baklava", product.getName());
        assertEquals("Baklava is Balkan", product.getDescription());
        assertEquals(20.0, product.getPrice());
    }
}