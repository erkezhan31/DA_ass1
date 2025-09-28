package com.assignment;

import com.assignment.closest.ClosestPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {
    @Test
    void testSimplePoints() {
        ClosestPair.Point[] pts = {
            new ClosestPair.Point(0, 0),
            new ClosestPair.Point(1, 1),
            new ClosestPair.Point(2, 2)
        };
        ClosestPair cp = new ClosestPair();
        double d = cp.closest(pts);
        assertEquals(Math.sqrt(2), d, 1e-9);
    }
}
