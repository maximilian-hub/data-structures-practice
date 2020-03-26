import java.util.ArrayList;

/**
*   This class implements a linked binary search tree.
*
*   For educational purposes.
*
*   @author         Maximilian Spedale
*   Created:        March 6, 2020
*   Last updated:   March 16, 2020
*/

public class MyBST<T extends Comparable<T>>{
    private Node root;
    private int size;

    public MyBST(){
        this.root = null;
        this.size = 0;
    } // end constructor

    public Node getRoot(){
        return this.root;
    } // end method getRoot

    public int getSize(){
        return this.size;
    } // end method getSize

    /**
    *   Tests if this tree is empty.
    *   @return 'true' if this tree is empty
    */
    public boolean isEmpty(){
        return (root == null);
    } // end method isEmpty

    /**
    *   Tests if this tree contains some value.
    *   @param value the value to be searched for
    *   @return 'true' if the value exists in this tree
    */
    public boolean contains(T value){
        return recursiveSearch(this.root, value);
    } // end method contains

    /**
    *   Recursively searches for some value.
    *
    *   @param currentNode the root of the subtree to be searched
    *   @param value the value to be searched for
    *   @return 'true' if this subtree contains t
    */
    private boolean recursiveSearch(Node currentNode, T value){
        if (currentNode.data.equals(value))         // base case: it's here!
            return true;
        else if (t.compareTo(currentNode.data) < 0) // if the current node's value is too high,
            return searchLeft(currentNode, value);  // search left.
        else                                        // if the current node's value is too low,
            return searchRight(currentNode, value); // search right.
    } // end method recursiveSearch

    /**
    *   Searches a node's left subtree for some value.
    *
    *   @param currentNode the node whose right subtree is to be searched
    *   @param value the value to be searched for
    *   @return 'true' is the value is found
    */
    private boolean searchLeft(Node currentNode, T value){
        if (currentNode.hasLeftChild())
            return recursiveSearch(currentNode.leftChild, value);
        else
            return false;
    } // end method searchLeft

    /**
    *   Searches a node's right subtree for some value.
    *
    *   @param currentNode the node whose right subtree is to be searched
    *   @param value the value to be searched for
    *   @return 'true' is the value is found
    */
    private boolean searchRight(Node currentNode, T value){
        if (currentNode.hasRightChild()){
            return recursiveSearch(currentNode.rightChild, value);
        } else {
            return false;
        }
    } // end method searchRight

    /**
    *   Finds the lowest-ordered value
    *   in this tree.
    *   @return the node containing the lowest value
    */
    public Node getMinimum(){
        return recursiveMin(this.root);
    } // end method getMinimum

    /**
    *   Finds the highest-ordered value
    *   in this tree.
    *   @return the node containing the highest value
    */
    public Node getMaximum(){
        return recursiveMax(this.root);
    } // end method getMaximum

    /**
    *   Recursively locates the lowest-ordered
    *   element in this subtree, aka the leftmost
    *   leaf.
    *   @param currentNode starting position
    *   @return the node containing the lowest value
    */
    private Node recursiveMin(Node currentNode){
        if (currentNode == null)
            return null;
        else if (currentNode.hasLeftChild())
            return recursiveMin(currentNode.leftChild);
        else
            return currentNode;
    } // end method recursiveMin

    /**
    *   Recursively locates the highest-ordered
    *   element in this tree, aka the rightmost
    *   leaf.
    *   @return the node containing the highest value
    */
    private Node recursiveMax(Node currentNode){
        if (currentNode == null)
            return null;
        else if (currentNode.hasRightChild())
            return recursiveMax(currentNode.rightChild);
        else
            return currentNode;
    } // end method recursiveMax

    /**
    *   Recursively calculates the height
    *   of this tree.
    *   Leaves have a height of 0.
    *   Nonexistent nodes have a height of -1.
    */
    private int recursiveHeight(Node n){
        if (n == null)
            return -1;
        if (n.isLeaf())
            return 0;
        else
            return Math.max(recursiveHeight(n.leftChild), recursiveHeight(n.rightChild));
    } // end method recursiveHeight

    /**
    *   @return the total number of levels
    *           in this tree
    */
    private int levels(){
        return recursiveHeight(this.root) + 1;
    } // end method levels

    /**
    *   Inserts new data in this tree,
    *   unless it's already in there.
    *   Maintains ordering.
    */
    public void add(T data){
        if (this.isEmpty()){
            this.root = new Node(data);
            this.size++;
        }
        else
            recursiveAdd(this.root, data);
    } // end method add

    /**
    *   A recursive method that inserts
    *   a new value into a subtree.
    *   @param currentNode the root of the subtree into which you're inserting
    *   @param data the value you're inserting
    */
    private void recursiveAdd(Node currentNode, T data){
        if (data.compareTo(currentNode.getData()) < 0) {        // if the incoming data is lower than the current node's,
            sendLeft(currentNode, data);                        // send it down the left subtree.
        }
        else if (data.compareTo(currentNode.getData()) > 0) {   // if the incoming data is higher than the current node's,
            sendRight(currentNode, data);                       // send the data down the right subtree.
        }
        else {                                                  // if the incoming data is already in this node,
            return;                                             // do nothing.
        }                                                       // TODO: you could throw an exception here
    } // end method recursiveAdd()

    /**
    *   Sends a piece of data down a node's
    *   left subtree, to be inserted somewhere.
    */
    private void sendLeft(Node currentNode, T data){
        if (currentNode.hasLeftChild())
            recursiveAdd(currentNode.getLeftChild(), data);
        else{
            currentNode.setLeftChild(new Node(data));
            this.size++;
        }
    } // end method sendLeft

    /**
    *   Sends a piece of data down a node's
    *   right subtree, to be inserted somewhere.
    */
    private void sendRight(Node currentNode, T data){
        if (currentNode.hasRightChild())
            recursiveAdd(currentNode.getRightChild(), data);
        else{
            currentNode.setRightChild(new Node(data));
            size++;
        }
    } // end method sendRight

    /**
    *   Removes a value from this tree.
    *   @param data the value to be removed
    */
    public void remove(T data){
        if (this.contains(data))
            recursiveRemove(this.root, data);
    } // end method remove

    /**
    *   Removes a node containing a certain value.
    *   Assumes the value is actually in this subtree.
    *   @param currentNode the subtree being searched
    *   @param data the value to be removed
    */
    private void recursiveRemove(Node currentNode, T data){
        if (currentNode.data.equals(data))                  // base case: it's here!
            delete(currentNode);
        else if (data.compareTo(currentNode.data) < 0)      // if the current node's value is too high,
            recursiveRemove(currentNode.leftChild, data);   // remove from the left subtree.
        else                                                // if the current node's value is too low,
            recursiveRemove(currentNode.rightChild, data);  // remove from the right subtree.
    } // end method recursiveRemove

    /**
    *   Removes this node's value from the tree.
    *   Responsible for decrementing the tree's size.
    *   Handles the children appropriately.
    */
    private void delete(Node currentNode){
        if (currentNode.isRoot())
            deleteRoot();
        else if (currentNode.isLeaf())
            deleteLeaf(currentNode);
        else if (currentNode.hasOneChild())
            deleteNodeWithOneChild(currentNode);
        else
            deleteNodeWithTwoChildren(currentNode);
    } // end method delete

    /**
    *   Deletes the root of this tree.
    *   Assumes this tree actually has a root.
    */
    private void deleteRoot(){
        if (root.isLeaf()){
            root = null;
        } else if (root.hasOneChild()){
            root = root.onlyChild();
        } else { // if root has two children:
            deleteNodeWithTwoChildren(root);
        }
        this.size--;
    } // end method deleteRoot

    /**
    *   Removes a childless node from
    *   this tree.
    *   @param n a childless node
    */
    private void deleteLeaf(Node n){
        n.parent.replaceChild(n, null);
        this.size--;
    } // end method deleteLeaf

    /**
    *   Removes a single-child node
    *   from this tree.
    *   @param n a single child node
    */
    private void deleteNodeWithOneChild(Node n){
        n.onlyChild().setParent(n.parent);          // Your child's parent becomes your parent,
        n.parent.replaceChild(n, n.onlyChild());    // and your child replaces you as your parent's child.
        this.size--;
    } // end method deleteNodeWithOneChild

    /**
    *   Removes a full node from this tree.
    *   @param n a node with two children
    */
    private void deleteNodeWithTwoChildren(Node n){
        Node temp = recursiveMax(n.leftChild);
        n.data = temp.data;
        delete(temp);
    } // end method deleteNodeWithTwoChildren

    /**
    *   Returns an iterable list containing every
    *   node in this Tree, in order.
    */
    public ArrayList<Node> inOrder(){
        return inOrderTraversal(this.root);
    } // end method inOrderTraversal

    /**
    *   Recursively generates an iterable list
    *   of every node in this tree, in order.
    */
    public ArrayList<Node> inOrderTraversal(Node currentNode){
        ArrayList<Node> list = new ArrayList();

        if (currentNode == null)
            return list;

        list.addAll(inOrderTraversal(currentNode.leftChild));
        list.add(currentNode);
        list.addAll(inOrderTraversal(currentNode.rightChild));

        return list;
    } // end method inOrderTraversal

    /**
    *   Returns an iterable list containing every
    *   node in this Tree, in pre-order.
    */
    public ArrayList<Node> preOrder(){
        return preOrderTraversal(this.root);
    } // end method preOrderTraversal

    /**
    *   Recursively generates an iterable list
    *   of every node in this tree, in pre-order.
    */
    public ArrayList<Node> preOrderTraversal(Node currentNode){
        ArrayList<Node> list = new ArrayList();

        if (currentNode == null)
            return list;

        list.add(currentNode);
        list.addAll(preOrderTraversal(currentNode.leftChild));
        list.addAll(preOrderTraversal(currentNode.rightChild));

        return list;
    } // end method preOrderTraversalTraversal

    /**
    *   Returns an iterable list containing every
    *   node in this Tree, in post-order.
    */
    public ArrayList<Node> postOrder(){
        return postOrderTraversal(this.root);
    } // end method postOrder

    /**
    *   Recursively generates an iterable list
    *   of every node in this tree, in post-order.
    */
    public ArrayList<Node> postOrderTraversal(Node currentNode){
        ArrayList<Node> list = new ArrayList();

        if (currentNode == null)
            return list;

        list.addAll(preOrderTraversal(currentNode.leftChild));
        list.addAll(preOrderTraversal(currentNode.rightChild));
        list.add(currentNode);

        return list;
    } // end method postOrderTraversal

    /**
    *   Returns an iterable list of every
    *   node in this tree, by level.
    */
    public ArrayList<T> byLevel(){
        ArrayList list = new ArrayList();

        if (this.isEmpty())
            return list;

        MyQueue<Node> q = new MyQueue();
        q.enqueue(this.root);

        Node currentNode;
        while(!q.isEmpty()){
            currentNode = q.dequeue();
            list.add(currentNode);
            if (currentNode.hasLeftChild())
                q.enqueue(currentNode.leftChild);
            if (currentNode.hasRightChild())
                q.enqueue(currentNode.rightChild);
        }

        return list;
    } // end method breadthFirstTraversal

    /**
    *   Calculates the width of this tree,
    *   i.e. the number of nodes in the
    *   level with the most nodes.
    */
    public int width(){
        if (this.isEmpty()) return 0;
        MyQueue<Node> q = new MyQueue();

        q.enqueue(this.root);  // load queue with the nodes of the first level, ie the root.
        int currentWidth = 1;  // width of the first level is 1.
        int width = 1;

        while(!q.isEmpty()){
            loadQueueWithNextLevel(q);              // replace the nodes in the current level, with those in the next level.
            currentWidth = q.size();                // count the nodes in the new level.
            width = Math.max(width, currentWidth);  // if the new level has more nodes than the previous leve, update the width.
        }

        return width;
    } // end method width

    /**
    *   Given a queue containing every node in
    *   one level of a tree, replace those nodes
    *   with their children.
    *
    *   @param q a list containing every node at some level of this tree
    */
    private void loadQueueWithNextLevel(MyQueue<Node> q){
        int count = q.size();

        while (count-- != 0){
            Node temp = q.dequeue();

            if (temp.hasLeftChild())
                q.enqueue(temp.leftChild);
            if (temp.hasRightChild())
                q.enqueue(temp.rightChild);
        }
    } // end method loadQueueWithNextLevel

    /**
    *   This inner class provides Nodes
    *   for a binary search tree.
    */
    class Node{
        private T data;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

        public Node(T data){
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        } // end constructor

        public void setData(T data){
            this.data = data;
        } // end method setData

        public void setLeftChild(Node leftChild){
            this.leftChild = leftChild;
            leftChild.parent = this;
        } // end method setLeftChild

        public void setRightChild(Node rightChild){
            this.rightChild = rightChild;
            rightChild.parent = this;
        } // end method setRightChild

        /**
        *   Sets this node's parent.
        *
        *   Careful with this one; if n already has
        *   children, this method doesn't affect them.
        */
        public void setParent(Node n){
            this.parent = n;
        } // end method setParent

        public T getData(){
            return this.data;
        } // end method getData

        public Node getLeftChild(){
            return this.leftChild;
        } // end method getLeftChild

        public Node getRightChild(){
            return this.rightChild;
        } // getRightChild

        public Node getParent(){
            return this.parent;
        } // end method getParent

        public boolean hasLeftChild(){
            if (this.leftChild == null)
                return false;
            return true;
        } // end method hasLeftChild

        public boolean hasRightChild(){
            if (this.rightChild == null)
                return false;
            return true;
        } // end method hasRightChild

        public boolean isRoot(){
            return (root.data == this.data);
        } // end method isRoot

        public boolean isLeaf(){
            return (this.leftChild==null && this.rightChild==null);
        } // end method isLeaf

        public boolean hasOneChild(){
            return (this.hasLeftChild() ^ this.hasRightChild());
        } // end method hasOneChild

        public boolean hasTwoChildren(){
            return(this.hasLeftChild() && this.hasRightChild());
        } // end method hasTwoChildren

        /**
        *   Assumes this node only has one child.
        *   Returns that child.
        */
        public Node onlyChild(){
            if (this.hasLeftChild())
                return this.leftChild;
            else
                return this.rightChild;
        }

        /**
        *   Replaces a particular child of this node
        *   with a new one. Assumes that 'old'
        *   is an actual child of this node.
        *
        *   @param currentChild the child to be replaced
        *   @param newChild the replacer
        */
        public void replaceChild(Node currentChild, Node newChild){
            if (this.leftChild == currentChild){
                this.leftChild = newChild;
            } else {
                this.rightChild = newChild;
            }
        } // end method replaceChild

        @Override
        public String toString(){
            return "" + this.data;
        } // end method toString
    } // end inner class Node
} // end class
