package two_d;

import interfaces.PresortStrategy;
import mainComponents.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TwoDPresortStrategy implements PresortStrategy {
    @Override
    public List<List<Point>> presort(List<Point> points) {
        // Constants
        int X = 0;
        int Y = 1;

        // Returned element
        List<List<Point>> returnList = new ArrayList<>();

        //Sort by x
        List<Point> sortedByX = points;
        sortedByX.sort(new Comparator<Point>() {
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
        for (int i = 0; i < sortedByX.size(); i++) {
            Point p = sortedByX.get(i);
            p.setIndex(i);
        }

        // Sort by y and keep the index
        List<Point> sortedByY = new ArrayList<>();
        sortedByY.addAll(sortedByX);
        Collections.sort(sortedByY, new Comparator<Point>() {
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

        returnList.add(sortedByX);
        returnList.add(sortedByY);

        return returnList;
    }
}
