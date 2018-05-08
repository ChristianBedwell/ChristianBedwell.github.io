// Test class for DynamicList
public class DynamicTest {

	public static void main(String[] args) {
		DynamicList list1 = new DynamicList(); // you need to create more than one list
		DynamicList list2 = new DynamicList();
		DynamicList list3 = new DynamicList();
		DynamicList list4 = new DynamicList();
		
		list1.insertFirst(2);
		list1.insertFirst(1);
		list3.insertFirst(4);;
		list3.insertFirst(5);;

		System.out.print("List: ");
		list1.printList();

		System.out.println("\n  Search insert with 1...");
		list1.searchInsert(1);
		System.out.print("List: ");
		list1.printList();

		System.out.println("\n  Search insert with 7...");
		list1.searchInsert(7);
		System.out.print("List: ");
		list1.printList();

		System.out.println("\n");
		System.out.println("Search insert Empty list with 1...");
		list2.searchInsert(1);
		System.out.print("List: ");
		list2.printList();
		
		System.out.println("\n");
		System.out.println("Lists: ");
		list1.printList();
		System.out.println(" ");
		list3.printList();
		System.out.println("\n  Add all elements...");
		list1.addAllElements(list3);
		System.out.print("List: ");
		list1.printList();
		
		System.out.println("\n");
		System.out.print("List: ");
		list1.printList();
		System.out.println("\n  Reverse list...");
		list1.reverse();
		System.out.print("List: ");
		list1.printList();
		
		System.out.print("\n");
		System.out.println("\nReverse Empty list...");
		list4.reverse();
		System.out.print("List: ");
		list4.printList();
		
		System.out.print("\n");
		System.out.print("\nList: ");
		list1.printList();
		System.out.println("\n  Delete mid...");
		list1.deleteMid();
		System.out.print("List: ");
		list1.printList();
		
		System.out.print("\n");
		System.out.print("\nList: ");
		list1.printList();
		System.out.println("\n  Delete mid...");
		list1.deleteMid();
		System.out.print("\nList: ");
		list1.printList();
		
		System.out.print("\n");
		System.out.println("\nDelete mid in Empty List...");
		list4.deleteMid();
		System.out.print("\nList: ");
		list4.printList();		
	}
}
