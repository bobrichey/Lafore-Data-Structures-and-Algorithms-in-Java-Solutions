package chapter2;

class HighArray {
	private int[] array;
	private int numberOfElements;
	
	public HighArray(int max) {
		array = new int[max];
		numberOfElements = 0;
	}
	
	public int linearSearch(int key) {
		for (int i = 0; i < numberOfElements; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	public void insert(int value) {
		array[numberOfElements++] = value;
	}
	
	public boolean delete(int value) {
		int index = linearSearch(value);
		
		if (index == -1) {
			return false;
		}
		else {
			for (int i = index; i < numberOfElements - 1; i++) {
				array[i] = array[i + 1];
			}
			numberOfElements--;
			return true;
		}
	}
	
	public boolean isEmpty() {
		return numberOfElements == 0;
	} 
	
	//Added for Project2_1
	public int getMax() {
		if (isEmpty()) {
			return -1;
		}
		else {
			int max = array[0];
		
			for (int i = 1; i < numberOfElements; i++) {
				if (array[i] > max) {
					max = array[i];
				}
			}
			return max;	
		} 
	}
	
	// Added for Project2_2
	public int removeMax() {
		if (isEmpty()) {
			return -1;
		}
		else {
			int max = array[0];
		
			for (int i = 1; i < numberOfElements; i++) {
				if (array[i] > max) {
					max = array[i];
				}
			}
			delete(max);
			return max;	
		} 
	}
	
	// Added for Project2_6
	public void noDups() {
		int numberOfDups = 0;
			
		for (int i = 0; i < numberOfElements; i++) {
			for (int j = i + 1; j < numberOfElements; j++) {
				if (array[i] == Integer.MAX_VALUE) {
					continue;
				}
				if (array[i] == array[j]) {
					array[j] = Integer.MAX_VALUE;
					numberOfDups++;
				}
			}
		}
		for (int i = 0; i < numberOfDups; i++) {
			delete(Integer.MAX_VALUE);
		}
	}
	
	public void display() {
		if (isEmpty()) {
			System.out.println("--");
		}
		else {
			for(int i = 0; i < numberOfElements; i++) {
				System.out.print(array[i] + " ");
			}
			System.out.println();
		} 
	}
}