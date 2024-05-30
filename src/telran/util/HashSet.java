package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	List<T>[] hashTable;
	int size;
	float factor;

	private class HashSetIterator implements Iterator<T> {
		private int currentBucket = 0;
		private Iterator<T> listIterator = getNextIterator();
		private boolean ifHasNext = false;

		@Override
		public boolean hasNext() {
			ifHasNext = false;
			if (listIterator != null) {
				if (listIterator.hasNext()) {
					ifHasNext = true;
				} else {
					while (currentBucket + 1 < hashTable.length && !ifHasNext) {
						currentBucket++;
						if (hashTable[currentBucket] != null) {
							listIterator = hashTable[currentBucket].iterator();
							if (listIterator.hasNext()) {
								ifHasNext = true;
							}
						}
					}
				}
			}
			return ifHasNext;
		}

		@Override
		public T next() {
			T res = null;
			if (hasNext()) {
				res = listIterator.next();
			} else {
				throw new NoSuchElementException();
			}
			return res;
		}

		private Iterator<T> getNextIterator() {
			Iterator<T> res = null;
			boolean found = false;
			while (currentBucket < hashTable.length && !found) {
				if (hashTable[currentBucket] != null) {
					res = hashTable[currentBucket].iterator();
					found = true;
				}
				if (!found) {
					currentBucket++;
				}
			}
			return res;
		}
	}

	public HashSet(int hashTableLength, float factor) {
		hashTable = new List[hashTableLength];
		this.factor = factor;
	}

	public HashSet() {
		this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (!contains(obj)) {
			if ((float) size / hashTable.length >= factor) {
				hashTableReallocation();
			}
			addObjInHashTable(obj, hashTable);
			size++;
			res = true;
		}

		return res;
	}

	private void hashTableReallocation() {
		List<T>[] tmp = new List[hashTable.length * 2];
		for (List<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					addObjInHashTable(obj, tmp);
				}
			}
		}
		hashTable = tmp;

	}

	private void addObjInHashTable(T obj, List<T>[] table) {
		int index = getIndex(obj);
		List<T> list = table[index];
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		list.add(obj);

	}

	private int getIndex(T obj) {
		int hashCode = obj.hashCode();
		int index = Math.abs(hashCode % hashTable.length);
		return index;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = getIndex(pattern);
		List<T> list = hashTable[index];
		if (list != null) {
			boolean removed = list.remove(pattern);
			if (removed) {
				size--;
				if (list.size() == 0) {
					hashTable[index] = null;
				}
				res = true;
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getIndex(pattern);
		List<T> list = hashTable[index];
		return list != null && list.contains(pattern);
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new HashSetIterator();
	}

	@Override
	public T get(T pattern) {
		T res = null;
		boolean found = false;
		int index = getIndex(pattern);
		List<T> list = hashTable[index];
		if (list != null) {
			for (T element : list) {
				if (!found && element.equals(pattern)) {
					res = element;
					found = true;
				}
			}
		}
		return res;
	}
}
