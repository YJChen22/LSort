package LSort;

import java.util.concurrent.RecursiveAction;

public class MSDSort extends RecursiveAction {
    private final int[] arr;
    private final int begin, end, n, mod, r;

    MSDSort(int[] arr, int begin, int end, int n, int mod, int r) {
        this.arr = arr;
        this.mod = mod;
        this.begin = begin;
        this.end = end;
        this.n = n;
        this.r = r;
    }

    @Override
    protected void compute() {
        if (n < 0 || end - begin < r || mod < 2) {
            new MergeInsertionSort(arr, begin, end).invoke();
        } else {
            int[] count = new int[mod], start = new int[mod], result = new int[end - begin];
            for (int i = begin; i < end; ++i) {
                ++count[getDigit(arr[i], n)];
            }
            start[0] = 0;
            for (int i = 1; i < mod; ++i) {
                start[i] = start[i - 1] + count[i - 1];
            }
            for (int i = begin; i < end; ++i) {
                result[start[getDigit(arr[i], n)]++] = arr[i];
            }
            if (end - begin >= 0) System.arraycopy(result, 0, arr, begin, end - begin);
            for (int i = 0; i < mod; ++i) {
                if (count[i] != 0) {
                    new MSDSort(arr, begin + (i != 0 ? start[i - 1]: 0), begin + start[i], n - 1, mod, r).fork();
                }
            }
        }
    }



    private int getDigit(int i, int n) {
        for (int j = 0; j < n; ++j) {
            i >>= mod;
        }
        return i & (mod - 1);
    }
}
