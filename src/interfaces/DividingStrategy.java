package interfaces;

import mainComponents.Point;

import java.util.List;

public interface DividingStrategy {
    /**
     * Divide the list of points accordingly to the given median index, such that we get a list of points to the left and a list of points to the right of the median index,
     * where none of the points are duplicated in the set.
     * @param points : The points given as input to the closestPair method in CLosestPairLogicImpl.
     * @param medianIndex : The medianIndex in the list where the dividing line will be at. Note that it is index and not the x-value.
     * @param splitAxis: Only for when doing the fast implementation. Indicates what coordinate axis to do the dividing line on.
     * @return a list-array of Lists. The Closest Pair Algorithm expects the point to be returned in a format of list[] returnValue = new List[] {left, right};
     */
    public List[] dividePoints(List<List<Point>> points, int medianIndex, String splitAxis);

}
