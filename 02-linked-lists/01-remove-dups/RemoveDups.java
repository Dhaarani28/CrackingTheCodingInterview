import java.util.HashSet;
import static org.junit.Assert.assertArrayEquals;

public class RemoveDups {
    // Constraints
    //  - Input: pointer to head of linked list
    //  - Output: void (but linked list modified)
    //  - Type of linked list: singly-linked
    //  - Data of linked list: integers
    //  - Length of linked list: as much as fits in memory


    // Basic
    // Procedure
    //  - Initialise an empty set of integers
    //  - Initialise pointer 'runner' to head of link list
    //  - Initiliase pointer 'behind' to null
    //  - While runner is not null
    //      - Test if runner.data is in the set
    //          - If yes, set behind.next=runner.next, and runner=runner.next
    //          - If no, add runner.data to set, behind=runner, runner=runner.next
    // Complexity
    //  - Time
    //      - O(N): iterate one time through the linked list
    //  - Space
    //      - O(N): the set containing all unique values of the linked list
    private static void removeDups(Node head) {
        HashSet<Integer> set = new HashSet<>();
        Node runner = head;
        Node behind = null;
        while (runner != null) {
            if (set.contains(runner.data))
                behind.next = runner.next;
            else {
                set.add(runner.data);
                behind = runner;
            }
            runner = runner.next;
        }
    }

    // No extra memory
    // Idea
    //  - For each element in the list, iterate through all the elements after
    //    it, and delete those which have the same data as the currentelement
    //  - Wrap the while loop of above solution in another while loop
    // Procedure
    //  - Initialise a pointer 'current' to head of list
    //  - While 'current' is not null
    //      - Initialise a pointer 'runner' to 'current.next'
    //      - Initialise a pointer 'behind' to 'current'
    //      - While 'runner' is not null
    //          - Test if runner.data equals current.data
    //              - If yes, behind.next = runner.next
    //              - If no, behind = runner
    //          - Set runner = runner.next
    //      - Set current = current.next
    // Complexity
    //  - Time
    //      - N * N/2 = O(N^2)
    //          - For each element, iterate on avg. through half of the elements
    //  - Space
    //      - O(1): no extra memory needed
    private static void removeDups2(Node head) {
        Node current = head;
        while (current != null) {
            Node runner = current.next;
            Node behind = current;
            while (runner != null) {
                if (runner.data == current.data)
                    behind.next = runner.next;
                else
                    behind = runner;
                runner = runner.next;
            }
            current = current.next;
        }
    }

    // Test Cases
    public static class Test {
        public static void main(String[] args) {
            // Initial data in the linked list
            int[][] i = {
                null,
                {1},
                {1,1},
                {1,1,2},
                {1,2,1},
                {2,6,3,5,8,2,6,4,5,6,8}
            };
            // Expected data in the linked list after removing duplicates
            int[][] o = {
                null,
                {1},
                {1},
                {1,2},
                {1,2},
                {2,6,3,5,8,4}
            };
            // Test
            for (int k = 0; k < i.length; k++) {
                Node head = Util.buildList(i[k]);
                removeDups(head);
                assertArrayEquals(o[k], Util.getListData(head));
            }
        }
    }
}
