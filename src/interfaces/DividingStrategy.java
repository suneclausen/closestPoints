package interfaces;

import mainComponents.Point;

import java.util.List;

public interface DividingStrategy {
    public List[] dividePoints(List<List<Point>> points, int medianIndex);

}
