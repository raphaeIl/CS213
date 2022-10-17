package test;

import datatypes.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test class for Date.isValid()
 * @author Michael Liu, Genfu Liu
 */
class DateTest {

    @Test
    public void add() {
        Date date = new Date("1/1/2002");
        date = date.add(1, 0);

        assertEquals(new Date("1/1/2003"), date);
    }

    /**
     * An instance of Date with an invalid year, month, or day should return false
     */
    @Test
    public void invalid_date_returns_false() {
        // 5 invalid test cases
        Date[] dates = { new Date("10/1/-1"), // invalid year (<= 0)
                         new Date("-2/1/2022"), // invalid month (<= 0)
                         new Date("13/1/2022"), // invalid month (> 12)
                         new Date("10/-1/2022"), // invalid day (<= 0)
                         new Date("10/32/2022") }; // invalid day (> 31)

        for (Date date: dates)
            assertFalse(date.isValid());
    }

    /**
     * An instance of Date with a short month (4, 6, 9, 11), but with a day >= 31 should return false
     */
    @Test
    public void day_in_short_month_should_be_less_than_thirtyone() {
        // test case: short month has more than 30 days is invalid
        Date date = new Date("11/31/2022");

        assertFalse(date.isValid());
    }

    /**
     * An instance of Date with the month = 2, day > 28, and the year is a non-leap year is invalid
     */
    @Test
    public void february_days_in_nonleap_should_be_less_than_or_equal_to_28() {
        // test case: a day in a non-leap year february that is > 28 is invalid
        Date date = new Date("2/29/2100");

        assertFalse(date.isValid());
    }

    /**
     * An instance of Date with the month = 2, day = 29, and the year is a leap year is valid
     */
    @Test
    public void max_february_days_in_leap_year_should_be_29() {
        // test case: a day in a leap year february with a day of 29 is valid
        Date date = new Date("2/29/2020");

        assertTrue(date.isValid());
    }

    /**
     * Create an instance of Date with a valid year, month and day is valid
     */
    @Test
    public void valid_date_returns_true() {
        // test case: any normal date is valid
        Date date = new Date("10/3/2022");

        assertTrue(date.isValid());
    }
}