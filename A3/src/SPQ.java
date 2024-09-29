/**
 * Name: Mingming Zhang
 * 
 * COMP352 Section (AB)
 * 
 * Assignment # 3
 * 
 * Due Date: 23/06/2024
 * 
 * @author Mingming Zhang
 * @version 16/06/2024
 */

/**
 * SPQ ADT that supports both min-heap and max-heap configurations. The heap is
 * implemented using a dynamically extendable array.
 */
public class SPQ {
	private Entry[] array;
	private int size;
	private boolean isMinHeap;

	/**
	 * constructor
	 */
	public SPQ() {
		this.array = new Entry[10];
		this.size = 0;
		this.isMinHeap = true;
	}

	/**
	 * Toggles the priority queue between min-heap and max-heap.
	 */
	public void toggle() {
		isMinHeap = !isMinHeap;
		// Rebuild the heap by heapifying down
		// from the last non-leaf node up to the root
		for (int i = (size() - 1) / 2; i >= 0; i--) {
			heapifyDown(i);
		}
	}

	/**
	 * Removes and returns the top entry (smallest or biggest key).
	 * 
	 * @return the top entry object.
	 */
	public Entry removeTop() {
		if (isEmpty()) {
			return null;
		}
		Entry top = array[0];
		array[0] = array[size - 1];// assign the last node to top
		size--;
		heapifyDown(0); // then heapifydown
		return top;
	}

	/**
	 * Inserts a key-value pair into the priority queue.
	 * 
	 * @param key   the key of the entry.
	 * @param value the value of the entry.
	 * @return the inserted entry object.
	 */
	public Entry insert(int key, int value) {
		if (size == array.length) {
			resize();
		}
		Entry entry = new Entry(key, value);
		array[size] = entry;
		size++;
		heapifyUp(size - 1);
		return entry;
	}

	/**
	 * Returns the top entry without removing it.
	 * 
	 * @return the top entry object.
	 */
	public Entry top() {
		if (isEmpty()) {
			return null;
		}
		return array[0];
	}

	/**
	 * Removes a specific entry from the priority queue.
	 * 
	 * @param e the entry to be removed.
	 * @return the removed entry object.
	 */
	public Entry remove(Entry e) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(e)) {
				Entry entry = array[i];
				array[i] = array[size - 1];
				size--;
				heapifyDown(i);
				return entry;
			}
		}
		return null;
	}

	/**
	 * Replaces the key of a specific entry.
	 * 
	 * @param e      the entry to be updated.
	 * @param newKey the new key.
	 * @return the old key.
	 */
	public int replaceKey(Entry e, int newKey) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(e)) {
				int oldKey = array[i].key;
				array[i].key = newKey;
				if (isMinHeap) {
					if (newKey < oldKey) {
						heapifyUp(i);
					} else {
						heapifyDown(i);
					}
				} else {
					if (newKey > oldKey) {
						heapifyUp(i);
					} else {
						heapifyDown(i);
					}
				}
				return oldKey;
			}
		}
		return -1;
	}

	/**
	 * Replaces the value of a specific entry.
	 * 
	 * @param e        the entry to be updated.
	 * @param newValue the new value.
	 * @return the old value.
	 */
	public int replaceValue(Entry e, int newValue) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(e)) {
				int oldValue = array[i].value;
				array[i].value = newValue;
				return oldValue;
			}
		}
		return -1;
	}

	/**
	 * Returns the current state of the priority queue (Min or Max).
	 * 
	 * @return the current state as a string.
	 */
	public String state() {
		return isMinHeap ? "Min" : "Max";
	}

	/**
	 * Checks if the priority queue is empty.
	 * 
	 * @return true if the priority queue is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of entries in the priority queue.
	 * 
	 * @return the number of entries.
	 */
	public int size() {
		return size;
	}

	/**
	 * Swaps two elements in the array.
	 * 
	 * @param i the first index.
	 * @param j the second index.
	 */
	private void swap(int i, int j) {
		Entry temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Ensures the heap property is maintained as an element is moved up the tree.
	 * 
	 * @param index the index of the element to heapify up.
	 */

	private void heapifyUp(int index) {
		int parentIndex = (index - 1) / 2;
		if (parentIndex < 0) {
			return;
		}
		if (isMinHeap) {
			if (array[index].key < array[parentIndex].key) {
				swap(index, parentIndex);
				heapifyUp(parentIndex);
			}
		} else {
			if (array[index].key > array[parentIndex].key) {
				swap(index, parentIndex);
				heapifyUp(parentIndex);
			}
		}
	}

	/**
	 * Ensures the heap property is maintained as an element is moved down the tree.
	 * 
	 * @param index the index of the element to heapify down.
	 */
	private void heapifyDown(int index) {
		int leftChildIndex = 2 * index + 1;
		int rightChildIndex = 2 * index + 2;
		int swapIndex = index;

		if (leftChildIndex < size) {
			if (isMinHeap) {
				if (array[leftChildIndex].key < array[swapIndex].key) {
					swapIndex = leftChildIndex;
				}
			} else {
				if (array[leftChildIndex].key > array[swapIndex].key) {
					swapIndex = leftChildIndex;
				}
			}
		}

		if (rightChildIndex < size) {
			if (isMinHeap) {
				if (array[rightChildIndex].key < array[swapIndex].key) {
					swapIndex = rightChildIndex;
				}
			} else {
				if (array[rightChildIndex].key > array[swapIndex].key) {
					swapIndex = rightChildIndex;
				}
			}
		}

		if (swapIndex != index) {
			swap(index, swapIndex);
			heapifyDown(swapIndex);
		}
	}

	/**
	 * Resizes the array when it reaches capacity.
	 */
	private void resize() {
		Entry[] newArray = new Entry[array.length * 2];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	/**
	 * A class representing an entry in the priority queue.
	 */
	public static class Entry {
		int key;
		int value;

		/**
		 * constuctor
		 * 
		 * @param key   int
		 * @param value int
		 */
		public Entry(int key, int value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Entry entry = (Entry) o;
			return key == entry.key && value == entry.value;
		}

		@Override
		public String toString() {
			return "Entry{key=" + key + ", value=" + value + "}";
		}
	}

}
