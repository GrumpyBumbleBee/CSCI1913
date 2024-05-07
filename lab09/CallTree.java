/** Created by Alexandra Postolaki (posto022) 5510867
 */
public class CallTree<Base> {
    private CallTreeNode<Base> root;

    public CallTree(String name){
        root = new CallTreeNode(name, null, null);
    }

    /** find() method takes in a String representing a name and a CallTree node to be searched in
     * the CallTree. If the given name is not part of the CallTree or is not under the given node,
     * then this function returns null. Otherwise, if the given person is part of the
     * CallTree, then this function returns the call tree node for that person.
     * @param name
     * @param node
     * @return CallTreeNode node, null
     */
    private CallTreeNode find(String name, CallTreeNode node){
        if(node.getName().equals(name)){                        // If the given node contains the given name to find, then returns the corresponding node
            return node;
        }
        if(node.getFirst() != null){                            // Otherwise, goes through the tree starting with the given node's first-person side of the tree if first person isn't null
            CallTreeNode nodeFound = find(name, node.getFirst());
            if(nodeFound != null){
                return nodeFound;
            }
        }
        if(node.getSecond() != null){                           // Goes through the given node's second-person side of the tree
            return find(name, node.getSecond());
        }
        return null;                                            
    }

    /** This find() method takes in a String, name, for a person being searched for in a CallTree.
     * If the name isn't in this call tree, this function returns 'null'. Otherwise, if
     * the name is found in the call tree, this function returns a node of that given person.
     * @param name
     * @return CallTreeNode node
     */
    private CallTreeNode find(String name){
        return find(name, root);
    }
    /** inCallTree() takes in a String representing a person and returns 'true' to indicate
     * if the listed person is in the call tree and 'false' otherwise.
     * @param person
     * @return boolean
     */
    public boolean inCallTree(String person){
        return find(person) != null;
    }

    /** shouldCall() takes in three strings. One string represents a person that could be in the
     * call tree and the other two strings, first and second, represent two listed people for the first person
     * to call. This method returns 'true' if the call tree is successfully changed to incorporate the
     * two listed people to be called by the given person and 'false' if this couldn't happen.
     * @param person
     * @param first
     * @param second
     * @return boolean
     */
    public boolean shouldCall(String person, String first, String second){
        if(inCallTree(person) == false){                            // If the person is not already in call tree
            return false;
        }
        CallTreeNode personNode = find(person);
        if(personNode != null && personNode.isCaller() == true){    // If the person listed is currently a caller
            return false;
        }
        else if(find(first) != null || find(second) != null){       // If the person listed for first/second are currently in call tree.
            return false;
        }
        else{                                                       // Otherwise, successfully adds two new leafs as children nodes.
            personNode.setFirst(new CallTreeNode(first));
            personNode.setSecond(new CallTreeNode(second));
            return true;
        }
    }
    /** getFirstCall() method looks up who the provided person will call and returns the name of the
     * person they should call first. If the named person is not a caller, or is not in the tree, this
     * function returns null.
     * @param name
     * @return String, null
     */
    public String getFirstCall(String name){
        if(find(name) != null && find(name).isCaller() && find(name).getFirst() != null) {                              // If the person is in the tree and is a caller (also if they do have a listed first-person-to-call, then returns name of first person to call
            return find(name).getFirst().getName();
        }
        return null;
    }
    /** getSecondCall() method looks up who the provided person will call and returns the name of the
     * person they should call second. If the named person is not a caller, or is not in the tree, this
     * function returns null.
     * @param name
     * @return String, null
     */
    public String getSecondCall(String name){
        if(find(name) != null && find(name).isCaller() && find(name).getSecond() != null) {                              // If the person is in the tree, is a caller, and has a listed second-person-to-call, then returns the name of the second person to call.
            return find(name).getSecond().getName();
        }
        return null;
    }

    /** getCallCount() method returns the number of rounds of calls before everyone has been contacted.
     * @return numberOfRounds
     */
    public int getCallCount() {
        return getHeight(root);
    }

    /** getHeight() method takes in a CallTreeNode and implements a recursive traversal to compute the height of a given node.
     * If not given a node, this function returns -1. Otherwise, this function returns 1 plus the maximum height
     * of the first and second child-nodes of the provided node.
     * @param node
     * @return
     */
    public int getHeight(CallTreeNode node){
        if(node == null){
            return -1;
        }
        else{
            int leftBranch = getHeight(node.getFirst());
            int rightBranch = getHeight(node.getSecond());
            if(leftBranch > rightBranch){                                                           // Checks if one of the "left-hand" branches contains the greater depth than the rightBranch-hand branch which is then returned
                return leftBranch + 1;
            }
            else{                                                                                   // Otherwise, the "right-hand" branch has a greater depth which would be returned
                return rightBranch + 1;
            }
        }
    }

    /** terminalContactCount() returns the count of "terminal contacts" / non-callers.
     * @return int nonCallerCount
     */
    public int terminalContactCount(){
        return terminalContactCountHelper(root);
    }

    /** The terminalContactCountHelper() function implements recursive traversal to compute the
     * number of leaf nodes accessible from a given node.
     * @param node
     * @return int
     */
    private int terminalContactCountHelper(CallTreeNode node){
        if(node == null){
            return 0;
        }
        else if(node.getFirst() == null && node.getSecond() == null) {                      // Returns 1 if the node doesn't have a first or second person to contact (1 leaf)
            return 1;
        }
        else{
            return terminalContactCountHelper(node.getFirst()) + terminalContactCountHelper(node.getSecond());
        }
    }
}
