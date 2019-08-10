package factories;

import interfaces.DividingStrategy;
import interfaces.PresortStrategy;
import interfaces.SlabStrategy;
import three_d_fast.ThreeDFastDividingStrategy;
import three_d_fast.ThreeDFastPresortStrategy;
import three_d_fast.ThreeDFastSlabStrategy;

public class ThreeDFastFactory implements ClosestPairFactory {
    @Override
    public PresortStrategy getPresortStrategy() {
        return new ThreeDFastPresortStrategy();
    }

    @Override
    public DividingStrategy getDividePointsStrategy() {
        return new ThreeDFastDividingStrategy();
    }

    @Override
    public SlabStrategy getSlabStrategy() {
        return new ThreeDFastSlabStrategy();
    }
}
