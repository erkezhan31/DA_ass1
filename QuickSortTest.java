package com.assignment;

import com.assignment.metrics.Metrics;
import com.assignment.sort.QuickSort;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {
    @Test
    void testCorrectness() {
        Random r = new Random();
        int[] arr = r.ints(5000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);
        Metrics m = new Metrics();
        new QuickSort(m).sort(arr);
        assertArrayEquals(expected, arr);
    }
}
