package test;

import datatypes.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    public void test_invalid_date() {
        Date date = new Date("10/1/-1");

        assertFalse(date.isValid());

        boolean expectedOutput = false;
        boolean actualOuput = date.isValid();

        assertEquals(expectedOutput, actualOuput);
    }

    @Test
    public void test_something_else() {

    }
}