package org.datastructure4j.list;

public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); // an empty list

	public LinkedStack() {
	} // new stack relies on the initially empty list

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E element) {
		list.addFirst(element);
	}

	public E top() {
		return list.first();
	}

	public E pop() {
		return list.removeFirst();
	}

	/** A generic method for reversing an array. */
	public static <E> void reverse(E[] a) {
		Stack<E> buffer = new ArrayStack<>(a.length);
		for (int i = 0; i < a.length; i++)
			buffer.push(a[i]);
		for (int i = 0; i < a.length; i++)
			a[i] = buffer.pop();
	}

}