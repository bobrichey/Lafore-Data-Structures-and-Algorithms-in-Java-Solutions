/* Directions:
 * Write a hash function to implement a digit-folding approach in the hash 
 * function (as described in the “Hash Functions” section of this chapter). 
 * Your program should work for any array size and any key length. Use linear 
 * probing. Accessing a group of digits in a number may be easier than you 
 * think. Does it matter if the array size is not a multiple of 10?
 */

package chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Project11_3 {
	public static void main(String[] args) throws IOException {
		DataItem aDataItem;
		int aKey, size, n, keysPerCell;

		System.out.print("Enter size of hash table: ");
		size = getInt();

		System.out.print("Enter initial number of items: ");
		n = getInt();
		System.out.println();

		keysPerCell = 10;

		// Make table
		HashTable2 theHashTable = new HashTable2(size);

		// Insert data
		for (int i = 0; i < n; i++) {
			aKey = (int) (Math.random() * keysPerCell * size);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aDataItem);
		}

		// Interact with user
		while (true) {
			System.out.print("Enter the first letter of show, insert, delete, or find: ");
			char choice = getChar();

			switch (choice) {
			case 's':
				theHashTable.display();
				break;
			case 'i':
				System.out.print("Enter key value to insert: ");
				aKey = getInt();
				aDataItem = new DataItem(aKey);
				theHashTable.insert(aDataItem);
				break;
			case 'd':
				System.out.print("Enter key value to delete: ");
				aKey = getInt();
				theHashTable.delete(aKey);
				break;
			case 'f':
				System.out.print("Enter key value to find: ");
				aKey = getInt();
				aDataItem = theHashTable.find(aKey);
				if (aDataItem != null) {
					System.out.println("Found " + aKey);
				} else
					System.out.println("Could not find " + aKey);
				break;
			default:
				System.out.print("Invalid entry\n");
			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}

class HashTable2 {
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem; // for deleted items
	private int foldNumber; // for hashing via digit-folding

	public HashTable2(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1); // deleted items have key of -1
		setFoldNumber(arraySize);
	}

	// Added for Project11_3
	// Entries will be broken into groups of 1 for arrays less than 100
	public void setFoldNumber(int arraySize) {
		foldNumber = 10;

		while (arraySize >= 100) {
			arraySize /= 10;
			foldNumber *= 10;
		}
	}

	// Modified for Project11_3
	public int hashFunction(int key) {
		int foldedKey = 0;

		while (key > 0) {
			foldedKey += key % foldNumber;
			key /= foldNumber;
		}
		return foldedKey % arraySize;
	}

	public void insert(DataItem item) { // assumes table isn't full
		int key = item.getKey();
		int hashValue = hashFunction(key);

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1) {
			++hashValue;
			hashValue %= arraySize;
		}
		hashArray[hashValue] = item;
	}

	public DataItem find(int key) {
		int hashValue = hashFunction(key);

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey() == key) {
				return hashArray[hashValue];
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	public DataItem delete(int key) {
		int hashValue = hashFunction(key);

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey() == key) {
				DataItem temp = hashArray[hashValue];
				hashArray[hashValue] = nonItem;
				return temp;
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	public void display() {
		System.out.print("Table: ");

		for (int i = 0; i < arraySize; i++) {
			if (hashArray[i] != null) {
				System.out.print(hashArray[i].getKey() + " ");
			} else {
				System.out.print("** ");
			}
		}
		System.out.println();
		System.out.println();
	}
}