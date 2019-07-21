package Factories;

import interfaces.DividingStrategy;
import interfaces.PresortStrategy;
import interfaces.SlabStrategy;

public interface ClosestPairFactory {
    PresortStrategy getPresortStrategy();

    DividingStrategy getDividePointsStrategy();

    SlabStrategy getSlabStrategy();

}
