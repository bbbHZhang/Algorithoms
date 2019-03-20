import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private LinkedList<LineSegment> segments;
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        //Corner cases. Throw a java.lang.IllegalArgumentException 
        //if the argument to the constructor is null, if any point in the array is null, 
        //or if the argument to the constructor contains a repeated point.
        if (points == null) throw new IllegalArgumentException();
        segments = new LinkedList<>();;
        for (int a = 0; a < points.length; a++) {
            if(points[a] == null) throw new IllegalArgumentException();
            for (int b = a + 1; b < points.length; b++) {
                if(points[b] == null) throw new IllegalArgumentException();
                if(points[a].equals(points[b])) throw new IllegalArgumentException();
                for(int c = b + 1; c < points.length; c++) {
                    if(points[c] == null) throw new IllegalArgumentException();
                    for (int d = c + 1; d < points.length; d++) {
                        if(points[d] == null) throw new IllegalArgumentException();
                        if(col(points[a], points[b], points[c], points[d])) {
                            segments.add(new LineSegment(max(points[a], points[b], points[c], points[d]), 
                                    min(points[a], points[b], points[c], points[d])));
                        }
                    }
                }
            }
        }
        
    }
    
    private boolean col(Point p1, Point p2, Point p3, Point p4) {
        return Double.compare(p1.slopeTo(p2), p1.slopeTo(p3)) == 0 && Double.compare(p1.slopeTo(p4), p1.slopeTo(p3)) == 0;
    }
    
    private Point max(Point p1, Point p2, Point p3, Point p4) {
        Point max = p1;
        if(max.compareTo(p2) < 0) max = p2;
        if(max.compareTo(p3) < 0) max = p3;
        if(max.compareTo(p4) < 0) max = p4;
        return max;
    }
    private Point min(Point p1, Point p2, Point p3, Point p4) {
        Point min = p1;
        if(min.compareTo(p2) > 0) min = p2;
        if(min.compareTo(p3) > 0) min = p3;
        if(min.compareTo(p4) > 0) min = p4;
        return min;
    }
    
    public           int numberOfSegments() {
        // the number of line segments
        return segments.size();
    }
    
    public LineSegment[] segments() {
        // the line segments
        return segments.toArray(new LineSegment[segments.size()]);
    }
    
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
