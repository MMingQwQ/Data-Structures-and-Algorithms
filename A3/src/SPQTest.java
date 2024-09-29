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
 * SPQTest class to test the functionality of the SPQ ADT.
 */
public class SPQTest {
	/**
	 * main method
	 * 
	 * @param args command line args
	 */
	public static void main(String[] args) {
		SPQ spq = new SPQ();

		// Example 1: Insert elements //maintain the min-heap firstly
		spq.insert(5, 50);
		spq.insert(3, 30);
		spq.insert(8, 80);
		spq.insert(1, 10);
		spq.insert(4, 40);
		spq.insert(7, 70);
		spq.insert(6, 60);
		spq.insert(2, 20);

		// Example 2: Display the top element
		System.out.println("Top element: " + spq.top() + "\n");

		// Example 3: Remove the top element
		System.out.println("Removed top element: " + spq.removeTop());
		System.out.println("New top element: " + spq.top() + "\n");

		// Example 4: Replace the key of an element
		SPQ.Entry entry = spq.insert(9, 90);
		System.out.println("Old key: " + spq.replaceKey(entry, 0));
		System.out.println("New top element after key replacement: " + spq.top() + "\n");

		// Example 5: Replace the value of an element
		SPQ.Entry entry2 = spq.insert(10, 100);
		System.out.println("Old value: " + spq.replaceValue(entry2, 101));
		System.out.println("Entry with new value: (" + entry2.key + ", " + entry2.value + ")" + "\n");

		// Example 6: Remove a specific element
		System.out.println("Removed specific element: " + spq.remove(entry2));
		System.out.println("Top element after removal: " + spq.top() + "\n");

		// Example 7: Check if the SPQ is empty
		System.out.println("Is the SPQ empty? " + spq.isEmpty() + "\n");

		// Example 8: Get the size of the SPQ
		System.out.println("Size of SPQ: " + spq.size() + "\n");

		// Example 9: Toggle the SPQ to max-heap
		spq.toggle();
		System.out.println("State after toggle: " + spq.state()); // Should be "Max"
		System.out.println("Top element after toggle: " + spq.top() + "\n");

		// Example 10: Toggle back to min-heap
		spq.toggle();
		System.out.println("State after toggle: " + spq.state()); // Should be "Min"
		System.out.println("Top element after toggle: " + spq.top() + "\n");

		// Example 11: Insert elements to trigger array resize
		for (int i = 11; i <= 20; i++) {
			spq.insert(i, i * 10);
		}
		System.out.println("Size after multiple inserts: " + spq.size() + "\n");

		// Example 12: Remove all elements
		while (!spq.isEmpty()) {
			System.out.println("Removing: " + spq.removeTop());
		}
		System.out.println("Is the SPQ empty after removing all elements? " + spq.isEmpty() + "\n"); // true

		// Example 13: Insert elements in descending order
		for (int i = 10; i >= 1; i--) {
			spq.insert(i, i * 10);
		}
		System.out.println("Top element after inserting in descending order: " + spq.top() + "\n");

		// Example 14: Insert elements in ascending order
		for (int i = 11; i <= 20; i++) {
			spq.insert(i, i * 10);
		}
		System.out.println("Top element after inserting in ascending order: " + spq.top() + "\n"); // Should still be 1

		// Example 15: Toggle to max-heap and check top element
		spq.toggle();
		System.out.println("State after toggle: " + spq.state()); // Should be "Max"
		System.out.println("Top element after toggle to max-heap: " + spq.top() + "\n"); // Should be 20

		// Example 16: Replace key to trigger up-heap operation
		SPQ.Entry entry3 = spq.insert(5, 500);
		spq.replaceKey(entry3, 25); // Should move to top in max-heap
		System.out.println("Top element after key replacement: " + spq.top() + "\n"); // Should be 25

		// Example 17: Replace key to trigger down-heap operation
		spq.replaceKey(entry3, 3); // Should move down in max-heap
		System.out.println("Top element after key replacement: " + spq.top() + "\n"); // Should be 20

		// Example 18: Remove specific element to trigger heap adjustments
		SPQ.Entry entry4 = spq.insert(22, 220);
		System.out.println("Removed specific element: " + spq.remove(entry4)); // Should remove entry with key 22
		System.out.println("Top element after removing specific element: " + spq.top() + "\n"); // Should be 20

		// Example 19: Insert and remove elements to test stability
		spq.insert(21, 210);
		spq.insert(23, 230);
		spq.insert(24, 240);
		spq.insert(26, 260);
		spq.removeTop();
		System.out.println("Top element after removing top element: " + spq.top() + "\n");

		// Example 20: Final state of SPQ
		while (!spq.isEmpty()) {
			System.out.println("Removing: " + spq.removeTop());
		}
		System.out.println("Final state - Is the SPQ empty? " + spq.isEmpty()); // Should return true
	}
}
