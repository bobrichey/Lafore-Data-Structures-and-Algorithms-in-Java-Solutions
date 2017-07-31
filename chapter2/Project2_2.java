/* Directions:
 * Modify the method in Programming Project 2.1 so that the item with the
 * highest key is not only returned by the method, but also removed from the
 * array. Call the method removeMax().
 */

package chapter2;

public class Project2_2 {
	public static void main(String[] args) {
		HighArray arr = new HighArray(5);
	
		arr.insert(116);
		arr.insert(9);
		arr.insert(0);
		arr.insert(4);
		arr.insert(17);
	
		arr.display();
	
		System.out.println(arr.removeMax());
		
		arr.display();
		
		while (!arr.isEmpty()) {
			arr.removeMax();
		}
	
		arr.display();
		
		System.out.println(arr.removeMax());
	}
}