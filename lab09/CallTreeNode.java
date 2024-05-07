/** Created by Alexandra Postolaki (posto022) 5510867
 */
public class CallTreeNode<Base>{
    private String name;
    private CallTreeNode<Base> first;
    private CallTreeNode<Base> second;

    public CallTreeNode(String name){
        this(name, null, null);
    }
    public CallTreeNode(String name, CallTreeNode first, CallTreeNode second){
        this.name = name;
        this.first = first;
        this.second = second;
    }
    public String getName(){
        return name;
    }
    public CallTreeNode<Base> getFirst(){
        return first;
    }
    public CallTreeNode<Base> getSecond(){
        return second;
    }
    public void setFirst(CallTreeNode<Base> first){
        this.first = first;
    }
    public void setSecond(CallTreeNode<Base> second){
        this.second = second;
    }

    /** isCaller() returns boolean 'true' if the person represented by the node
     * is a "caller" and 'false' otherwise.
     * @return boolean
     */
    public boolean isCaller(){
        if(this.getFirst() != null || this.getSecond() != null){        // Should be a caller if they have someone to contact
            return true;
        }
        return false;
    }
}
