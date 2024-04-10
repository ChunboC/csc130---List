package csc130.list;

public class LinkedList<T> implements List<T> {
	private class Node<E> {
		private E data;
		private Node<E> next;

		public Node() {
			data = null;
			next = null;
		}

		public Node(E data) {
			data = data;
			next = null;
		}

		public Node(E data, Node<E> next) {
			data = data;
			next = next;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}

	// instance variables
	private Node<T> head = null;
	private int numItems = 0;

	@Override
	public void add(T item) {
		if (head == null)
			head = new Node<>(item);
		else {
			Node<T> trav = head;
			while (trav.getNext() != null)
				trav = trav.getNext();
			trav.setNext(new Node<>(item));
		}
		numItems++;
	}

	@Override
	public void add(int index, T item) {
		if (index < 0 || index > numItems)
			throw new RuntimeException("Index out of bounds Exception.");
		Node<T> trav = head;
		Node<T> temp = new Node<>(item);
		if (index == 0) {
			temp.setNext(head);
			head = temp;
		} else {
			for (int i = 0; i < index - 1; i++)
				trav = trav.getNext();
			temp.setNext(trav.getNext());
			trav.setNext(temp);
		}
		numItems++;
	}

	@Override
	public T remove(T item) {
		Node<T> trav = head;
		T oldItem = null;
		// case: empty
		if (head == null) {
			return null;
			// case: item is at head
		} else if (head.getData().equals(item)) {
			oldItem = head.getData();
			head = head.getNext();
			// case: normal
		} else {
			while (trav.getNext() != null) {
				if (trav.getNext().getData().equals(item)) {
					oldItem = trav.getNext().getData();
					trav.setNext(trav.getNext().getNext());
					break;
				}
				trav = trav.getNext();
			}
		}
		numItems--;
		return oldItem;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index > numItems)
			throw new RuntimeException("Index out of bounds Exception.");
		Node<T> trav = head;
		T oldItem = null;
		if (index == 0) {
			oldItem = head.getData();
			head = head.getNext();
		} else {
			for (int i = 0; i < index - 1; i++)
				trav = trav.getNext();
			oldItem = trav.getNext().getData();
			trav.setNext(trav.getNext().getNext());
		}
		numItems--;
		return oldItem;
	}

	@Override
	public boolean contains(T item) {
		return indexOf(item) >= 0;
	}

	@Override
	public int indexOf(T item) {
		Node<T> trav = head;
		for (int i = 0; i < numItems; i++) {
			if (trav.getData().equals(item))
				return i;
			trav = trav.getNext();
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T item) {
		Node<T> trav = head;
		int idx = -1;
		for (int i = 0; i < numItems; i++) {
			if (trav.getData().equals(item))
				idx = i;
			trav = trav.getNext();
		}
		return idx;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index > numItems)
			throw new RuntimeException("Index out of bounds Exception.");
		Node<T> trav = head;
		for (int i = 0; i < index; i++)
			trav = trav.getNext();
		return trav.getData();
	}

	@Override
	public int getSize() {
		return numItems;
	}

	@Override
	public T set(int index, T item) {
		if (index < 0 || index > numItems)
			throw new RuntimeException("Index out of bounds Exception.");
		Node<T> trav = head;
		for (int i = 0; i < index; i++)
			trav = trav.getNext();
		T oldItem = trav.getData();
		trav.setData(item);
		return oldItem;
	}

	@Override
	public T set(T oldItem, T newItem) {
		if (!contains(oldItem))
			throw new RuntimeException("Element not found Exception.");
		Node<T> trav = head;
		for (int i = 0; i < indexOf(oldItem); i++)
			trav = trav.getNext();
		trav.setData(newItem);
		return oldItem;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public void clear() {
		head = null;
		numItems = 0;
	}

}
