import java.util.LinkedList;

/**
*   This class implements a Queue, a
*   data structure that operates under
*   a FIFO (First In, First Out) structure.
*
*   This Queue is built on an LinkedList;
*   the beginning of the list acts as the
*   "front" of the line, and the end acts
*   as the "back" of the line.
*
*   @author         Max Spedale
    Created:        March 3, 2020
*   Last updated:   March 15, 2020
*/
public class MyQueue<T>{
    private LinkedList<T> list;

    /**
    *   Builds an empty queue.
    */
    public MyQueue(){
        this.list = new LinkedList();
    } // end constructor


    /**
    *   Adds a new element to the
    *   end of the queue.
    *
    *   @param element the element to be added
    */
    public void enqueue(T element){
        this.list.add(element);
    } // end method enqueue

    /**
    *   Removes the element at the
    *   front of the queue.
    *
    *   @return the first in line
    */
    public T dequeue(){
        return this.list.remove();
    } // end method dequeue

    /**
    *   Retrieves, but does not remove, the
    *   head of this queue.
    *
    *   Returns null if this queue is empty.
    */
    public T peek(){
        return this.list.get(0);
    } // end method peek

    /**
    *   Tests if the queue is empty.
    */
    public boolean isEmpty(){
        return this.list.isEmpty();
    } // end method isEmpty

    /**
    *   @return the number of elements in
    *   this queue
    */
    public int size(){
        return this.list.size();
    } // end method size
} // end class
