package factories;

import interfaces.DividingStrategy;
import interfaces.PresortStrategy;
import interfaces.SlabStrategy;
import two_d.TwoDDividingStrategy;
import two_d.TwoDPresortStrategy;
import two_d.TwoDSlabStrategy;

public class TwoDFactory implements ClosestPairFactory {
    @Override
    public PresortStrategy getPresortStrategy() {
        return new TwoDPresortStrategy();
    }

    @Override
    public DividingStrategy getDividePointsStrategy() {
        return new TwoDDividingStrategy();
    }

    @Override
    public SlabStrategy getSlabStrategy() {
        return new TwoDSlabStrategy();
    }
}
