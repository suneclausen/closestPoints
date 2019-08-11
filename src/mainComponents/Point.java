package mainComponents;

import java.util.*;

public class Point {
//    private List<Integer> index;
    private HashMap<String, Integer> index;
    private double[] coordinates;
    private int dimension;

    public Point(int dimension, double[] coordinates){
        this.dimension = dimension;

        if (dimension != coordinates.length){
            throw new RuntimeException("There was a mismatch between the dimension and the coordinates given. " +
                    "The dimension given was: " + dimension +  ". The dimension of the coordinates was: " + coordinates.length);
        }

        this.coordinates = coordinates;
        this.index = new HashMap<>();

//        this.index = new ArrayList<>();
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public HashMap<String, Integer> getIndex() {
        return index;
    }

    public void setIndex(String coordinate, Integer index){
        this.index.put(coordinate, index);
    }

    public void replaceIndex(HashMap<String, Integer> replacer){
        this.index = replacer;
    }


//    public void setIndex(List<Integer> index) {
//        this.index = index;
//    }

//    public void addIndex(int number) {
//        this.index.add(number);
//    }

//    public void removeIndexElement(int index) {
//        this.index.remove(index);
//    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", index=" + index +
                '}';
    }

//    @Override
//    public String toString() {
//        return "new Point(3, new double[]" + Arrays.toString(coordinates) +")";
//    }

    @Override
    public Point clone() throws CloneNotSupportedException {
        Point clonedPoint = new Point(getDimension(), getCoordinates());
        clonedPoint.replaceIndex(getIndex()); //TODO: FIX!
        return clonedPoint;

    }
}
