package mainComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Point {
//    private int index;
    private List<Integer> index;
    private double[] coordinates;
    private int dimension;

    public Point(int dimension, double[] coordinates){
        this.dimension = dimension;

        if (dimension != coordinates.length){
            throw new RuntimeException("There was a mismatch between the dimension and the coordiantes given. " +
                    "The dimension given was: " + dimension +  ". The dimension of the coordinates was: " + coordinates.length);
        }

        this.coordinates = coordinates;
        this.index = new ArrayList<>();
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

    public List<Integer> getIndex() {
        return index;
    }

    public void setIndex(List<Integer> index) {
        this.index = index;
    }

    public void addIndex(int number) {
        this.index.add(number);
    }

    public void removeIndexElement(int index) {
        this.index.remove(index);
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", index=" + index +
                '}';
    }
}
