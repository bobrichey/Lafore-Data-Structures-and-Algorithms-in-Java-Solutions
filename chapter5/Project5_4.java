/* Directions:
 * Implement a stack class based on the circular list of Programming Project 
 * 5.3. This exercise is not too difficult. (However, implementing a queue can 
 * be harder, unless you make the circular list doubly linked.)
 */

package chapter5;

public class Project5_4 {
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.display();
		
		// add 0 - 7 to stack
		for (int i = 0; i < 8; i++) {
			stack.push(i);
		}
		// check top
		System.out.println(stack.peek()); // displays right to left
		stack.display();
		
		// pop first two items, push back on
		int x = stack.pop();
		int y = stack.pop();
		stack.push(y);
		stack.push(x);
		stack.display();
		
		// empty the stack
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
		stack.display();
		
		// test pop function w/ repeats in stack
		stack.push(1);
		stack.push(5);
		stack.push(5);
		stack.push(4);
		stack.push(5);
		stack.display();
		stack.pop();
		stack.display();
		stack.pop();
		stack.display();
		
		Queue q = new Queue();
		
		for (int i = 0; i < 8; i++) {
			q.insert(i);
		}
		q.display();
		
		q.remove();
		q.remove();
		q.insert(8);
		q.display();
		
		q.remove();
		q.remove();
		q.insert(9);
		q.display();
		
		System.out.println(q.peekFront());
		System.out.println(q.getSize());
	}
}

class Stack {
	CircularLinkedList list;

	public Stack() {
		list = new CircularLinkedList();
	}
	
	public int pop() {
		return list.deleteCurrent().data;
	}
	
	public void push(int value) {
		list.insert(value);
	}
	
	public int peek() {
		return list.getCurrent().data;
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	// circles the list, displaying current last 
	public void display() {
		list.display();
	}
}

class Queue {
	CircularLinkedList list;

	public Queue() {
		list = new CircularLinkedList();
	}
	
	public void insert(int value) {
		list.insert(value);
	}
	
	public int remove() {
		return list.deleteNext().data;
	}
	
	public int peekFront() {
		return list.getNext().data;
	}
	
	public int peekRear() {
		return list.getCurrent().data;
	}
	
	public int getSize() {
		return list.getSize();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void display() {
		list.display();
	}
}