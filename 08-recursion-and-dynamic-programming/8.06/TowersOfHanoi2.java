import java.util.Stack;

/* Just a refactoring of TowersOfHanoi.java */

class TowersOfHanoi2 {

	private static Stack<Integer> stack0 = new Stack<>();
	private static Stack<Integer> stack1 = new Stack<>();
    private static Stack<Integer> stack2 = new Stack<>();

	public static void main(String[] args) {
        // Create initial stacks
		int n = Integer.parseInt(args[0]);
		for (int i = n; i >= 1; i--) stack0.push(i);

        // Move all disks from stack 0 to stack 2
		hanoi(n, stack0, stack1, stack2);

        // Print out final state of stacks
		System.out.println("Stack 0: " + stack0);
		System.out.println("Stack 1: " + stack1);
		System.out.println("Stack 2: " + stack2);
	}

	private static void hanoi(int n, Stack<Integer> start, Stack<Integer> tmp, Stack<Integer> dest) {
		if (n <= 0) return;              // Base case, no disks to move
		hanoi(n-1, start, dest, tmp);    // Move disks on top of largest disks to "tmp"
        dest.push(start.pop());          // Move largest disk from to "dest"
		hanoi(n-1, tmp, start, dest);    // Move disks on "tmp" to "dest"
    }
	
}

