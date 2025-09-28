package com.assignment;

import com.assignment.metrics.Metrics;
import com.assignment.select.DeterministicSelect;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicSelectTest {
    @Test
    void testSelectMedian() {
        Random r = new Random();
        int[] arr = r.ints(200, -1000, 1000).toArray();
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int k = arr.length / 2;
        Metrics m = new Metrics();
        int result = new DeterministicSelect(m).select(arr.clone(), k);
        assertEquals(sorted[k], result);
    }
}
