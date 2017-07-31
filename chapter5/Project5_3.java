/* Directions:
 * A circular list is a linked list in which the last link points back to the 
 * Next link. There are many ways to design a circular list. Sometimes there 
 * is a pointer to the “start” of the list. However, this makes the list less 
 * like a real circle and more like an ordinary list that has its end attached 
 * to its beginning. Make a class for a singly linked circular list that has 
 * no end and no beginning. The only access to the list is a single reference, 
 * current, that can point to any link on the list. This reference can move 
 * around the list as needed. (See Programming Project 5.5 for a situation in 
 * which such a circular list is ideally suited.) Your list should handle 
 * insertion, searching, and deletion. You may find it convenient if these 
 * operations take place one link downstream of the link pointed to by current. 
 * (Because the upstream link is singly linked, you can’t get at it without 
 * going all the way around the circle.) You should also be able to display the 
 * list (although you’ll need to break the circle at some arbitrary point to 
 * print it on the screen). A step() method that moves current along to the 
 * next link might come in handy too.
 */

package chapter5;
// See CircularLinkedList.java for data structure implementation
public class Project5_3 {
	public static void main(String[] args) {
		CircularLinkedList c = new CircularLinkedList();
		c.display();
		
		c.insert(1);
		c.display();
		
		c.insert(2);
		c.insert(3);
		c.insert(4);
		c.insert(5);
		c.display(); // right to left
		
		c.delete(3);
		c.display();
		System.out.println(c.find(2).data);
		
		// test delete function w/ repeats in list
		c.insert(2);
		c.display();
		c.delete(2);
		c.display();
		c.insert(50);
		c.display();
		System.out.println(c.deleteNext().data);
		c.insert(60);
		c.display();
		System.out.println(c.deleteNext().data);
		c.display();
		System.out.println(c.deleteNext().data);
		c.insert(500);
		c.display();
		System.out.println(c.deleteNext().data);
	}
}