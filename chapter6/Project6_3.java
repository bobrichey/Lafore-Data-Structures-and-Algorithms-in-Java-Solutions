/* Directions:
 * Implement the recursive approach to raising a number to a power, as 
 * described in the “Raising a Number to a Power” section near the end of this 
 * chapter. Write the recursive power() function and a main() routine to test 
 * it.
 */

package chapter6;

public class Project6_3 {
	public static void main(String[] args) {
		int x = 1;
		int y = 2;
		int z = 3;
		
		for (int i = 0; i < 5; i++) {
			System.out.println(x + "^" + i + " = " + power(x, i));
			System.out.println(y + "^" + i + " = " + power(y, i));
			System.out.println(z + "^" + i + " = " + power(z, i));
			System.out.println();
		}
	}
	
	public static int power(int x, int y) {
		if (y == 0) {
			return 1;
		}
		else if (y == 1) {
			return x;
		}
		else if (y % 2 == 1) {
			return x * power(x*x, y/2);
		}
		else {
			return power(x*x, y/2);
		}
	}
}