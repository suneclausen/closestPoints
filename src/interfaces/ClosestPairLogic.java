package interfaces;
import mainComponents.ClosestPair;
import mainComponents.DividingValuesHelper;
import mainComponents.Point;


import java.util.List;

public interface ClosestPairLogic {
    public List<List<Point>> presort(List<Point> points);

    public DividingValuesHelper dividePoints(List<List<Point>> points, int medianIndex, String splitAxis);

    public ClosestPair closestPair(List<List<Point>> points, String recursion);
}


