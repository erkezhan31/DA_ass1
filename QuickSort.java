package com.assignment.sort;

import com.assignment.metrics.Metrics;
import com.assignment.util.Utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class QuickSort {
    private final Metrics m;
    private final Random rnd = ThreadLocalRandom.current();

    public QuickSort(Metrics m) { this.m = m; }

    public void sort(int[] a) {
        if (a == null || a.length <= 1) return;
        sortIterative(a, 0, a.length - 1);
    }

    private void sortIterative(int[] a, int lo, int hi) {
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{lo, hi});
        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            lo = cur[0];
            hi = cur[1];
            while (lo < hi) {
                int pivotIndex = lo + rnd.nextInt(hi - lo + 1);
                int pivotValue = a[pivotIndex];
                Utils.swap(a, pivotIndex, hi, m);
                int p = partition(a, lo, hi, pivotValue);
                int leftSize = p - lo;
                int rightSize = hi - p + 1;
                if (leftSize < rightSize) {
                    if (p <= hi) stack.push(new int[]{p, hi});
                    hi = p - 1;
                } else {
                    if (lo <= p - 1) stack.push(new int[]{lo, p - 1});
                    lo = p;
                }
            }
        }
    }

    private int partition(int[] a, int lo, int hi, int pivotVal) {
        int i = lo, j = lo;
        for (; j < hi; j++) {
            m.comparisons++;
            if (a[j] < pivotVal) {
                Utils.swap(a, i, j, m);
                i++;
            }
        }
        Utils.swap(a, i, hi, m);
        return i + 1;
    }
}
