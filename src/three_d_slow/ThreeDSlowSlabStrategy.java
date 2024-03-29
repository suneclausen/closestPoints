package three_d_slow;

import factories.TwoDFactory;
import interfaces.ClosestPairLogic;
import interfaces.SlabStrategy;
import mainComponents.ClosestPair;
import mainComponents.ClosestPairLogicImpl;
import mainComponents.Point;

import java.util.ArrayList;
import java.util.List;

public class ThreeDSlowSlabStrategy implements SlabStrategy {
    @Override
    public List<List<Point>> buildSlab(List<List<Point>> points, double delta, int medianIndex, String splitAxis, List<Point> cutList) { //Remember the medianIndex is only the index and not the x-value of it
        List<List<Point>> slab = new ArrayList<>();
        List<Point> pointsSortedByY = points.get(1);
        List<Point> pointsSortedByZ = points.get(2);

        // Get the x value of the median point - this is where the dividing line is.
        double medianPointX = points.get(0).get(medianIndex).getCoordinates()[0];

        List<Point> slabSortedByY = getSlabList(delta, pointsSortedByY, medianPointX);
        List<Point> slabSortedByZ = getSlabList(delta, pointsSortedByZ, medianPointX);

        slab.add(slabSortedByY);
        slab.add(slabSortedByZ);
        return slab;
    }

    @Override
    public ClosestPair traverseSlab(ClosestPair currentClosestPair, List<List<Point>> slab, ClosestPairLogicImpl closestPairLogic, String splitAxis) {
        // Format the points to work in the projected dimension accordingly to the datastructure defined in this algorithm .
        // Change indexes to have only one element, since this is expected in 2d. We make a copy of the slab such that the points from the 3d algorithm will still have all the index.

        List<List<Point>> slabCopy = new ArrayList<>();

        for (List<Point> listSortedBy : slab) {
            List<Point> toAdd = new ArrayList<>();
            for (Point point : listSortedBy) {
                try {
                    // This is hacky. The twoD expects to look at index "x" to get the index of where the split is.
                    // But in projected we let the "y" corrospond to the "x".
                    // This is the reason why we need a deep copy of the point, such that we do not mess with the logic for three d which needs both indexes.
                    Point p = point.clone();
                    p.setIndex("x", p.getIndex().get("y"));
                    toAdd.add(p);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            }
            slabCopy.add(toAdd);
        }

        // Run the slab in 2D - use twoDFactory.
        ClosestPairLogic cpTwoDLogic = new ClosestPairLogicImpl(closestPairLogic.getDimension(), new TwoDFactory());
        ClosestPair cpFrom2d = cpTwoDLogic.closestPair(slabCopy, "3D-Slow-TOP");

        if (cpFrom2d.getDistanceBetweenPoints() < currentClosestPair.getDistanceBetweenPoints()) {
            currentClosestPair = cpFrom2d;
        }

        return currentClosestPair;
    }


    // Helper: Gets the point of interest that lies within delta from the dividing line. TODO: Could be generalizes and used globally. Move then to Utility.
    private List<Point> getSlabList(double delta, List<Point> pointsSortedByACoordinate, double medianPointX) {
        List<Point> slabSortedByGivenCoordinate = new ArrayList<>();
        for (Point point : pointsSortedByACoordinate) {
            double currentPointXValue = point.getCoordinates()[0];
            if (currentPointXValue >= (medianPointX - delta) && currentPointXValue <= (medianPointX + delta)) {
                slabSortedByGivenCoordinate.add(point);
            }
        }
        return slabSortedByGivenCoordinate;
    }
}
