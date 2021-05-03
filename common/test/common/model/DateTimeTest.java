package common.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeTest {
    private DateTime dateTime;
    private DateTime date;

    @BeforeEach
    void setUp() {
        dateTime = new DateTime(9,2,2001);
        date = new DateTime(9,10,2001);
    }

    @Test
    void yearsBetween(){
        int yb = DateTime.yearsBetween(dateTime);
        assertEquals(20, yb);
        yb = DateTime.yearsBetween(date);
        assertEquals(19, yb);
    }
}