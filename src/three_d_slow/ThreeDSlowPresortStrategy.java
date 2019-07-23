package three_d_slow;

import interfaces.PresortStrategy;
import mainComponents.Point;
import mainComponents.Utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ThreeDSlowPresortStrategy implements PresortStrategy {
    @Override
    public List<List<Point>> presort(List<Point> points) {
        // Constants
        int X = 0;
        int Y = 1;
        int Z = 2;

        List<List<Point>> returnList = new ArrayList<>();
        List<Point> pointsSortedByX = new ArrayList<>();
        List<Point> pointsSortedByY = new ArrayList<>();
        List<Point> pointsSortedByZ = new ArrayList<>();

        //Handle x
        pointsSortedByX = points;
        pointsSortedByX.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                double value = (p1.getCoordinates()[X] - p2.getCoordinates()[X]);
                if (value < 0) {
                    return -1;
                } else if (value > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // set the index of the point of where it is in the x-sorted-list
        for (int i = 0; i < pointsSortedByX.size(); i++) {
            Point p = pointsSortedByX.get(i);
            p.addIndex(i);
        }

        // Handle for y
        pointsSortedByY.addAll(pointsSortedByX);
        pointsSortedByY.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                double value = (p1.getCoordinates()[Y] - p2.getCoordinates()[Y]);
                if (value < 0) {
                    return -1;
                } else if (value > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        // set the index of the point of where it is in the y-sorted-list
        for (int i = 0; i < pointsSortedByY.size(); i++) {
            Point p = pointsSortedByY.get(i);
            p.addIndex(i);
        }


        // Handle z
        // Handle for y
        pointsSortedByZ.addAll(pointsSortedByY);
        pointsSortedByZ.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                double value = (p1.getCoordinates()[Z] - p2.getCoordinates()[Z]);
                if (value < 0) {
                    return -1;
                } else if (value > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });


        // All points will have a list of indexes as indexes for [x,y]... z is not needed since we only divide on x
        returnList.add(pointsSortedByX);
        returnList.add(pointsSortedByY);
        returnList.add(pointsSortedByZ);

        Utility.checkSorting(returnList);

        return returnList;




    }
}
