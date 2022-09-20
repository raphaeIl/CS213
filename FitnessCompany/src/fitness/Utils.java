package fitness;

import java.util.Comparator;

public class Utils {

    // sorting helper
    public static void insertionSort(Comparable<Member>[] array, Comparator<Member> comparator) {
        for (int i = 1; i < array.length; i++) {
            Comparable<Member> target = array[i];
            int currentIndex = i - 1;


            while (currentIndex >= 0 && comparator.compare((Member) array[currentIndex], (Member) target) > 0) {
                array[currentIndex + 1] = array[currentIndex];

                currentIndex--;
            }

            array[currentIndex + 1] = target;
        }
    }
}
