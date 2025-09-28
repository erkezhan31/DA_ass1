package com.assignment.select;

import com.assignment.metrics.Metrics;
import com.assignment.util.Utils;

import java.util.Arrays;

public class DeterministicSelect {
    private final Metrics m;

    public DeterministicSelect(Metrics m) { this.m = m; }

    public int select(int[] a, int k) {
        if (k < 0 || k >= a.length) throw new IllegalArgumentException("k out of bounds");
        return selectRec(a, 0, a.length - 1, k);
    }

    private int selectRec(int[] a, int lo, int hi, int k) {
        m.enterRecursion();
        try {
            while (lo <= hi) {
                if (hi - lo + 1 <= 32) {
                    Arrays.sort(a, lo, hi + 1);
                    return a[lo + k];
                }
                int pivot = medianOfMedians(a, lo, hi);
                int[] part = partitionAround(a, lo, hi, pivot);
                int L = part[0], R = part[1];
                if (k < L - lo) {
                    hi = L - 1;
                } else if (k <= R - lo) {
                    return pivot;
                } else {
                    k = k - (R - lo + 1);
                    lo = R + 1;
                }
            }
            throw new RuntimeException("Unexpected state");
        } finally {
            m.exitRecursion();
        }
    }

    private int medianOfMedians(int[] a, int lo, int hi) {
        int n = hi - lo + 1;
        int numMedians = (n + 4) / 5;
        for (int i = 0; i < numMedians; i++) {
            int subLo = lo + i * 5;
            int subHi = Math.min(subLo + 4, hi);
            insertionSort(a, subLo, subHi);
            int medianIndex = subLo + (subHi - subLo) / 2;
            Utils.swap(a, lo + i, medianIndex, m);
        }
        int mid = (numMedians - 1) / 2;
        return selectRec(a, lo, lo + numMedians - 1, mid);
    }

    private void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int v = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.comparisons++;
                if (a[j] > v) {
                    a[j + 1] = a[j];
                    m.swaps++;
                    j--;
                } else break;
            }
            a[j + 1] = v;
        }
    }

    private int[] partitionAround(int[] a, int lo, int hi, int pivotVal) {
        int lt = lo, i = lo, gt = hi;
        while (i <= gt) {
            m.comparisons++;
            if (a[i] < pivotVal) {
                Utils.swap(a, lt++, i++, m);
            } else if (a[i] > pivotVal) {
                Utils.swap(a, i, gt--, m);
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }
}
