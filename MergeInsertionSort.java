package LSort;

import java.util.concurrent.RecursiveAction;

public class MergeInsertionSort extends RecursiveAction {
    private final int ubr = 500;
    private int[] arr;
    private int begin, end;

    public MergeInsertionSort(int[] arr, int begin, int end) {
        this.arr = arr;
        this.begin = begin;
        this.end = end;
    }

    private void merge(int begin, int mid, int end) {
        int[] mergedArray = new int[end - begin];
        int maIndex = 0;
        int lIndex = begin, rIndex = mid;
        while (!(lIndex == mid && rIndex == end)) {
            if (lIndex != mid && rIndex != end) {
                if (arr[lIndex] > arr[rIndex]) {
                    mergedArray[maIndex++] = arr[rIndex++];
                } else {
                    mergedArray[maIndex++] = arr[lIndex++];
                }
            } else if (lIndex != mid) {
                mergedArray[maIndex++] = arr[lIndex++];
            } else {
                mergedArray[maIndex++] = arr[rIndex++];
            }
        }
        if (maIndex >= 0) System.arraycopy(mergedArray, 0, arr, begin, maIndex);
    }

    @Override
    protected void compute() {
        if (end <= begin) {
            return ;
        }
        if (end - begin <= ubr) {
            InsertionSort.sort(arr, begin, end);
        } else {
            final int mid = (begin + end) / 2;
            RecursiveAction left = new MergeInsertionSort(arr, begin, mid);
            RecursiveAction right = new MergeInsertionSort(arr, mid, end);
            invokeAll(left, right);
            merge(begin, mid, end);
        }
    }
}
