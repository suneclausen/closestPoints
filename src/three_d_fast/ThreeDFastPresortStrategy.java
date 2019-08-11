package three_d_fast;

import interfaces.PresortStrategy;
import mainComponents.Point;
import mainComponents.Utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ThreeDFastPresortStrategy implements PresortStrategy {
    @Override
    public List<List<Point>> presort(List<Point> points) {
        // Constants indexes
        int X = 0;
        int Y = 1;
        int Z = 2;

        List<List<Point>> returnList = new ArrayList<>();
        List<Point> pointsSortedByX = new ArrayList<>();
        List<Point> pointsSortedByY = new ArrayList<>();
        List<Point> pointsSortedByZ = new ArrayList<>();

        //Handle x
        pointsSortedByX.addAll(points);
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
//            p.addIndex(i);
            p.setIndex("x", i);
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
            p.setIndex("y", i);
        }

        // Handle for z
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
        // set the index of the point of where it is in the z-sorted-list
        for (int i = 0; i < pointsSortedByZ.size(); i++) {
            Point p = pointsSortedByZ.get(i);
            p.setIndex("z", i);
        }

        //Since we have the possibility to divide an arbitrary axis we need to have the x and y set to have the index of z as well.
        pointsSortedByX.clear();
        pointsSortedByX.addAll(pointsSortedByZ);
        pointsSortedByX.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int value = p1.getIndex().get("x") - p2.getIndex().get("x");
                if (value < 0) {
                    return -1;
                } else if (value > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        pointsSortedByY.clear();
        pointsSortedByY.addAll(pointsSortedByX);
        pointsSortedByY.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int value = p1.getIndex().get("y") - p2.getIndex().get("y");
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
