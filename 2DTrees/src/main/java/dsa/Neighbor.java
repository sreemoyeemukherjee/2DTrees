// Name: Sreemoyee Mukherjee
// Course: Data Structures & Algorithms
// Assignment Number: 2
package dsa;

// class to store the nearest neighbour
public class Neighbor {
    private double distance;    // stores distance from the query point
    private TwoDTreeNode pointer;   // points to the tree node which is the nearest neighbour to the query point

    // generic getters
    public TwoDTreeNode getPointer() {
        return pointer;
    }
    public double getDistance() {
        return distance;
    }

    // generic setters
    public void setPointer(TwoDTreeNode pointer) {
        this.pointer = pointer;
    }

    // calculates Pythagorian distance between two points
    public void setDistance(double x1, double y1, double x2, double y2) {
        distance = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }
}
