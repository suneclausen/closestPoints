package interfaces;

import mainComponents.ClosestPair;
import mainComponents.ClosestPairLogicImpl;
import mainComponents.Point;

import java.util.List;

public interface SlabStrategy {
    /**
     * Build slab will build the slab in the specific dimension and only find the points that lies within delta of the
     * dividing hyperplane defined by the median index. It will build the slab with multiple lists containing points in
     * a sorted order accordingly to the coordinate axis. Runs in O(n)
     * @param points: The points in a presorted order
     * @param delta: The distance seen so far defined by the closest pair of points found so far.
     * @param medianIndex: The indexvalue of the median point
     * @param splitAxis: This is only used for the 'fast' implementations
     * @param cutList: Only used with 'fast' algorithm. Otherwise there is given an empty list.
     * @return list of list with points in a sorted order according to a coordinate-axis
     */
    public List<List<Point>> buildSlab(List<List<Point>> points, double delta, int medianIndex, String splitAxis, List<Point> cutList);

    /**
     * Traverse the slab will go through the slab and check if there are any pair of points that are closer together
     * than the currentClosestPairOfPoints. Runs in O(n)
     * @param currentClosestPair
     * @param slab : The slab that needs traversal
     * @param closestPairLogic
     * @param splitAxis
     * @return current closest pair found so far in the algorithm.
     */
    public ClosestPair traverseSlab(ClosestPair currentClosestPair, List<List<Point>> slab, ClosestPairLogicImpl closestPairLogic, String splitAxis);
}
