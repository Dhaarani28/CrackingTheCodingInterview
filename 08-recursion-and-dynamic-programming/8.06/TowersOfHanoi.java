import java.util.Stack;

class TowersOfHanoi {

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

	private static void hanoi(int n, Stack<Integer> from, Stack<Integer> tmp, Stack<Integer> to) {
		if (n == 1) {
			move(from, to);
			return;
		}
		hanoi(n-1, from, to, tmp);  // Move disks on top of largest disk to stack 1
		move(from, to);             // Move largest disk to stack 2
		hanoi(n-1, tmp, from, to);  // Move disks on stack 1 to stack 2
}

	private static void move(Stack<Integer> from, Stack<Integer> to) {
		to.push(from.pop());
	}

	
}

