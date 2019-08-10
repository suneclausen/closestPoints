package three_d_fast.two_d_helper_for_fast_3d;

import factories.ClosestPairFactory;
import interfaces.DividingStrategy;
import interfaces.PresortStrategy;
import interfaces.SlabStrategy;
import two_d.TwoDPresortStrategy;

public class TwoDHelperFactory implements ClosestPairFactory {
    @Override
    public PresortStrategy getPresortStrategy() {
        return new TwoDPresortStrategy();
    }

    @Override
    public DividingStrategy getDividePointsStrategy() {
        return new TwoDHelperDivdingStrategy();
    }

    @Override
    public SlabStrategy getSlabStrategy() {
        return new TwoDHelperSlabStrategy();
    }
}
