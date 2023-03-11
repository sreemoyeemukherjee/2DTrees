// Name: Sreemoyee Mukherjee
// Course: Data Structures & Algorithms
// Assignment Number: 2
package dsa;

// class for each LinkedList Node
public class LinkedListNode {
    private TwoDTreeNode data; // stores data of node
    private LinkedListNode link;    // stores pointer to the next node

    // constructor
    public LinkedListNode(TwoDTreeNode data, LinkedListNode link) {
        this.data = data;
        this.link = link;
    }

    // generic getters
    public TwoDTreeNode getData() {
        return data;
    }
    public LinkedListNode getLink() {
        return link;
    }

    // generic setters
    public void setData(TwoDTreeNode data) {
        this.data = data;
    }
    public void setLink(LinkedListNode link) {
        this.link = link;
    }

    // method to add a node after the current last node
    public void addNodeAfter(TwoDTreeNode data)
    {
        link = new LinkedListNode(data, link);
    }
}
