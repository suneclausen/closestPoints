package factories;

import interfaces.DividingStrategy;
import interfaces.PresortStrategy;
import interfaces.SlabStrategy;
import three_d_slow.ThreeDSlowDividingStrategy;
import three_d_slow.ThreeDSlowPresortStrategy;
import three_d_slow.ThreeDSlowSlabStrategy;

public class ThreeDSlowFactory implements ClosestPairFactory {
    @Override
    public PresortStrategy getPresortStrategy() {
        return new ThreeDSlowPresortStrategy();
    }

    @Override
    public DividingStrategy getDividePointsStrategy() {
        return new ThreeDSlowDividingStrategy();
    }

    @Override
    public SlabStrategy getSlabStrategy() {
        return new ThreeDSlowSlabStrategy();
    }
}
