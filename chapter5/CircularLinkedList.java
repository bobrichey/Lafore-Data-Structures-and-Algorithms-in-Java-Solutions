package chapter5;

public class CircularLinkedList {
	Link current;

	public CircularLinkedList() {
		current = null;
	}
	
	public void insert(int key) {
		Link newLink = new Link(key);
		
		if (isEmpty()) {
			current = newLink;
			newLink.next = current;
		}
		else {
			newLink.next = current.next;
			current.next = newLink;
			current = newLink;
		}
	}
	
	// assumes key is in list
	public Link find(int key) {
 		while (current.data != key) {
			step();
		}
		return current;
	}
	
	// assumes key is in list
	public Link delete(int key) {
		while (current.next.data != key) {
			step();
		}
		Link temp = current.next;
		current.next = current.next.next;
		return temp;
	}
	
	public void step() {
		current = current.next;
	}
	
	public Link getCurrent() {
		return current;
	}
	
	public Link deleteCurrent() {
		Link temp = current;
		if (current == current.next) {
			current = null;
		}
		else {
			while (current.next != temp) {
				step();
			}
			current.next = current.next.next;
		}
		return temp;
	}
	
	// returns Link following current, used for queue
	public Link getNext() {
		return current.next;
	}
	
	// deletes Link following current, used for queue
	public Link deleteNext() {
		Link temp = getNext();
		current.next = current.next.next;
		return temp;
	}
	
	public boolean isEmpty() {
		return current == null;
	}
	
	public int getSize() {
		if (isEmpty()) {
			return 0;
		} 
		else {
			int numLinks = 1; // count current
			Link pointer = current.next;
			
			while (pointer != current) {
				pointer = pointer.next;
				numLinks++;
			}
			return numLinks;
		}
	}
	
	// circles the list, displaying current last 
	public void display() {
		if (isEmpty()) {
			System.out.println("--");
		} 
		else {
			Link pointer = current.next; // first item inserted
			
			while (pointer != current) {
				System.out.print(pointer.data + " ");
				pointer = pointer.next;
			}
			System.out.println(pointer.data);
		}
	}
}
