import static org.junit.Assert.assertArrayEquals;

public class DeleteMiddleNode {
    // Constraints
    //  - Input: reference to a node in the linked list
    //      - Not the first node, and not the last node
    //  - Output: void (but linked list modified)
    //  - Type of linked list: singly-linked
    //  - Length of linked list: 3 or more
    //  - Data of linked list: integers


    // Idea
    //  - Move data of node.next to node
    //  - Set node.next = node.next.next
    // Complexity
    //  - Time
    //      - O(1): just two operations
    //  - Space
    //      - O(1): no additional space needed
    private static void deleteNode(Node node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    // Test Cases
    public static class Test {
        public static void main(String[] args) {
            // Data in the initial linked lists
            int[][] inData = {
                {1,2,3},
                {1,2,3,4,5,6,7},
                {1,2,3,4,5,6,7}
            };
            // Index of the node to delete (starting at 0)
            int[] index = {1, 1, 5};
            // Expected data in the output linked lists
            int[][] outData = {
                {1,3},
                {1,3,4,5,6,7},
                {1,2,3,4,5,7}
            };
            // Test
            for (int i = 0; i < inData.length; i++) {
                Node head = Util.buildList(inData[i]);
                deleteNode(Util.getNodeAt(head, index[i]));
                assertArrayEquals(outData[i], Util.getListData(head));
            }
        }
    }
}
