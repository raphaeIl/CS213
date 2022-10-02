package utils;

import core.Member;
import datatypes.Date;

/**
 * Utilities class for random helper methods
 * @author Michael Liu, Genfu Liu
 */
public class Utils {

    public static double dobToAge(Date dob) {
        Date today = new Date();

        double year = today.getYear() - dob.getYear();
        double month = (today.getMonth() - dob.getMonth()) / (double)Date.MONTHS_IN_YEAR;

        return year + month;
    }

    public static String arrayToString(Object[] array) {
        String toString = "[";

        for (Object obj: array)
            toString += obj + ", ";

        toString = toString.substring(0, toString.length() - 2);
        toString += ']';

        return toString;
    }

    public static boolean arrayContains(int[] array, int element) {
        for (int e: array)
            if (e == element)
                return true;

        return false;
    }

    // sorting helper
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
