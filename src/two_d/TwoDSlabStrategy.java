package two_d;

import interfaces.SlabStrategy;
import mainComponents.ClosestPair;
import mainComponents.ClosestPairLogicImpl;
import mainComponents.Point;
import mainComponents.Utility;

import java.util.ArrayList;
import java.util.List;

public class TwoDSlabStrategy implements SlabStrategy {
    @Override
    public List<Point> buildSlab(List<List<Point>> points, double delta, int medianIndex) {
        List<Point> slab = new ArrayList<>();
        List<Point> pointsSortedByY = points.get(1);
        double medianPointX = points.get(0).get(medianIndex).getCoordinates()[0];
        for (int i = 0; i < pointsSortedByY.size(); i++) {
            Point point = pointsSortedByY.get(i);
            double currentPointX = point.getCoordinates()[0];
            if (currentPointX >= (medianPointX - delta) && currentPointX <= (medianPointX + delta)) {
                slab.add(point);
            }
        }

        return slab;
    }

    @Override
    public ClosestPair traverseSlab(ClosestPair currentClosestPair, List<Point> slab, ClosestPairLogicImpl closestPairLogic) {
        int sparsityConstant = closestPairLogic.getSparsityConstant();
        int numberOfSlabPoints = slab.size();
        for (int i = 0; i < numberOfSlabPoints; i++) {
            Point currentPoint = slab.get(i);
            List<Point> pointsToConsider = closestPairLogic.getPointsToConsider(slab, sparsityConstant + 1, i);

            // Look the amount of the sparsity constant points ahead in a sorted order in the slab.
            for (Point point : pointsToConsider) {
                double distance = Utility.distance(currentPoint, point);
                if (distance < currentClosestPair.getDistanceBetweenPoints()) {
                    currentClosestPair = new ClosestPair(currentPoint, point);
                }
            }
        }

        return currentClosestPair;
    }
}
