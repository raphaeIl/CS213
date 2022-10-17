package utils;

import core.entity.Member;
import datatypes.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Utilities class for random helper methods
 * @author Michael Liu, Genfu Liu
 */
public class Utils {

    /**
     * Counts the number of lines in a text file
     * @param filePath the path of the text file
     * @return the number of lines the text file contains, -1 if the file was not found
     */
    public static int fileLines(String filePath) {
        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            return -1;
        }

        int lines = 0;
        while (scanner.hasNext()) {
            lines++;
            scanner.nextLine();
        }

        scanner.close();

        return lines;
    }

    /**
     * Calculates your age from your date of birth
     * @param dob your dob
     * @return your age (contains the decimal)
     */
    public static double dobToAge(Date dob) {
        Date today = new Date();

        double year = today.getYear() - dob.getYear();
        double month = (today.getMonth() - dob.getMonth()) / (double)Date.MONTHS_IN_YEAR;

        return year + month;
    }

    /**
     * Converts array to string for display purposes
     * @param array the array to be converted
     * @return the formatted string representation of the array
     */
    public static String arrayToString(Object[] array) {
        String toString = "[";

        for (Object obj: array)
            toString += obj + ", ";

        toString = toString.substring(0, toString.length() - 2);
        toString += ']';

        return toString;
    }

    /**
     * Check if an int array contains a specific element
     * @param array the array
     * @param element the element to be checked
     * @return if the array contains the element
     */
    public static boolean arrayContains(int[] array, int element) {
        for (int e: array)
            if (e == element)
                return true;

        return false;
    }

    /**
     * Check if an string array contains a speific elements
     * @param array the array
     * @param element the element to be checked
     * @return if the array contains the element
     */
    public static boolean arrayContains(String[] array, String element) {
        for (String e: array)
            if (e.equals(element))
                return true;

        return false;
    }

    /**
     * Sorts a Member array using Insertion Sort
     * @param array the Member array to be sorted
     * @param size the actual size of the array, since there might be null elements
     */
    public static void insertionSort(Member[] array, int size) {
        for (int i = 1; i < size; i++) {
            Member target = array[i];
            int currentIndex = i - 1;


            while (currentIndex >= 0 && array[currentIndex].compareTo(target) > 0) {
                array[currentIndex + 1] = array[currentIndex];

                currentIndex--;
            }

            array[currentIndex + 1] = target;
        }
    }
}
