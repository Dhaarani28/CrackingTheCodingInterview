import java.util.ArrayList;

// Static helper methods for linked lists
public class Util {

    // Create a singly-linked integer list based on the supplied integer data
    public static Node buildList(int[] data) {
        if (data == null || data.length == 0) return null;
        Node head = new Node(data[0]);
        Node current = head;
        for (int i = 1; i < data.length; i++) {
            Node newNode = new Node(data[i]);
            current.next = newNode;
            current = newNode;
        }
        return head;
    }

    // Get the data of an integer linked list as an integer array
    public static int[] getListData(Node head) {
        if (head == null) return null;
        ArrayList<Integer> a = new ArrayList<>();
        while (head != null) {
            a.add(head.data);
            head = head.next;
        }
        return a.stream().mapToInt(i->i).toArray();
    }
    
    // Print the data of a linked list
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Get the node at a specific index from a linked list (starting at 0)
    public static Node getNodeAt(Node head, int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) throw new IndexOutOfBoundsException(""+index);
        }
        return current;
    }

}
