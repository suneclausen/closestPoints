package interfaces;

import mainComponents.ClosestPair;
import mainComponents.ClosestPairLogicImpl;
import mainComponents.Point;

import java.util.List;

public interface SlabStrategy {
    // TODO: Might want to make it retrun List<List<Point>> for when working with more dimensions????
    public List<List<Point>> buildSlab(List<List<Point>> points, double delta, int medianIndex);

    public ClosestPair traverseSlab(ClosestPair currentClosestPair, List<List<Point>> slab, ClosestPairLogicImpl closestPairLogic);
}
