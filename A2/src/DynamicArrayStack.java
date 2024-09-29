/**
 * Name: Mingming Zhang
 * 
 * COMP352 Section (AB)
 * 
 * Assignment # 2
 * 
 * Due Date: 09/06/2024
 * 
 * @author Mingming Zhang
 * @version 08/06/2024
 */

/**
 * Stack implementation using dynamic arrays.
 */
public class DynamicArrayStack {
	private int[] stackArray;
	private String[] stringStackArray;
	private int top;
	private int capacity;

	/**
	 * Constructor
	 * 
	 * @param initialCapacity capacity
	 */
	public DynamicArrayStack(int initialCapacity) {
		stackArray = new int[initialCapacity];
		stringStackArray = new String[initialCapacity];
		top = -1;
		capacity = initialCapacity;
	}

	/**
	 * Push an integer element onto the stack
	 * 
	 * @param element int
	 */
	public void push(int element) {
		// Check if the stack is full, double the size if needed
		if (top == capacity - 1) {
			resize();
		}
		stackArray[++top] = element;
	}

	/**
	 * Push a string element onto the stack
	 * 
	 * @param element String
	 */
	public void pushString(String element) {
		// Check if the stack is full, double the size if needed
		if (top == capacity - 1) {
			resize();
		}
		stringStackArray[++top] = element;
	}

	/**
	 * Pop an integer element from the stack
	 * 
	 * @return int
	 */
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return stackArray[top--];
	}

	/**
	 * Pop a string element from the stack
	 * 
	 * @return String
	 */
	public String popString() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return stringStackArray[top--];
	}

	/**
	 * Peek the top integer element of the stack
	 * 
	 * @return int
	 */
	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return stackArray[top];
	}

	/**
	 * Peek the top string element of the stack
	 * 
	 * @return String
	 */
	public String peekString() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return stringStackArray[top];
	}

	/**
	 * Check if the stack is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return top == -1;
	}

	/**
	 * Resize the stack when it is full
	 */
	private void resize() {
		int newCapacity = capacity * 2;
		int[] newArray = new int[newCapacity];
		for (int i = 0; i < capacity; i++) {
			newArray[i] = stackArray[i];
		}
		stackArray = newArray;
		capacity = newCapacity;
	}

	/**
	 * override toString
	 */
	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		String result = "[";
		for (int i = 0; i <= top; i++) {
			result += stackArray[i];
			if (i < top) {
				result += ", ";
			}
		}
		result += "]";
		return result;
	}
}