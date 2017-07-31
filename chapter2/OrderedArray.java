package chapter2;

class OrderedArray {
	public int[] array;
	private int numberOfElements;
	
	public OrderedArray(int max) {
		array = new int[max];
		numberOfElements = 0;
	}
	
	public int getSize() {
		return numberOfElements;
	}
	
	public int find(int key) {
		int low = 0;
		int high = getSize() - 1;
		
		while (low <= high) {
			int mid = (low + high) / 2;
			
			if (array[mid] == key) {
				return mid;
			}
			else if (key < array[mid]) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		return -1;
	}
	
	// an alternative approach to binary search
	public int findInsertPoint(int value) {
		int low = 0;
		int high = numberOfElements - 1; 
		
		while (true) {
			int current = (low + high) / 2; 
			
			if (array[current] == value) {
				return current; 
			}
			else if (array[current] < value) {
				low = current + 1; 
				if (low > high) {
					return current + 1; 
				}
			}
			else {
				high = current - 1; 
				if (low > high) {
					return current; 
				}
			}
		} 
	}
	
	public void insert(int value) {
		int insertPoint = findInsertPoint(value);
		
		if (getSize() == 0) {
			array[0] = value;
		}
		else {
			for(int i = numberOfElements; i > insertPoint; i--) {
				array[i] = array[i - 1];
			}
			array[insertPoint] = value;	
		}
		numberOfElements++;
	}
	
	public boolean delete(int value) {
		int deleteIndex = find(value);
		if (deleteIndex == -1) {
			return false;
		}
		else {
			for (int i = deleteIndex; i < numberOfElements - 1; i++) {
				array[i] = array[i + 1];
			}
			numberOfElements--;
			return true;
		}
	}
	
	// Added for Project2_5
	public static int[] merge(OrderedArray arr1, OrderedArray arr2) {
		int[] result = new int[arr1.getSize() + arr2.getSize()];
		
		int[] a1 = arr1.getArray();
		int[] a2 = arr2.getArray();
		
		int a1Index, a2Index, resultIndex;
		a1Index = a2Index = resultIndex = 0;
		
		while (a1Index < a1.length && a2Index < a2.length) {
			if (a1[a1Index] < a2[a2Index]) {
				result[resultIndex++] = a1[a1Index++];
			}
			else {
				result[resultIndex++] = a2[a2Index++];
			}
		}
		if (a1Index < a1.length) {
			while (a1Index < a1.length) {
				result[resultIndex++] = a1[a1Index++];
			}
		}
		else {
			while (a2Index < a2.length) {
				result[resultIndex++] = a2[a2Index++];
			}
		}
		return result;
	}
	
	public int[] getArray() {
		return array;
	}
	
	public boolean isEmpty() {
		return getSize() == 0;
	}
	
	public void display() {
		if (getSize() == 0) {
			System.out.println("--");
		}
		else {
			for (int i = 0; i < numberOfElements; i++) {
				System.out.print(array[i] + " ");
			}
			System.out.println();
		} 
	}
}
