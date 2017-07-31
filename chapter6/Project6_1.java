/* Directions:
 * Suppose you buy a budget-priced pocket PC and discover that the chip inside 
 * can’t do multiplication, only addition. You program your way out of this 
 * quandary by writing a recursive method, mult(), that performs multiplication 
 * of x and y by adding x to itself y times. Its arguments are x and y and its 
 * return value is the product of x and y. Write such a method and a main() 
 * program to call it. Does the addition take place when the method calls 
 * itself or when it returns?
 * 
 * ANSWER: When returning
 */

package chapter6;

public class Project6_1 {
	public static void main(String[] args) {
		int x = 0;
		int y = 1;
		int z = 2;
		
		for (int i = 0; i < 5; i++) {
			System.out.println(x + " * " + i + " = " + mult(x, i));
			System.out.println(y + " * " + i + " = " + mult(y, i));
			System.out.println(z + " * " + i + " = " + mult(z, i));
			System.out.println();
		}
	}
	
	public static int mult(int x, int y) {
		if (y == 0) {
			return 0;
		}
		else if (y == 1) {
			return x;
		}
		else {
			return x + mult(x, y - 1);
		}
	}
}