
public class hw2 {	
	  
	// Parameters
	//    1) heap: the array where the heap (sweeping window) is implemented
	//    2) newEle: the new element to insert
	//    3) pos: where to insert the new element initially.
	//            note it does not mean newEle is going to 
	//            stay at pos after this function
	//    4) increment
	//    	a) true: insert newEle, that is all
	//    	b) false: insert newEle, then remove the root
	static void insertAt(int [] heap, int newEle, 
					int pos, boolean increment) {
		if(heap.length == 0) {
			return;
		}
		else {
			// if true, insert new element (create original heap)
			if(increment == true) {
				heap[pos] = newEle;
				bubbleUp(heap, pos);
			}
			// if false, insert new element and remove the root (create resulting heap)
			else {
				heap[pos] = newEle;
				bubbleUp(heap, pos);
				bubbleDown(heap, pos);		
			}
		}		
	}
	
	// add an element to the heap 
	private static void bubbleUp(int[] heap, int pos) {
		int elt = heap[pos];		
		int child = pos;
		int parent = (child - 1)/2;
		while(child > 0 && heap[parent] < elt){
			heap[child] = heap[parent];
			child = parent;
			parent = (child - 1)/2;
		}
		heap[child] = elt;
	}
	
	// delete the root from the heap and restore its properties
	public static void bubbleDown(int[] heap, int pos) {
		int iValue = heap[pos];
		heap[pos] = heap[0];
		int parent = 0;
		int child;
		
		if(pos == 1) {
			child = -1;
		}
		else {
			child = 1;
		}
		if(pos > 2 && heap[2] > heap[1]) {
			child = 2;
		}
		while(child >= 0 && iValue < heap[child]) {
			heap[parent] = heap[child];
			parent = child;
			child = 2*parent + 1;
			
			if(child + 1 <= pos - 1 && heap[child] < heap[child + 1]) {
				child = child + 1;
			}
			if(child > pos - 1) {
				child = -1;
			}
		}
		heap[parent] = iValue;
	}
	
	// Get the smallest k elements in array x in O(nlogk) time, where
	// n is the size of array x.
	// It is supposed to return an array, containing the smallest k elements
	// in the increasing order.
	static int [] getSmallestK(int x[], int k) {
		
		if(k > x.length) 
			return null;
		
		int [] result = new int[k + 1];
		
		// use the first k elements in x to construct an 
		// almost complete binary tree, where parent >= children
		result[0] = x[0];
		for(int i = 1; i < k; i++) {
			insertAt(result, x[i], i, true);
		}
		
		System.out.print("Original heap: ");
		for(int i = 0; i < k; i++) {
			System.out.print(result[i]+ " ");
		}
		System.out.println();

		// for each element in the rest of array x, 
		// insert it in the almost complete binary tree, and then
		// remove the root in the tree. 
		for(int i = k; i < x.length; i++) {
			insertAt(result, x[i], k, false);
		}
		
		// now the first k elements in result are the smallest k elements in x
		System.out.print("Resulting heap: "); 
		for(int i = 0; i < k; i++) {
			System.out.print(result[i]+ " ");
		}
		System.out.println();
		
		// sort the first k elements in result in O(klogk) time
		for(int i = k - 1; i > 0; i--) {
			heapSort(result, i);	
		}			
		
		return result;		
	}
	
	// sort the resulting heap in ascending order
	public static void heapSort(int heap [], int pos) {
		int larger;
		int child;
		int parent = 0;
		
		// switch the last element of the tree with the root
		int temp = heap[pos];
		heap[pos] = heap[0];
		heap[0] = temp;
		
		if(pos - 1 == 0) {	// only one node
		 	return;   
		}
		
		if(pos - 1 == 1) {  // only two nodes: root and its child
		     larger = 1;  // the larger child is at position 1
		}
		else {
		     larger = (heap[1] > heap[2])? 1: 2;
		}
		
		// bubble down the element at the root to create another max-tree
		while(heap[parent] < heap[larger]) {
		      temp = heap[parent];       // swap x[parent]
		      heap[parent] = heap[larger];  // and its larger
		      heap[larger] = temp;       // child
		      parent = larger;	  // update parent
		 	  child = 2*parent + 1;
		      if(child > pos-1)	{	// no child
		    	  return; 
		      }
		      else if(child + 1 > pos - 1) {	// only left child
		    	  larger = child; 
		      }
		      else {	// two kids - pick largest
		    	  larger = (heap[child + 1] > heap[child]) ? child + 1: child;
		      }
		}		
	}
	
	public static void main(String [] args) {
		// Test cases		
		int [] data = {31, 44, 64, 5, 61,
						43, 6, 88, 59, 90,
						39, 97, 77, 62, 99,
						34, 57, 53, 60, 29};
		
		int i, k = 5;		
		System.out.println(" k = " + k);
		
		int [] largestK = getSmallestK(data, k);
		
		System.out.print("Sorted result (smallest k elements): "); 
		for(i = 0; i < k; i++) {
			System.out.print(largestK[i]+ " ");
		}
		
		k = 8;		
		System.out.println("\n k = " + k);
		
		largestK = getSmallestK(data, k);
		
		System.out.print("Sorted result (smallest k elements): "); 
		for(i = 0; i < k; i++) {
			System.out.print(largestK[i]+ " ");
		}
	}
}