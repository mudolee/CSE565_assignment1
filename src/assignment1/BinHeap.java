package assignment1;

import java.util.*;

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

    public void insert(E x)
    {
        if (mSize == mCapacity - 1)
            doubleCapacity();

        percolateUp(++mSize, x);
    }

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

    public int size() { return mSize; }

    // private helper methods ------------------------------------
    private void orderHeap()
    {
        int k;

        for (k = mSize/2; k > 0; k--)
            percolateDown(k);
    }
    
    public void decrease(int hole, E x)
    {
        if (hole <= mSize)
        {
            percolateUp(hole, x);
        }
    }

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
