import java.util.Arrays;
//import java.util.Comparator;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private LinkedList<LineSegment> segments;
    //    private ArrayList<ArrayList<Point>> ends;
    //    private ArrayList<Point> ends;
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) throw new IllegalArgumentException();
        for (Point p: points) {
            if (p == null) throw new IllegalArgumentException();
        }
        for(int i = 0; i < points.length; i++) {
            for(int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }




        segments = new LinkedList<>();
        Point[] points1 = points.clone();
        Arrays.sort(points1);
        if(points.length >= 4) {
            for(int i = 0; i < points.length; i++) {
                Point[] copy = points1.clone();
                Arrays.sort(copy, points[i].slopeOrder());
                int ptr = 1;
                Point anchor = copy[0];
                while(ptr < copy.length - 2){
                    double slope = anchor.slopeTo(copy[ptr]);
                    int front = ptr + 1;
                    while (front < copy.length && Double.compare(slope, anchor.slopeTo(copy[front])) == 0) {
                        front++;
                    }
                    front = front - 1;
                    if(front < copy.length && front - ptr >= 2 && copy[ptr].compareTo(anchor) > 0) {
                        segments.add(new LineSegment(anchor, copy[front]));
                    }
                    ptr = front + 1;
                }
            }
        }




/*
        //        ends = new ArrayList<>();

        //        Arrays.sort(points);
        for(int a = 0; a < points.length; a++){
            Point anchor = points[a];
            Point[] copy = points.clone();
            Arrays.sort(copy, new myComparator(points[a]));
            //find the one that is not the same as points[a]
            int ptr = 1;
            //            if (anchor.slopeTo(copy[ptr]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();

            //sort the rest based on the slope to points[a]
            while(ptr < copy.length - 2){
                double slope = anchor.slopeTo(copy[ptr]);
                int front = ptr + 1;
                while (front < copy.length && Double.compare(slope, anchor.slopeTo(copy[front])) == 0) {
                    front++;
                }
                front = front - 1;
                if(front < copy.length && front - ptr >= 2 && copy[ptr].compareTo(anchor) > 0) {
                    segments.add(new LineSegment(anchor, copy[front]));
                    //                    Point min = anchor;
                    //                    Point max = anchor;
                    //                    for(int i = ptr; i <= front; i++) {
                    //                        if(min.compareTo(copy[i]) > 0) min = copy[i];
                    //                        if(max.compareTo(copy[i]) < 0) max = copy[i];
                    //                    }
                    //                    addPair(min, max);
                }
                ptr = front + 1;
            }
        }

        //remove duplicates
        //        for(int i = 0; i < ends.size(); i++) {
        //            ArrayList<Point> tmp = ends.get(i);
        //            boolean notDup = true;
        //            for (int j = i + 1; j < ends.size(); j++) {
        //                if (ends.get(j).get(0).compareTo(tmp.get(0)) == 0 && ends.get(j).get(1).compareTo(tmp.get(1)) == 0) {
        //                    notDup = false;
        //                    break;
        //                }
        //            }
        //            if (notDup) {
        //                segments.add(new LineSegment(tmp.get(0), tmp.get(1)));
        //            }
        //        }
*/
    }
//    private class myComparator implements Comparator<Point> {
//        private Point current;
//        myComparator(Point p){
//            current = p;
//        }
//
//        @Override
//        public int compare(Point o1, Point o2) {
//            if (current.slopeOrder().compare(o1, o2) == 0) {
//                return o1.compareTo(o2);
//            }
//            return current.slopeOrder().compare(o1, o2);
//        }
//
//    }


    //    private void addPair(Point p1, Point p2) {
    //        boolean add = true;
    //        for(ArrayList<Point> al: ends) {
    //            Point front = al.get(0);
    //            Point end = al.get(1);
    //            if(p1.compareTo(front) == 0 && p2.compareTo(end) == 0 || p2.compareTo(front) == 0 && p1.compareTo(end) == 0) {
    //                add = false;
    //                break;
    //            }
    //        }
    //        if (add) {
    //            segments.add(new LineSegment(p1, p2));
    //            ArrayList<Point> pair = new ArrayList<>();
    //            pair.add(p1);
    //            pair.add(p2);
    //            ends.add(pair);
    //        }
    //    }

    public int numberOfSegments() {
        // the number of line segments
        return segments.size();
    }

    public LineSegment[] segments() {
        // the line segments
        return segments.toArray(new LineSegment[segments.size()]);
    }
    public static void main(String[] args) {
        //        Point[] points = new Point[6];
        //        points[0] = new Point(0, 0);
        //        points[1] = new Point(2, 4);
        //        points[2] = new Point(3, 6);
        //        points[3] = new Point(1, 3);
        //        points[4] = new Point(1, 2);
        //        points[5] = new Point(2, 6);
        //        FastCollinearPoints fcp = new FastCollinearPoints(points);
        //        for(LineSegment ls: fcp.segments()){
        //            System.out.println(ls.toString());
        //        }
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        //show all points
        for(Point p: points) {
            System.out.println(p.toString());
        }
        System.out.println("After sort");
        //        FastCollinearPoints collinear = new FastCollinearPoints(points);


        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}