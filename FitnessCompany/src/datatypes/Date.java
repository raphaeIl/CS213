package datatypes;

import test.Testcase;
import utils.Utils;

import java.util.Calendar;

/**
 * Represents a Calendar Date
 * @author Michael Liu, Genfu Liu
 */
public class Date implements Comparable<Date> {

    public static final int MONTHS_IN_YEAR = 12;
    public static final int SHORT_MONTH_DAYS = 30;
    public static final int LONG_MONTH_DAYS = 31;
    public static final int LEAP_YEAR_DAYS = 29;
    public static final int NON_LEAP_YEAR_DAYS = 28;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int[] SHORT_MONTHS = { 4, 6, 9, 11 }; // months that have 30 days

    private int year;
    private int month;
    private int day;

    public Date() { // create an object with today’s date (see Calendar class)
        this.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        this.year = Calendar.getInstance().get(Calendar.YEAR);
    }

    public Date(String date) { // take "mm/dd/yyyy" and create a Date object
        String[] dateArray = date.split("/");

        this.month = Integer.parseInt(dateArray[0]);
        this.day = Integer.parseInt(dateArray[1]);
        this.year = Integer.parseInt(dateArray[2]);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }

    @Override
    public int compareTo(Date other) {
        int years = Integer.compare(this.year, other.year);
        int months = Integer.compare(this.month, other.month);
        int days = Integer.compare(this.day, other.day);

        return years == 0 ? months == 0 ? days : months : years; // using ternary operator here, can change to if else for better clarity
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Date other &&
                this.compareTo(other) == 0;
    }

    public boolean isValid() { // check if a date is a valid calendar date
        if (year <= 0 || month <= 0 || month > MONTHS_IN_YEAR || day <= 0 || day > LONG_MONTH_DAYS)
            return false;

        if (Utils.arrayContains(SHORT_MONTHS, month)) {
            return day <= SHORT_MONTH_DAYS;
        } else if (month == 2) {
            if (isLeapYear(year))
                return day <= LEAP_YEAR_DAYS;
            else
                return day <= NON_LEAP_YEAR_DAYS;
        }

        return true;
    }

    public static boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0 && year % CENTENNIAL != 0)
            return true;
        else if (year % QUATERCENTENNIAL == 0)
            return true;
        else
            return false;
    }

    public static Date random() { // testing purposes random date
        return new Date(String.format("%d/%d/%d", (int)(Math.random() * 12 + 1), (int)(Math.random() * 31 + 1), 1800 + (int)(Math.random() * 400 + 1)));
    }

    /**
     * Testbed main for Date,
     */
    public static void main(String[] args) {
        Testcase[] isValidTestcases =   {
                                                new Testcase("An instance of Date with an invalid year, month, or day", false, null, new Date[] { new Date("10/1/-1"), new Date("-2/1/2022"), new Date("13/1/2022"), new Date("10/-1/2022"), new Date("10/32/2022") }),
                                                new Testcase("An instance of Date with a short month (4, 6, 9, 11), but with a day >= 31", false, null, new Date("11/31/2022")),
                                                new Testcase("An instance of Date with the month = 2, day > 28, and the year is a non-leap year", false, null, new Date("2/29/2100")),
                                                new Testcase("An instance of Date with the month = 2, day > 28, and the year is a leap year", true, null, new Date("2/29/2020")),
                                                new Testcase("An instance of Date with the month = 2, day = 29, and the year is a leap year", true, null, new Date("10/3/2022"))
                                            };

        Testcase[] compareToTestcases = {
                                                new Testcase("An instance of Date with the date: 10/3/2022, and compare it to a past date", 1, null, new Date("10/2/1997")),
                                                new Testcase("An instance of Date with the date: 10/3/2022, and compare it to another instance with the same date", 0, null, new Date("10/3/2022")),
                                                new Testcase("An instance of Date with the date: 10/3/2022, and compare it to a future date", -1, null, new Date("11/4/2050")),
                                            };

        System.out.println("\n\n-- Now testing: Date.isValid() --");
        for (int i = 0; i < isValidTestcases.length; i++) {
            Testcase current = isValidTestcases[i];
            System.out.printf("\nTest case #%d: %s:\n", i + 1, current.getDescription());

            for (Object testDate: current.getTestClasses()){
                boolean actualOutput = ((Date)testDate).isValid();
                System.out.printf("\tCase %-10s: isValid() returns %-5s: %s\n", testDate, actualOutput, (current.getExpectedOutput()).equals(actualOutput) ? "PASS" : "FAIL");
            }
        }
        System.out.println("\n\n-- Now testing: Date.compareTo(other) --");
        for (int i = 0; i < compareToTestcases.length; i++) {
            Testcase current = compareToTestcases[i];
            System.out.printf("\nTest case #%d: %s:\n", i + 1, current.getDescription());

            for (Object testDate: current.getTestClasses()) {
                int actualOutput = new Date("10/3/2022").compareTo((Date)testDate);
                System.out.printf("\tCase %-10s: compareTo() returns %-5s: %s\n", testDate, actualOutput, (current.getExpectedOutput()).equals(actualOutput) ? "PASS" : "FAIL");
            }
        }
    }

}