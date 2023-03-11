// Name: Sreemoyee Mukherjee
// Course: Data Structures & Algorithms
// Assignment Number: 2
package dsa;

public class TwoDTreeNode
{
    private TwoDTreeNode left;
    private TwoDTreeNode right;
    private Double X;
    private Double Y;
    private String data;    // stores entire crime data

    // direction is false for nodes with Y as the direction and true for nodes with X as the direction
    private boolean direction;

    public TwoDTreeNode(Double X, Double Y, boolean direction, String data)
    {
        this.X = X;
        this.Y = Y;
        this.setDirection(direction);
        this.data = data;
        this.setLeft(null);
        this.setRight(null);

    }


    /*
     * Generic getters and setters to access private variables
     */

    public TwoDTreeNode getLeft()
    {
        return left;
    }

    public void setLeft(TwoDTreeNode left)
    {
        this.left = left;
    }

    public TwoDTreeNode getRight()
    {
        return right;
    }

    public void setRight(TwoDTreeNode right)
    {
        this.right = right;
    }

    public Double getX() {
        return X;
    }

    public void setX(Double x) {
        X = x;
    }

    public Double getY() {
        return Y;
    }
    public void setY(Double y) {
        Y = y;
    }

    public boolean getDirection()
    {
        return direction;
    }

    public void setDirection(boolean direction)
    {
        this.direction = direction;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void insertNode(TwoDTreeNode root, Double X, Double Y, boolean direction, String data){
        root = insertNodeRec(root, X, Y, direction, data);
    }
    public TwoDTreeNode insertNodeRec(TwoDTreeNode root, Double X, Double Y, boolean direction, String data){
        if (root == null) {
            root = new TwoDTreeNode(X, Y, direction, data); // create new node
            return root;
        }
        if (direction) // if n is an X-node, we compare X-value and add accordingly
        {
            direction = false;
            if (X < root.X) {
                root.setLeft(insertNodeRec(root.left, X, Y, direction, data));
                return root;
            }

            if (X >= root.X) {
                root.setRight(insertNodeRec(root.right, X, Y, direction, data));
                return root;
            }
        }
        if (!direction) // if n is an Y-node, we compare Y-value and add accordingly
        {
            direction = true;
            if (Y < root.Y) {
                root.setLeft(insertNodeRec(root.left, X, Y, direction, data));
                return root;
            }

            if (Y >= root.Y) {
                root.setRight(insertNodeRec(root.right, X, Y, direction, data));
                return root;
            }
        }
        return root;
    }
}