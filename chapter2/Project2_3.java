/* Directions:
 * The removeMax() method in Programming Project 2.2 suggests a way to sort
 * the contents of an array by key value. Implement a sorting scheme that
 * does not require modifying the HighArray class, but only the code in
 * main(). You’ll need a second array, which will end up inversely sorted.
 * (This scheme is a rather crude variant of the selection sort in Chapter
 * 3, “Simple Sorting.”)
 */

package chapter2;

public class Project2_3 {
	public static void main(String[] args) {
		final int SIZE = 5;
		
		HighArray arr = new HighArray(SIZE);
	
		arr.insert(116);
		arr.insert(9);
		arr.insert(0);
		arr.insert(4);
		arr.insert(17);
	
		arr.display();
			
		HighArray arr2 = new HighArray(SIZE);
		
		while (!arr.isEmpty()) {
			arr2.insert(arr.removeMax());
		}
	
		arr.display();
		arr2.display();
	}
}