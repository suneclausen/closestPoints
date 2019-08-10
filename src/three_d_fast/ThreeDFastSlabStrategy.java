package three_d_fast;

import interfaces.ClosestPairLogic;
import interfaces.SlabStrategy;
import mainComponents.ClosestPair;
import mainComponents.ClosestPairLogicImpl;
import mainComponents.Point;
import three_d_fast.two_d_helper_for_fast_3d.TwoDHelperFactory;

import java.util.ArrayList;
import java.util.List;

public class ThreeDFastSlabStrategy implements SlabStrategy {
    @Override
    public List<List<Point>> buildSlab(List<List<Point>> points, double delta, int medianIndex, String splitAxis, List<Point> cutList) {
        // Constants
        int X = 0;
        int Y = 1;
        int Z = 2;
        List<Point> pointsSortedByX = points.get(X);
        List<Point> pointsSortedByY = points.get(Y);
        List<Point> pointsSortedByZ = points.get(Z);
        // Slabs
        List<Point> slabOne = new ArrayList<>();
        List<Point> slabTwo = new ArrayList<>();

        if (splitAxis.equals("x")){
            double medianValue = (cutList.get(0).getCoordinates()[X] + cutList.get(cutList.size()-1).getCoordinates()[X]) / 2;

            for (Point point: pointsSortedByY) {
                double pointValue = point.getCoordinates()[X];
                if (pointValue <= medianValue + delta && pointValue >= medianValue - delta){
                    slabOne.add(point);
                }
            }

            for (Point point : pointsSortedByZ) {
                double pointValue = point.getCoordinates()[X];
                if (pointValue <= medianValue + delta && pointValue >= medianValue - delta){
                    slabTwo.add(point);
                }
            }
        }


        if (splitAxis.equals("y")){
            double medianValue = (cutList.get(0).getCoordinates()[Y] + cutList.get(cutList.size()-1).getCoordinates()[Y]) / 2;

            for (Point point: pointsSortedByX) {
                double pointValue = point.getCoordinates()[Y];
                if (pointValue <= medianValue + delta && pointValue >= medianValue - delta){
                    slabOne.add(point);
                }
            }

            for (Point point : pointsSortedByZ) {
                double pointValue = point.getCoordinates()[Y];
                if (pointValue <= medianValue + delta && pointValue >= medianValue - delta){
                    slabTwo.add(point);
                }
            }
        }


        if (splitAxis.equals("z")){
            double medianValue = (cutList.get(0).getCoordinates()[Z] + cutList.get(cutList.size()-1).getCoordinates()[Z]) / 2;

            for (Point point: pointsSortedByX) {
                double pointValue = point.getCoordinates()[Z];
                if (pointValue <= medianValue + delta && pointValue >= medianValue - delta){
                    slabOne.add(point);
                }
            }

            for (Point point : pointsSortedByY) {
                double pointValue = point.getCoordinates()[Z];
                if (pointValue <= medianValue + delta && pointValue >= medianValue - delta){
                    slabTwo.add(point);
                }
            }
        }

        List<List<Point>> returnList = new ArrayList<>();
        returnList.add(slabOne);
        returnList.add(slabTwo);
        return returnList;
    }

    @Override
    public ClosestPair traverseSlab(ClosestPair currentClosestPair, List<List<Point>> slab, ClosestPairLogicImpl closestPairLogic, String splitAxis) {
//        List<List<Point>> slabCopy = slab;
//        List<List<Point>> slabCopy = new ArrayList<>();
//        slabCopy.addAll(slab);
//        for (Point point : slabCopy.get(0)) {
//            if (point.getIndex().size() > 1) { //We project it down into 2d and solve it there, but we only expect it to have 1 index here.
//                if (splitAxis.equals("x")){
//                    point.removeIndexElement(0);
//                }else if(splitAxis.equals("y")){
//                    point.removeIndexElement(1);
//                }else if (splitAxis.equals("z")){
//                    point.removeIndexElement(2);
////                    point.removeIndexElement(1);
//                }else {
//                    throw new RuntimeException("Something went wrong with what axis to split on. The axis does properly not exist. Given was: " + splitAxis);
//                }
//            }
//        }

        if (slab.get(0).size() == 0){
            return currentClosestPair;
        }

        //TODO: Fix logic and make 2d strategies to support the logic for the 3d implementation.
        ClosestPairLogic cpTwoDLogic = new ClosestPairLogicImpl(closestPairLogic.getDimension(), new TwoDHelperFactory());
        ((ClosestPairLogicImpl) cpTwoDLogic).setSplitAxis(splitAxis);
        ClosestPair cpFrom2d = cpTwoDLogic.closestPair(slab, "3D-Fast-TOP");

        if (cpFrom2d.getDistanceBetweenPoints() < currentClosestPair.getDistanceBetweenPoints()) {
            currentClosestPair = cpFrom2d;
        }

        return currentClosestPair;
    }
}
