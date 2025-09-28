package com.assignment;

import com.assignment.metrics.Metrics;
import com.assignment.sort.MergeSort;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {
    @Test
    void testSmallArray() {
        int[] arr = {5, 3, 1, 4, 2};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        Metrics m = new Metrics();
        new MergeSort(m).sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        Random r = new Random();
        int[] arr = r.ints(1000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);
        Metrics m = new Metrics();
        new MergeSort(m).sort(arr);
        assertArrayEquals(expected, arr);
    }
}
