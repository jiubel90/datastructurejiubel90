package org.datastructure4j.tree;

import org.datastructure4j.Position;

/** An abstract base class providing some functionality of the Tree interface. */
public abstract class AbstractTree<E> implements Tree<E> {
	
	public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }
	
	public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }
	
	public boolean isRoot(Position<E> p) { return p == root(); }
	
	public boolean isEmpty() { return size() == 0; }
	
	/** Returns the height of the subtree rooted at Position p. */
	public int height(Position<E> p) {
		int h = 0; // base case if p is external
		for (Position<E> c : children(p))
			h = Math.max(h, 1 + height(c));
	return h;
	}
}
