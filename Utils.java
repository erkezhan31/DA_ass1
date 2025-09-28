package com.assignment.util;

import com.assignment.metrics.Metrics;

public class Utils {
    public static void swap(int[] a, int i, int j, Metrics m) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        if (m != null) m.swaps++;
    }
    public static void swapObj(Object[] a, int i, int j) {
        Object t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
