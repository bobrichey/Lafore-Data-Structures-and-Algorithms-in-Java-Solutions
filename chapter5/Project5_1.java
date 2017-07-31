/* Directions:
 * Implement a priority queue based on a sorted linked list. The remove 
 * operation on the priority queue should remove the item with the smallest 
 * key.
 */

package chapter5;

import java.util.Random;

public class Project5_1 {
	public static void main(String[] args) {
		PriorityQ pq = new PriorityQ();
		Random r = new Random();
		
		pq.display();
		
		for (int i = 0; i < 8; i++) {
			pq.insert(r.nextInt(8));
		}
		pq.display();
		
		pq.remove();
		pq.remove();
		
		pq.display();
		
		System.out.println(pq.peekMin());
		
		while (!pq.isEmpty()) {
			pq.remove();
		}
		pq.display();
	}
}

class PriorityQ {
	SortedList queue;
	
	public PriorityQ() {
		queue = new SortedList();
	}
	
	public void insert(int value) {
		queue.insert(value);
	}
	
	public int remove() {
		return queue.deleteFirst().data;
	}
	
	public int peekMin() {
		return queue.getFirst().data;
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public void display() {
		queue.display();
	}
}