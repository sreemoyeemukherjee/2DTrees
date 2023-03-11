// Name: Sreemoyee Mukherjee
// Course: Data Structures & Algorithms
// Assignment Number: 2
package dsa;

// our class for Stack data structure
public class Stack {

    // a dummy node pointing to the first node
    private LinkedListNode front = new LinkedListNode(null, null);

    // to insert data in the stack
    public void push(TwoDTreeNode data){
        front.addNodeAfter(data);
        //head = head.getLink();
    }

    // to remove data from the stack
    public void pop(){
        if(front == null){  // empty stack
            return;
        }
        if(front.getData() == null){     // to move head from the dummy node to the first node
            front = front.getLink();
        }
        System.out.println(front.getData().getData());
        front = front.getLink();
    }

    // checks if stack is empty
    public boolean isEmpty(){
        if(front == null){
            return true;
        }
        return false;
    }
}
