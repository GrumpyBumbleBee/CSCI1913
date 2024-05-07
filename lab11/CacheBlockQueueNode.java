/** Created by Alexandra Postolaki (posto022) 5510867
 */
public class CacheBlockQueueNode<T> {
    private T data;
    private int count;
    private CacheBlockQueueNode<T> next;

    public CacheBlockQueueNode(T data, CacheBlockQueueNode<T> next){
        // Constructor that takes initial values for data and next, setting count to initially be 1.
        this.data = data;
        this.next = next;
        this.count = 1;
    }
    public T getData(){
        return data;
    }
    public int getCount(){
        return count;
    }
    public CacheBlockQueueNode<T> getNext(){
        return next;
    }
    public void setData(T newData){
        this.data = newData;
    }
    public void setCount(int newCount){
        count = newCount;
    }
    public void setNext(CacheBlockQueueNode<T> newNextCacheBlockQueueNode){
        next = newNextCacheBlockQueueNode;
    }
}
