/* Directions:
 * Let’s try something a little different: a two-dimensional linked list, which
 * we’ll call a matrix. This is the list analogue of a two-dimensional array. 
 * It might be useful in applications such as spreadsheet programs. If a 
 * spreadsheet is based on an array, and you insert a new row near the top, you 
 * must move every cell in the lower rows N*M cells, which is potentially a 
 * slow process. If the spread- sheet is implemented by a matrix, you need only 
 * change N pointers.
 * 
 * For simplicity, we’ll assume a singly linked approach (although a double-
 * linked approach would probably be more appropriate for a spreadsheet). Each
 * link (except those on the top row and left side) is pointed to by the link 
 * directly above it and by the link on its left. You can start at the upper-
 * left link and navigate to, say, the link on the third row and fifth column 
 * by following the pointers down two rows and right four columns. Assume your 
 * matrix is created with specified dimensions (7 by 10, for example). You 
 * should be able to insert values in specified links and display the contents 
 * of the matrix.
 * 
 * NOTE: added methods for finding and deleting specified values, getting the 
 * value at a specific index, and deleting rows or columns by index number
 */

package chapter5;

public class Project5_6 {
	public static void main(String[] args) {
		Matrix m = new Matrix(3, 5);
		m.display();
		
		int x = 5;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				m.insert(i, j, x += 5);
			}
		}
		m.display();
		
		m.remove(75);
		m.removeAt(0, 0);
		m.display();
		
		System.out.println(m.find(20).data);
		System.out.println(m.remove(80).data);
		m.display();
		
		m.deleteRow(2);
		m.display();
		
		m.deleteColumn(2);
		m.display();
	}
}

class Matrix {
	private MatrixLink row;
	private MatrixLink column;
	private int n;
	private int m;
	private MatrixLink handle;
	MatrixLink current;
	
	public Matrix(int n, int m) {
		this.n = n;
		this.m = m;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				MatrixLink newLink = new MatrixLink(null);
				
				if (isEmpty()) {
					row = newLink;
					column = newLink;
					current = newLink;
					handle = newLink;
				}
				else {
					if (j == 0 && i > 1) {
						current = current.down;
					}
					if (j == 0) {
						column = current.down;
						row = current;
						column = newLink;
					}
					else {
						column.right = newLink;
						column = newLink;
					}
					if (i > 0) {
						row.down = newLink;
						row = row.right;
					}
				}
			}
		}
	}
	
	public void insert(int n, int m, int value) {
		current = handle;
		
		for (int i = 0; i < n; i++) {
			current = current.down;
		}
		for (int i = 0; i < m; i++) {
			current = current.right;
		}
		current.data = value;
	}
	
	public void removeAt(int n, int m) {
		current = handle;
		
		for (int i = 0; i < n; i++) {
			current = current.down;
		}
		for (int i = 0; i < m; i++) {
			current = current.right;
		}
		current.data = null;
	}
	
	public MatrixLink remove(int value) {
		current = find(value);
		
		if (current.data == null) {
			return current;
		} 
		else {
			MatrixLink temp = new MatrixLink(current.data);
			current.data = null;
			return temp;
		}
	}
	
	public MatrixLink find(int value) {
		current = handle;
		MatrixLink first = handle;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (current.data != null && current.data == value) {
					return current;
				}
				current = current.right;
			}
			first = first.down;
			current = first;
		}
		return new MatrixLink(null);
	}
	
	public void deleteRow(int index) {
		if (index == 0) {
//			MatrixLink temp = handle;
			handle = handle.down;
			
//			while (temp != null) {
//				MatrixLink temp2 = temp;
//				temp = temp.right;
//				temp2 = null;
//			}
		} 
		else {
			current = handle;
			
			for (int i = 0; i < index - 1; i++) {
				current = current.down;
			}
			while (current != null) {
				current.down = current.down.down;
				current = current.right;
			}
		}
	}
	
	public void deleteColumn(int index) {
		if (index == 0) {
			handle = handle.right;
			
//			while (temp != null) {
//				MatrixLink temp2 = temp;
//				temp = temp.right;
//				temp2 = null;
//			}
		} 
		else {
			current = handle;
			
			for (int i = 0; i < index - 1; i++) {
				current = current.right;
			}
			while (current != null) {
				current.right = current.right.right;
				current = current.down;
			}
		}
	}
	
	private boolean isEmpty() {
		return handle == null;
	}
	
	public void display() {
		MatrixLink n = handle;
		MatrixLink m = handle;
		
		while (n != null) {
			while (m != null) {
				if (m.data == null) {
					System.out.print("--  ");
				} 
				else {
					System.out.print(m.data + "  ");
				}
				m = m.right;
			}
			System.out.println();
			n = n.down;
			m = n;
		}
		System.out.println();
	}
}

class MatrixLink {
	Integer data;
	MatrixLink down;
	MatrixLink right;
	
	public MatrixLink(Integer data) {
		this.data = data;
		down = null;
		right = null;
	}
}
