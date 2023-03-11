// Name: Sreemoyee Mukherjee
// Course: Data Structures & Algorithms
// Assignment Number: 2
package dsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// class for our 2D tree
public class TwoDTree {
    private TwoDTreeNode root;  // root of the tree

    // dir is false for nodes with Y as the direction and true for nodes with X as the direction
    private boolean dir = false;
    private ListOfCrimes listOfCrimes = new ListOfCrimes(); // to store the list of crimes
    private int size, count;
    private Neighbor neighbor = new Neighbor(); // to store the nearest neighbour
    private double distance;

    /**pre-condition: The String crimeDataLocation contains the path to a file
     * formatted in the exact same way as CrimeLatLonXY.csv
     * post-condition: The 2d tree is constructed and may be printed or queried.
     */
    public TwoDTree(String crimeDataLocation){
        Double X, Y;
        String allLines = "", line;
        try {
            Scanner sc = new Scanner(new File(crimeDataLocation));
            while (sc.hasNext()){
                allLines = allLines + sc.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        line = allLines.split("\n")[1];     // ignore first line as it contains headers
        X = Double.parseDouble(line.split(",")[0]);
        Y = Double.parseDouble(line.split(",")[1]);
        root = new TwoDTreeNode(X, Y, true, line);
        size++; // keeping count of total number of nodes in tree
        int length = allLines.split("\n").length;
        String remLines[] =  allLines.split("\n");
        int index = 2;
        while (length - 2 > 0) {    // (length - 2) as we need to ignore header along with the first row as we already got our first crime data row
            X = Double.parseDouble(remLines[index].split(",")[0]);
            Y = Double.parseDouble(remLines[index].split(",")[1]);
            root.insertNode(root, X, Y, root.getDirection(), remLines[index]);
            size++;
            length--;
            index++;
        }
    }

    public int getSize() {
        return size;
    }

    public TwoDTreeNode getRoot() {
        return root;
    }

    public void setRoot(TwoDTreeNode root) {
        this.root = root;
    }

    /**pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a pre-order traversal.
     */
    public void preOrderPrint(){
        preOrderPrint(this.getRoot());
    }
    public void preOrderPrint(TwoDTreeNode root){
        if (root == null)
            return;

        /* first print data of node */
        System.out.println(root.getData());

        /* then recur on left subtree */
        preOrderPrint(root.getLeft());

        /* now recur on right subtree */
        preOrderPrint(root.getRight());
    }

    /**pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with an in-order traversal.
     *
     * Big Theta: Θ(n) This is as per expectation because in any traversal
     * we have to visit each and every node and thus the complexity is Θ(n)
     */
    public void inOrderPrint(){
        inOrderPrint(this.getRoot());
    }
    public void inOrderPrint(TwoDTreeNode root){
        if (root == null)
            return;

        /* then recur on left subtree */
        inOrderPrint(root.getLeft());

        /* first print data of node */
        System.out.println(root.getData());

        /* now recur on right subtree */
        inOrderPrint(root.getRight());

    }

    /**pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a post-order traversal.
     */
    public void postOrderPrint(){
        postOrderPrint(this.getRoot());
    }
    public void postOrderPrint(TwoDTreeNode root){
        if (root == null)
            return;

        /* then recur on left subtree */
        postOrderPrint(root.getLeft());

        /* now recur on right subtree */
        postOrderPrint(root.getRight());

        /* first print data of node */
        System.out.println(root.getData());

    }

    /**pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a level-order traversal.
     *
     * Big Theta: Θ(n) This is as per expectation because in any traversal
     * we have to visit each and every node and thus the complexity is Θ(n)
     */
    public void levelOrderPrint(){
        TwoDTreeNode root = this.getRoot();
        Queue queue = new Queue();
        queue.enqueue(root);
        queue.dequeue();    // removing the initial dummy node
        while (!queue.isEmpty()) { // loop takes Θ(n)
            TwoDTreeNode tempNode = queue.getFront().getData();
            System.out.println(tempNode.getData() + " ");
            if (tempNode.getLeft() != null) {
                queue.enqueue(tempNode.getLeft());
            }
            if (tempNode.getRight() != null) {
                queue.enqueue(tempNode.getRight());
            }
            queue.dequeue();
        }
    }

    /**pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a reverse level-order traversal.
     *
     * Big Theta: Θ(n) This is as per expectation because in any traversal
     * we have to visit each and every node and thus the complexity is Θ(n)
     */
    public void reverseLevelOrderPrint(){      //Big Theta: Θ(n)
        TwoDTreeNode root = this.getRoot();
        Queue queue = new Queue();
        Stack stack = new Stack();
        queue.enqueue(root);
        queue.dequeue(); // removing the initial dummy node
        while (!queue.isEmpty()) {  // loop takes Θ(n)
            TwoDTreeNode tempNode = queue.getFront().getData();
            stack.push(tempNode);   // pushing queue elements to stack for reverse order printing
            if (tempNode.getLeft() != null) {
                queue.enqueue(tempNode.getLeft());
            }
            if (tempNode.getRight() != null) {
                queue.enqueue(tempNode.getRight());
            }
            queue.dequeue();
        }
        while (!stack.isEmpty()){   // loop takes Θ(n)
            stack.pop();
        }
    }

    // Post condition: we get a copy of our root that we can afford to lose
    public TwoDTreeNode treeCopy(TwoDTreeNode root) {
        if (root == null)
        {
            return null;
        }
        TwoDTreeNode newNode = new TwoDTreeNode(root.getX(), root.getY(), root.getDirection(), root.getData());
        newNode.setLeft(treeCopy(root.getLeft()));
        newNode.setRight(treeCopy(root.getRight()));
        return newNode;
    }

    /**pre-condition: The 2d tree has been constructed
     * post-condition: A list of 0 or more crimes is returned.
     * These crimes occurred within the rectangular range specified by the four parameters.
     * The pair (x1, y1) is the left bottom of the rectangle. The pair (x2, y2) is the top right of the rectangle.
     */
    public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2){
        dir = root.getDirection();
        count = 0;
        TwoDTreeNode temp = treeCopy(root); // working with a copy of root
        findPointsInRangeRec(temp, x1, y1, x2, y2);
        System.out.println("Examined " + count + " nodes during search");
        System.out.println("Found " + listOfCrimes.getCount() + " crimes.");
        listOfCrimes.toKML();
        return listOfCrimes;
    }
    public TwoDTreeNode findPointsInRangeRec(TwoDTreeNode root, double x1, double y1, double x2, double y2){
        if (root == null){
            return null;
        }
        if (dir) // if n is an X-node, we compare X-value and add accordingly
        {
            dir = false;
            if(root.getX() > x2 && root.getX() > x1){
                count++;    // increasing count by 1 as we checked another node in the tree
                root = findPointsInRangeRec(root.getLeft(), x1, y1, x2, y2);
                if (root == null){
                    return null;
                }
            } else if (root.getX() <= x2 && root.getX() >= x1) {
                count++;    // increasing count by 1 as we checked another node in the tree
                root.setLeft(findPointsInRangeRec(root.getLeft(), x1, y1, x2, y2));
                root.setRight(findPointsInRangeRec(root.getRight(), x1, y1, x2, y2));
                if(root.getY() <= y2 && root.getY() >= y1){
                    listOfCrimes.addCrime(root);
                }

                return null;
            } else if (root.getX() < x2 && root.getX() < x1) {
                count++;    // increasing count by 1 as we checked another node in the tree
                root = findPointsInRangeRec(root.getRight(), x1, y1, x2, y2);
                if (root == null){
                    return null;
                }
            }
        }
        if (!dir) // if n is an Y-node, we compare Y-value and add accordingly
        {
            dir = true;
            if(root.getY() > y2 && root.getY() > y1){
                count++;    // increasing count by 1 as we checked another node in the tree
                root = findPointsInRangeRec(root.getLeft(), x1, y1, x2, y2);
                if (root == null){
                    return null;
                }
            } else if (root.getY() <= y2 && root.getY() >= y1) {
                count++;    // increasing count by 1 as we checked another node in the tree
                root.setLeft(findPointsInRangeRec(root.getLeft(), x1, y1, x2, y2));
                root.setRight(findPointsInRangeRec(root.getRight(), x1, y1, x2, y2));
                if(root.getX() <= x2 && root.getX() >= x1){
                    listOfCrimes.addCrime(root);
                }
                return null;
            } else if (root.getY() < y2 && root.getY() < y1) {
                count++;    // increasing count by 1 as we checked another node in the tree
                root = findPointsInRangeRec(root.getRight(), x1, y1, x2, y2);
                if (root == null){
                    return null;
                }
            }
        }
        return root;
    }

    /** pre-condition: the 2d tree has been constructed. The (x1,y1) pair represents
     *  a point in space near Pittsburgh and in the state plane coordinate system.
     *  post-condition: the distance in feet to the nearest node is returned in Neighbor.
     *  In addition, the Neighbor object contains a reference to the nearest neighbor in the tree.
     */
    public Neighbor nearestNeighbor(double x1, double y1){
        count = 0;
        TwoDTreeNode temp = treeCopy(root); // working with a copy of root
        neighbor.setDistance(x1, y1, this.getRoot().getX(), this.getRoot().getY());
        distance = neighbor.getDistance();
        nearestNeighborRec(temp, x1, y1);
        neighbor.setDistance(x1, y1, neighbor.getPointer().getX(), neighbor.getPointer().getY());
        System.out.print("Looked at " + count + " nodes in tree. ");
        return neighbor;
    }
    public TwoDTreeNode nearestNeighborRec(TwoDTreeNode root, double x1, double y1) {
        if (root == null) {
            return null;
        }
        neighbor.setDistance(x1, y1, root.getX(), root.getY()); // checking current node distance from query point
        count++;    // increasing count by 1 as we checked another node in the tree
        if (neighbor.getDistance() <= distance) {
            distance = neighbor.getDistance();  // update with new-found nearest neighbour distance
            neighbor.setPointer(root);
            root.setLeft(nearestNeighborRec(root.getLeft(), x1, y1));
            root.setRight(nearestNeighborRec(root.getRight(), x1, y1));
            return null;
        } else if (neighbor.getDistance() > distance) {
            return null;
        }
        return root;
    }
}
