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
    public List<List<Point>> buildSlab(List<List<Point>> points, double delta, int medianIndex) {
        List<List<Point>> returnList = new ArrayList<>();
        List<Point> slab = new ArrayList<>();
        List<Point> pointsSortedByY = points.get(1);
        double medianPointX = points.get(0).get(medianIndex).getCoordinates()[0];
        for (Point point : pointsSortedByY) {
            double currentPointX = point.getCoordinates()[0];
            if (currentPointX >= (medianPointX - delta) && currentPointX <= (medianPointX + delta)) {
                slab.add(point);
            }
        }

        returnList.add(slab);
        return returnList;
    }

    @Override
    public ClosestPair traverseSlab(ClosestPair currentClosestPair, List<List<Point>> slab, ClosestPairLogicImpl closestPairLogic) {
        int sparsityConstant = closestPairLogic.getSparsityConstant();
        List<Point> theSlab = slab.get(0);  // get(0) is because the slab in 2D only contains one list
        int numberOfSlabPoints = theSlab.size();
        for (int i = 0; i < numberOfSlabPoints; i++) {
            Point currentPoint = theSlab.get(i);
            List<Point> pointsToConsider = closestPairLogic.getPointsToConsider(theSlab, sparsityConstant + 1, i);

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
