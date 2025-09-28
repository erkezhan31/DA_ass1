package com.assignment.closest;

import java.util.*;

public class ClosestPair {
    public static class Point {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public double closest(Point[] pts) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;
        Point[] px = pts.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Point[] py = pts.clone();
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return Math.sqrt(closestRec(px, py, 0, px.length - 1));
    }

    private double dist2(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return dx * dx + dy * dy;
    }

    private double closestRec(Point[] px, Point[] py, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= 3) {
            double d2 = Double.POSITIVE_INFINITY;
            for (int i = lo; i <= hi; i++)
                for (int j = i + 1; j <= hi; j++)
                    d2 = Math.min(d2, dist2(px[i], px[j]));
            return d2;
        }
        int mid = lo + (hi - lo) / 2;
        double midx = px[mid].x;
        List<Point> pyl = new ArrayList<>(), pyr = new ArrayList<>();
        for (Point p : py) {
            if (p.x <= midx) pyl.add(p);
            else pyr.add(p);
        }
        double dl = closestRec(px, pyl.toArray(new Point[0]), lo, mid);
        double dr = closestRec(px, pyr.toArray(new Point[0]), mid + 1, hi);
        double d = Math.min(dl, dr);
        List<Point> strip = new ArrayList<>();
        double sqrtD = Math.sqrt(d);
        for (Point p : py) if (Math.abs(p.x - midx) <= sqrtD) strip.add(p);
        double best = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && j <= i + 7; j++) {
                best = Math.min(best, dist2(strip.get(i), strip.get(j)));
            }
        }
        return best;
    }
}
