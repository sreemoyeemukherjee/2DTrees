// Name: Sreemoyee Mukherjee
// Course: Data Structures & Algorithms
// Assignment Number: 2
package dsa;

// our class for Queue data structure
public class Queue {

    // pointer to the last node
    private LinkedListNode rear = new LinkedListNode(null, null);

    // a dummy node pointing to the first node
    private LinkedListNode front = rear;

    // generic getters
    public LinkedListNode getFront() {
        return front;
    }

    public LinkedListNode getRear() {
        return rear;
    }

    // generic setters
    public void setFront(LinkedListNode front) {
        this.front = front;
    }

    public void setRear(LinkedListNode rear) {
        this.rear = rear;
    }

    // function to insert data in the queue
    public void enqueue(TwoDTreeNode data){
        rear.addNodeAfter(data);
        rear = rear.getLink();
    }

    // function to remove data from the queue
    public void dequeue(){
        if(front == null){  // empty queue
            rear = null;
            return;
        }
        front = front.getLink();
    }

    // checks if queue is empty
    public boolean isEmpty(){
        if(front == null){
            return true;
        }
        return false;
    }
}
