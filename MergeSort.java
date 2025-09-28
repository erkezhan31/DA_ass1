package com.assignment.sort;

import com.assignment.metrics.Metrics;

public class MergeSort {
    private final Metrics m;
    private final int CUTOFF = 32;

    public MergeSort(Metrics m) { this.m = m; }

    public void sort(int[] a) {
        if (a == null || a.length <= 1) return;
        int[] buf = new int[a.length];
        m.allocations++;
        sortRec(a, buf, 0, a.length - 1);
    }

    private void sortRec(int[] a, int[] buf, int lo, int hi) {
        if (lo >= hi) return;
        m.enterRecursion();
        try {
            if (hi - lo + 1 <= CUTOFF) {
                insertion(a, lo, hi);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sortRec(a, buf, lo, mid);
            sortRec(a, buf, mid + 1, hi);
            int i = lo, j = mid + 1, k = lo;
            while (i <= mid && j <= hi) {
                m.comparisons++;
                if (a[i] <= a[j]) buf[k++] = a[i++];
                else buf[k++] = a[j++];
            }
            while (i <= mid) buf[k++] = a[i++];
            while (j <= hi) buf[k++] = a[j++];
            for (k = lo; k <= hi; k++) a[k] = buf[k];
        } finally {
            m.exitRecursion();
        }
    }

    private void insertion(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int v = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.comparisons++;
                if (a[j] > v) { a[j+1] = a[j]; m.swaps++; j--; }
                else break;
            }
            a[j+1] = v;
        }
    }
}
