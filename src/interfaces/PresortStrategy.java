package interfaces;

import mainComponents.Point;

import java.util.List;

public interface PresortStrategy {
    /**
     * Presort method that makes the ClosestPairLogicImpl work properly. It will sort the points in each of the coordinate-axis.
     * @param points: A list of points in a specific dimension.
     * @return a list of list where each list will correspond to a list of points sorted in reletaion to one coordinate-axis. The x-axis MUST be the first list in the return list.
     */
    public List<List<Point>> presort(List<Point> points);

}
