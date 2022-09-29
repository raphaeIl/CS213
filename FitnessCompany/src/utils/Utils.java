package utils;

import core.Member;

/**
 * @Author Michael Genfu
 */

public class Utils {

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

    public static boolean objectArrayContains(Object[] array, Object element) {
        for (Object obj: array)
            if (obj != null && obj.equals(element))
                return true;
        return false;
    }

    // sorting helper
    public static void insertionSort(Member[] array, int size) {
//        for (int i = 1; i < size; i++) {
//            Member target = array[i];
//            int currentIndex = i - 1;
//
//
//            while (currentIndex >= 0 && array[currentIndex].compareTo(target) > 0) {
//                array[currentIndex + 1] = array[currentIndex];
//
//                currentIndex--;
//            }
//
//            array[currentIndex + 1] = target;
//        }

        for (int i = 1; i < size; ++i) {
            Member key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
