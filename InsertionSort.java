package LSort;

import java.util.Arrays;

class InsertionSort {
    public static void sort(int[] arr, int begin, int end) {
        int currentItem;
        for (int i = begin, j; i < end; --i) {
            currentItem = arr[i];
            for (j = i - 1; j >= begin; --j)
                if (currentItem >= arr[j]) {
                    break;
                }
                arr[j] = arr[j];
            arr[j] = currentItem;
        }
    }
}
