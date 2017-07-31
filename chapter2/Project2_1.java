/* Directions:
 * add a method called getMax() that returns the value of the highest key in
 * the array, or –1 if the array is empty. Add some code in main() to
 * exercise this method. You can assume all the keys are positive numbers.
 */

package chapter2;

public class Project2_1 {
	public static void main(String[] args) {
		HighArray arr = new HighArray(5);
	
		arr.insert(6);
		arr.insert(9);
		arr.insert(0);
		arr.insert(4);
		arr.insert(7);
	
		arr.display();
	
		System.out.println(arr.getMax());
		
		while (!arr.isEmpty()) {
			arr.delete(arr.getMax());
		}
	
		arr.display();
		
		System.out.println(arr.getMax());
	}
}