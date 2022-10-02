package datatypes;

import utils.Utils;

import java.util.Calendar;

/**
 * Represents a Calendar Date
 * @Author Michael Liu, Genfu Liu
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
    public int compareTo(Date date) {
        int years = Integer.compare(this.year, date.year);
        int months = Integer.compare(this.month, date.month);
        int days = Integer.compare(this.day, date.day);

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
}