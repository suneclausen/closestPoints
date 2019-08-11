package three_d_fast.two_d_helper_for_fast_3d;

import interfaces.SlabStrategy;
import mainComponents.ClosestPair;
import mainComponents.ClosestPairLogicImpl;
import mainComponents.Point;
import two_d.TwoDSlabStrategy;

import java.util.ArrayList;
import java.util.List;

public class TwoDHelperSlabStrategy implements SlabStrategy {
    @Override
    public List<List<Point>> buildSlab(List<List<Point>> points, double delta, int medianIndex, String splitAxis, List<Point> cutList) {
        List<Point> pointsSortedByX = points.get(0);
        List<Point> pointsSortedByY = points.get(1);

        List<List<Point>> returnList = new ArrayList<>();
        List<Point> slab = new ArrayList<>();

        if (splitAxis.equals("x")) {
            //The Y coordinate is corrosponding to the x in this logic
            double medianPointX = pointsSortedByX.get(medianIndex).getCoordinates()[1]; //Y = 1
            for (Point point : pointsSortedByY) {
                double currentPointX = point.getCoordinates()[1];
                if (currentPointX >= (medianPointX - delta) && currentPointX <= (medianPointX + delta)) {
                    slab.add(point);
                }
            }
        }
        if (splitAxis.equals("y") || splitAxis.equals("z")) {
            //The Y coordinate is corrosponding to the x in this logic
            double medianPointX = pointsSortedByX.get(medianIndex).getCoordinates()[0];
            for (Point point : pointsSortedByY) {
                double currentPointX = point.getCoordinates()[0];
                if (currentPointX >= (medianPointX - delta) && currentPointX <= (medianPointX + delta)) {
                    slab.add(point);
                }
            }
        }

        returnList.add(slab);
        return returnList;

    }

    @Override
    public ClosestPair traverseSlab(ClosestPair currentClosestPair, List<List<Point>> slab, ClosestPairLogicImpl closestPairLogic, String splitAxis) {
        if (slab.get(0).size() == 0) {
            return currentClosestPair;
        }

        // This is the normal traversal of slabs, which is already implemented in 2d strategies.
        TwoDSlabStrategy normalTraversal = new TwoDSlabStrategy();
        return normalTraversal.traverseSlab(currentClosestPair, slab, closestPairLogic, splitAxis);
    }
}
