package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private int size;
	private T[] array;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	private class ArrayListIterator implements Iterator<T> {
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[currentIndex++];
		}

	}

	@Override
	/**
	 * adds object
	 */
	public boolean add(T obj) {
		if (size == array.length) {
			allocate();
		}
		array[size++] = obj;
		return true;
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		boolean res = false;
		if (index > -1) {
			res = true;
			remove(index);
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		return indexOf(pattern) > -1;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public void add(int index, T obj) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		if (size == array.length) {
			allocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;

	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}

		T removedElement = array[index];

		int i = index;
		while (i < size - 1) {
			array[i] = array[i + 1];
			i++;
		}

		array[size - 1] = null;
		size--;

		return removedElement;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i = 0;
		while (i < size && res == -1) {
			if (array[i].equals(pattern)) {
				res = i;
			}
			i++;
		}
		return res;

	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int i = size - 1;
		while (i >= 0 && res == -1) {
			if (array[i].equals(pattern)) {
				res = i;
			}
			i--;
		}
		return res;

	}

	@Override
	public int size() {
		return size;
	}

}
