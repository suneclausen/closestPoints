package interfaces;

import mainComponents.Point;

import java.util.List;

public interface PresortStrategy {
    public List<List<Point>> presort(List<Point> points);

}
