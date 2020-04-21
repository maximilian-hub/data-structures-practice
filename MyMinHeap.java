import java.util.Arrays;

/**
*   This class implements a min-heap,
*   built from an array.
*
*   For educational purposes.
*
*   @author         Maximilian Spedale
*   Created:        April 18, 2020
*   Last updated:   April 21, 2020
*/

public class MyMinHeap<T extends Comparable<T>>{
    private int capacity;
    private int size;
    private T[] elements;


    public MyMinHeap(){
        capacity = 10;
        size = 0;
        elements = (T[]) new Comparable[capacity];
    }

    public MyMinHeap(int capacity){
        this.capacity = capacity;
        size = 0;
        elements = (T[]) new Comparable[capacity];
    }

    public MyMinHeap(T[] data){
        capacity = data.length;
        size = 0;
        elements = (T[]) new Comparable[capacity];

        for (T d : data) {
            this.insert(d);
        }
    }

    public boolean isEmpty(){return size == 0;       }
    public boolean isFull() {return size == capacity;}

    private int leftChildIndex(int i)   {return i*2 + 1;}
    private int rightChildIndex(int i)  {return i*2 + 2;}
    private int parentIndex(int i)      {return (i-1)/2;}

    private T leftChild(int i)  {return elements[leftChildIndex(i)];    }
    private T rightChild(int i) {return elements[rightChildIndex(i)];   }
    private T parent(int i)     {return elements[parentIndex(i)];       }

    private boolean hasLeftChild(int i) {return leftChildIndex(i) < size;   }
    private boolean hasRightChild(int i){return rightChildIndex(i) < size;  }
    private boolean hasParent(int i)    {return parentIndex(i) >= 0;        }

    /**
        Inserts an element into this heap,
        preserving the MinHeap property.
        @param data the object to be inserted
    */
    public void insert(T data){
        if (data == null) return;

        ensureExtraCapacity();
        elements[size] = data;
        size++;
        bubbleUp();
    }

    /**
        Restores the heap property.
        Assumes an insertion has just occurred.
    */
    private void bubbleUp(){
        int i = size-1; // the index of the inserted element.

        while (hasParent(i) && (parent(i).compareTo(elements[i]) > 0)){
            swap(i, parentIndex(i)); // Swap the inserted element with its parent,
            i = parentIndex(i);      // and update the position of the inserted element.
        }
    }

    /**
        Removes the root (minimum value) of
        this heap, while maintaining the
        heap property.
    */
    public T remove(){
        if (this.isEmpty()) throw new IllegalStateException();

        T element = elements[0];        // Store the first element,
        elements[0] = elements[size-1]; // copy the last element to the front,
        elements[size-1] = null;        // delete the last element,
        size--;                         // adjust the heap's size,
        bubbleDown();                   // and restore the heap property.

        return element;
    }

    /**
        Restores the heap property.
        Assumes the root has just been deleted.
    */
    private void bubbleDown(){
        int i=0; // the index of the element being bubbled down.

        while (hasLeftChild(i)) {                           // because of how heaps fill up, you only need to check the left side.
            int smallerChildIndex = smallerChildIndex(i);

            if (elements[i].compareTo(elements[smallerChildIndex]) < 0) {   // If the heap is in order,
                break;                                                      // do nothing.
            } else {                        // If the heap is still out of order,
                swap(i, smallerChildIndex); // swap the current element with its smaller child.
                i = smallerChildIndex;      // and update the current element's position.
            }
        }
    }

    /**
        Returns the index of a "node's" smaller child.
        Assumes that index has at least a left child.

        @param i the index of the node whose children are being examined
        @return the smaller of i's children, or i's only child
    */
    private int smallerChildIndex(int i){
        int smallerChildIndex = leftChildIndex(i);

        if (hasRightChild(i) && (rightChild(i).compareTo(leftChild(i)) < 0)) {
            smallerChildIndex = rightChildIndex(i);
        }

        return smallerChildIndex;
    }

    /**
        Swaps the elements at two indexes.
    */
    private void swap(int i1, int i2){
        T temp = elements[i1];
        elements[i1] = elements[i2];
        elements[i2] = temp;
    }

    /**
        If this heap is full, this method
        doubles this heap's capacity.
    */
    private void ensureExtraCapacity(){
        if (this.isFull()) {
            elements = Arrays.copyOf(elements, capacity*2);
            capacity = capacity*2;
        }
    }

    public T peek(){
        if (this.isEmpty())
            throw new IllegalStateException();

        return elements[0];
    }

    /**
        @return a string containing each element in this heap,
                separated by spaces.
    */
    public String toString(){
        String output = "";

        for (T element : elements){
            if (element != null)
                output += "" + element + " ";
        }

        return output.trim();
    }
} // end class
