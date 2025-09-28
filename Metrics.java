package com.assignment.metrics;

public class Metrics {
    public long comparisons = 0;
    public long swaps = 0;
    public long allocations = 0;
    public int maxDepth = 0;
    private int curDepth = 0;

    public void enterRecursion() {
        curDepth++;
        if (curDepth > maxDepth) maxDepth = curDepth;
    }
    public void exitRecursion() { curDepth--; }

    public void reset() {
        comparisons = swaps = allocations = 0;
        maxDepth = curDepth = 0;
    }

    public String toCSV() {
        return comparisons + "," + swaps + "," + allocations + "," + maxDepth;
    }
}
