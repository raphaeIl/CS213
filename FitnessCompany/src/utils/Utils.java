package utils;

import core.Member;
import datatypes.FitnessClass;
import datatypes.FitnessClassType;

public class Utils {

    public static boolean arrayContains(int[] array, int element) {
        for (int e: array)
            if (e == element)
                return true;

        return false;
    }

    public static FitnessClassType strToClassType(String fitnessClassType) {
        for (FitnessClassType type: FitnessClassType.values())
            if (type.toString().equals(fitnessClassType))
                return type;

        return null;
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
