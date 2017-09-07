package assignment1;

import java.util.*;

/**
 * Binary Heap implementation for minimum priority queue used in Dijkstra algorithm
 * 
 * @author Brad Lee
 *
 * @param E : generic, but two methods added for tracing back from Dijkstra network
 */
public class BinHeap<E extends Comparable< ? super E > & BinHeapInfo>
{
    static final int INIT_CAPACITY = 64; // perfect tree of size 63

    private E[] mArray;
    private int mSize;
    private int mCapacity;

    // public  methods -------------------------------------------
    public void makeEmpty() { mSize = 0; };
    public BinHeap() { this(INIT_CAPACITY); }
    @SuppressWarnings("unchecked")
    public BinHeap(int capacity)
    {
        // choose a capacity that is exactly 2^N - 1 for some N (esthetic)
        // which leads to mCapacity 2^N, internally -- user asks for 127, we
        // reserve 128, if they want 128, we have to reserve 256.
        for (mCapacity = INIT_CAPACITY; 
            mCapacity <= capacity;
            mCapacity = 2 * mCapacity
            )
        {
            if (mCapacity < 0)
            {
                mCapacity = INIT_CAPACITY; // give up - overflow
                break;
            }
        }
        mArray = (E[]) new Comparable[mCapacity];  // Int or any Comparable
        makeEmpty();
    }

    /**
     * copy constructor but it's missing callback functions (set heap index)
     * @param items : list of items to be copied
     */
    public BinHeap(E[] items)
    {
        this(items.length);
        int k;

        mSize = items.length;

        // copy starting with position 1 - no ordering yet
        for(k = 0; k < mSize; k++)
            mArray[k+1] = items[k];

        // order the heap
        orderHeap();
    }

    public boolean empty() { return mSize == 0; }

    /**
     * this is the only insertion method for new items
     * @param x : item to be added to the heap
     */
    public void insert(E x)
    {
        if (mSize == mCapacity - 1)
            doubleCapacity();

        percolateUp(++mSize, x);
    }

    /**
     * this will take out minimum item from the heap
     * @return minimum value item, otherwise exception occurs
     */
    public E remove()
    {
        E minObject;

        if (empty())
            throw new NoSuchElementException();

        minObject = mArray[1];
        mArray[1] = mArray[mSize--];
        mArray[1].setHeapIndex(1);
        percolateDown(1);

        return minObject;
    }

    /**
     * value (priority) decrease, then adjust the new location of the item in the heap
     * @param hole : index in the queue
     * @param x : object of the item
     */
    public void decrease(int hole, E x)
    {
        if (hole <= mSize)
        {
            percolateUp(hole, x);
        }
    }

    public int size() { return mSize; }

    // private helper methods ------------------------------------
    private void orderHeap()
    {
        int k;

        for (k = mSize/2; k > 0; k--)
            percolateDown(k);
    }
    
    /**
     * percolate up an item to find right location in the heap
     * @param hole : index in the heap
     * @param x : object of the item
     */
    private void percolateUp(int hole, E x)
    { 
        for (; hole > 1 && x.compareTo(mArray[hole/2]) < 0; hole /= 2)
        {
            mArray[hole] = mArray[hole/2];
            mArray[hole].setHeapIndex(hole);
        }
        mArray[hole] = x;
        x.setHeapIndex(hole);
    }
    
    /**
     * percolate down an item to find right location in the heap
     * @param hole
     */
    private void percolateDown(int hole)
    { 
        int child;
        E tmp;

        for (tmp = mArray[hole]; 2 * hole <= mSize; hole = child)
        {
            child = 2 * hole;
            // if 2 children, get the lesser of the two
            if (child < mSize && mArray[child + 1].compareTo(mArray[child]) < 0)
                child++;
            if (mArray[child].compareTo(tmp) < 0)
            {
                mArray[hole] = mArray[child];
                mArray[hole].setHeapIndex(hole);
            }
            else
                break;
        }
        mArray[hole] = tmp;
        mArray[hole].setHeapIndex(hole);
    }

    /**
     * automatic increase in size when insertion require mode space of the heap
     */
    @SuppressWarnings("unchecked")
    private void doubleCapacity()
    {
        E[] oldArray = mArray;
        int oldCapacity = mCapacity;

        mCapacity = 2 * oldCapacity;

        // new will throw exceptions for us so no need to test anything
        mArray = (E[]) new Comparable[mCapacity];    // Int or any Comparable
        System.arraycopy(oldArray, 0, mArray, 0, oldCapacity);
    }
}
