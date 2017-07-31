package chapter11;

public class Project11_1 {
	public static void main(String[] args) {

	}
}

class DataItem {
	private int data;

	public DataItem(int data) {
		this.data = data;
	}
	
	public int getKey() {
		return data;
	}
}

class HashTable {
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem; // for deleted items
	
	public HashTable(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1); // deleted items have key of -1
	}
	
	public int hashFunction(int key) {
		return key % arraySize;
	}
	
	public void insert(DataItem item) {
		// assumes table isn't full
		int key = item.getKey();
		int hashValue = hashFunction(key);
		
		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1) {
			++hashValue;
			hashValue %= arraySize;
		}
		hashArray[hashValue] = item;
	}
	
	private DataItem find(int key) {
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
		System.out.println("Table: ");
		
		for (int i = 0; i < arraySize; i++) {
			if (hashArray[i] != null) {
				System.out.println(hashArray[i].getKey() + " ");
			} 
			else {
				System.out.println("** ");
			}
		}
		System.out.println();
	}
}