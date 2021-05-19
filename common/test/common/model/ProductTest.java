package common.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        product = new Product("1", 5, "Baklava", "Baklava is Balkan", 20);
    }
    @Test
    public void createProduct(){
        /**Regular value*/
        //product = new Product("1", 5, "Baklava", "Baklava is Balkan", 20);
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

    /**Test setters*/
    @Test
    void setNull(){
        /**ID*/
        assertThrows(IllegalArgumentException.class, () -> product.setId(null));
        assertThrows(IllegalArgumentException.class, () -> product.setId(""));
        assertThrows(NumberFormatException.class, () -> product.setId(" "));
        /**Product ID needs better checks*/

        /**Quantity*/
        //product.setQuantity(0);
        //assertEquals(0, product.getQuantity());
        /**Why the quantity can't be less than 1? if it is 0, then it is unavailable at the moment?*/

        /**Name*/
        assertThrows(IllegalArgumentException.class, () -> product.setName(null));
        assertThrows(IllegalArgumentException.class, () -> product.setName(""));
        //assertThrows(IllegalArgumentException.class, () -> product.setName(" "));
        /**Name needs better checks*/

        /**Description*/
        product.setDescription(null);
        assertEquals("", product.getDescription());
        product.setDescription("");
        assertEquals("", product.getDescription());
        product.setDescription(" ");
        assertEquals(" ", product.getDescription());
        /**Price*/
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(0));
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(0));
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(0));
        /**So the price can be null but the quantity not?*/
    }

    @Test
    void setOne(){
        /**ID*/
        product.setId("1");
        assertEquals("1", product.getId());
        //assertThrows(NumberFormatException.class, () -> product.setId("one"));
        /**Product ID needs better checks*/

        /**Quantity*/
        product.setQuantity(1);
        assertEquals(1, product.getQuantity());
        /**Name*/
        product.setName("n");
        assertEquals("n", product.getName());
        /**Description*/
        product.setDescription("n");
        assertEquals("n", product.getDescription());
        /**Price*/
        product.setPrice(1);
        assertEquals(1, product.getPrice());
    }

    @Test
    void setMany(){
        /**ID*/
        product.setId("123");
        assertEquals("123", product.getId());
        //assertThrows(IllegalArgumentException.class, () -> product.setId("one"));
        /**Product ID needs better checks*/

        /**Quantity*/
        product.setQuantity(123);
        assertEquals(123, product.getQuantity());
        /**Name*/
        product.setName("name");
        assertEquals("name", product.getName());
        /**Description*/
        product.setDescription("nsdsfdgfhgjfxtfhdytrsewertghjk,kjhreghjhghjm");
        assertEquals("nsdsfdgfhgjfxtfhdytrsewertghjk,kjhreghjhghjm", product.getDescription());
        /**Price*/
        product.setPrice(231);
        assertEquals(231, product.getPrice());
        product.setPrice(2.3);
        assertEquals(2.3, product.getPrice());
    }

    @Test
    void setBoundary(){
        /**ID*/
        //assertThrows(IllegalArgumentException.class, () -> product.setId("-1"));
        //assertThrows(IllegalArgumentException.class, () -> product.setId("0"));
        /**Product ID needs better checks*/

        /**Quantity*/
        assertThrows(IllegalArgumentException.class, () -> product.setQuantity(-1));
        /**Name
         * no more cases*/
        /**Description
         * no more cases*/
        /**Price*/
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(-1));
    }

    @Test
    void setExceptions(){
        /**ID*/
        //assertThrows(IllegalArgumentException.class, () -> product.setId("-1"));
        //assertThrows(IllegalArgumentException.class, () -> product.setId("0"));
        /**Product ID needs better checks*/

        /**Quantity*/
        assertThrows(IllegalArgumentException.class, () -> product.setQuantity(-1));
        assertThrows(IllegalArgumentException.class, () -> product.setQuantity(-23));
        /**Name
         * no more cases*/
        /**Description
         * no more cases*/
        /**Price*/
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(-1));
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(-26));
    }
}