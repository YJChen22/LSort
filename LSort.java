package LSort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class LSort {
    private final int ub = 1000;

    private int r, mod;
    private final int[] arr;

    public LSort(int[] source, boolean insertionSort, int mod) {
        final int N = source.length;
        r = (int) Math.sqrt(N);
        r = r > ub ? r : ub;
        if (mod == 0) {
            this.mod = N / ub + 1;
        } else {
            this.mod = mod;
        }

        this.mod = (int) Math.ceil(Math.log(this.mod));

        arr = source;
        if (insertionSort || N < ub) {
            InsertionSort.sort(arr, 0, arr.length);
        } else {
            ForkJoinPool.commonPool().invoke(new MSDSort(arr, 0, arr.length, getMSD(0, arr.length), this.mod, r));
        }
    }

    private int getMSD(int begin, int end) {
        if (mod < 2) {
            return 0;
        }
        int result = -1;
        for (int i = begin; i < end; ++i) {
            int count = 0, t = arr[i];
            while (t >> mod != 0) {
                t >>= mod;
                ++count;
            }
            if (count > result) {
                result = count;
            }
        }
        return result;
    }

    public int[] getResult() {
        return arr;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] a = new int[1000];
        for (int i = 0; i < 1000; ++i) {
            a[i] = random.nextInt(10000000);
            System.out.printf("%d ", a[i]);
        }
        System.out.println();
        LSort lsort = new LSort(a, false, 0);
        for (int i = 0; i < 1000; ++i) {
            System.out.printf("%d ", a[i]);
        }
        System.out.println();
    }
}
