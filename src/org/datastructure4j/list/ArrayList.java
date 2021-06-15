package org.datastructure4j.list;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
	// instance variables
	public static final int CAPACITY = 16; // default array capacity

	private E[] data; // generic array used for storage

	private int size = 0; // current number of elements
	// constructors

	public ArrayList() {
		this(CAPACITY);
	} // constructs list with default capacity

	public ArrayList(int capacity) { // constructs list with given capacity
		data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
	}

	// public methods
	/** Returns the number of elements in the array list. */
	public int size() {
		return size;
	}

	/** Returns whether the array list is empty. */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Returns (but does not remove) the element at index i. */
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}

	/** Replaces the element at index i with e, and returns the replaced element. */
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}

	/**
	 * Inserts element e to be at index i, shifting all subsequent elements later.
	 */
	public void insert(int i, E e) throws IndexOutOfBoundsException, IllegalStateException {
		checkIndex(i, size + 1);
		if (size == data.length) // not enough capacity
			throw new IllegalStateException("Array is full");
		for (int k = size - 1; k >= i; k--) // start by shifting rightmost
			data[k + 1] = data[k];
		data[i] = e; // ready to place the new element
		size++;
	}

	/**
	 * Removes/returns the element at index i, shifting subsequent elements earlier.
	 */
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		for (int k = i; k < size - 1; k++) // shift elements to fill hole
			data[k] = data[k + 1];
		data[size - 1] = null; // help garbage collection
		size--;
		return temp;
	}

	// utility method
	/** Checks whether the given index is in the range [0, n−1]. */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + i);
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int count=0;
			
			@Override
			public boolean hasNext() {
				return count<size;
			}

			@Override
			public E next() {
				if(hasNext())
					return data[count++];
				else
					throw new UnsupportedOperationException() ; 
			}
		};
	}
	/**
	 * Add the element at the end of array 
	 * */
	@Override
	public void add(E e) throws IndexOutOfBoundsException {
		ensureCapacityInternal(size + 1); 
		data[this.size++]=e;
	}
	
	/**
	 * 
	 * */
	
	 /**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param   minCapacity   the desired minimum capacity
     */

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_DATA = {};

    public void ensureCapacity(int minCapacity) {
        int minExpand = (data != EMPTY_DATA)
            // any size if real element table
            ? 0
            // larger than default for empty table. It's already supposed to be
            // at default size.
            : CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (data == EMPTY_DATA) {
            minCapacity = Math.max(CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
       // overflow-conscious code
        if (minCapacity - data.length > 0)
            grow(minCapacity);
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        data = Arrays.copyOf(data, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
}
