/** Created by Alexandra Postolaki (posto022) 5510867
 */

public class CacheBlockQueue<T> {
    private CacheBlockQueueNode<T> front;
    private CacheBlockQueueNode<T> rear;
    int size, totalCount;

    public CacheBlockQueue(){
        front = null;
        rear = null;
        size = 0;
        totalCount = 0;         // Used to track the total count in the queue (sum of each count in each node)
    }

    /** enqueue() method adds an element to the end of a queue. If the queue is empty, then
     * the element is simply added to the queue. If the element is equal to the current end of the
     * queue, then the count on the related CacheBlockQueueNode is updated and no new nodes are made.
     * However, if the current end element is not equivalent, then a new node is made to be the given element.
     * @param element
     */
    public void enqueue(T element){
        if(front == null){                              // Check if the queue is an empty queue (if it is, adds to the queue).
            CacheBlockQueueNode<T> newFront = new CacheBlockQueueNode<>(element, null);
            front = newFront;
            rear = newFront;
            size++;
            totalCount += 1;
        }
        else if(rear.getData().equals(element)){                                            // Check if the current end node's data is equivalent to the given element (if so, updates count)
            int count = rear.getCount();
            rear.setCount(count + 1);
            totalCount += 1;
        }
        else {                                                                               // (otherwise, a new node made and it's data is the new element).
            CacheBlockQueueNode<T> newNode = new CacheBlockQueueNode<>(element, null);
            rear.setNext(newNode);
            rear = newNode;
            size++;
            totalCount += 1;
        }
    }

    /** front() returns the current front of the queue without removing it. If the queue is empty, this returns 'null'.
     * @return currentFront, null
     */
    public T front(){
        if(front != null){          // The queue is NOT empty if the front node and rear node aren't 'null'
            return front.getData();
        }
        else{
            return null;
        }
    }
    /** dequeue() removes the current front of the queue and returns its value. If the queue is empty,
     * this returns 'null' and no changes are made.
     * @return returnValue, null
     */
    public T dequeue(){
        T returnValue;
        if(front == null) {
            return null;
        }
        else if(front.getCount() > 1){                  // case for if the count of the node is >1 (don't remove node, just reduce count)
            front.setCount(front.getCount() - 1);
            totalCount -= 1;
            returnValue = front.getData();
            return returnValue;
        }
        else if(front.getCount() <= 1){                 // case for if the count of the node is 1 (then remove the node if the count == 0 after).
            if(front == rear && front != null && rear != null){       // When front and rear point to the same node, that means that the list will be empty
                size--;
                totalCount -= 1;
                returnValue = front.getData();
                front = null;
                rear = null;
                return returnValue;
            }
            returnValue = front.getData();
            front.setCount(front.getCount() - 1);
            totalCount -= 1;
            front = front.getNext();
            size--;
            return returnValue;
        }
        return null;
    }

    /** frontOfLineRepeatCount() returns the size of the cache block at the front of the queue.
     * If the queue is empty, then this returns 0
     * @return frontCount, int
     */
    public int frontOfLineRepeatCount(){
        if(front == null){                      // If queue empty
            return 0;
        }
        else{
            return front.getCount();
        }
    }

    public int getSize(){
        return totalCount;
    }

    /** isEmpty() returns boolean 'true' if the queue is empty and boolean 'false' otherwise.
     * @return boolean
     */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }
    /** toString() This method formulates a string to display the cache block queue.
     * @return String
     */
    @Override
    public String toString() {
        CacheBlockQueueNode<T> currentNode = front;
        String returnString = "";
        while (currentNode != null) {                                                                       // If currentNode and the node after it are both null, then the string formulates an ending
            if (currentNode.getNext() == null) {
                returnString = returnString + currentNode.getData() + ":" + currentNode.getCount();
                return returnString;
            } else {
                returnString = returnString + currentNode.getData() + ":" + currentNode.getCount() + ", ";       // Otherwise, there are more nodes that aren't empty and requires a ',' to be added.
                currentNode = currentNode.getNext();
            }
        }
        return returnString;
    }
}
