package chapter11;

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

	// Modified for Project11_1
	public void insert(DataItem item) { // assumes table isn't full
		int key = item.getKey();
		int hashValue = hashFunction(key);
		int probeCount = 1;

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1) {
			hashValue += probeCount * probeCount;
			probeCount++;

			hashValue %= arraySize;
		}
		hashArray[hashValue] = item;
	}

	// Modified for Project11_1
	public DataItem find(int key) {
		int hashValue = hashFunction(key);
		int probeCount = 1;

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey() == key) {
				return hashArray[hashValue];
			}
			hashValue += probeCount * probeCount;
			probeCount++;

			hashValue %= arraySize;
		}
		return null;
	}

	// Modified for Project11_1
	public DataItem delete(int key) {
		int hashValue = hashFunction(key);
		int probeCount = 1;

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey() == key) {
				DataItem temp = hashArray[hashValue];
				hashArray[hashValue] = nonItem;
				return temp;
			}
			hashValue += probeCount * probeCount;
			probeCount++;

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
	}
}