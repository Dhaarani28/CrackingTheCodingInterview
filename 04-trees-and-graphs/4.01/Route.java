import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Route {
	public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1));
        graph.add(Arrays.asList(2));
        graph.add(Arrays.asList(0, 3));
        graph.add(Arrays.asList(2));
        graph.add(Arrays.asList(6));
        graph.add(Arrays.asList(4));
        graph.add(Arrays.asList(5));

        int source = Integer.parseInt(args[0]);
        int target = Integer.parseInt(args[1]);
	
		if (hasPath(graph, source, target))
			System.out.println("yes");
		else
			System.out.println("no");
	}
	
	private static boolean hasPath(List<List<Integer>> graph, int source, int target) {
        if (source == target) return true;
		boolean[] marked = new boolean[graph.size()];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		while (!queue.isEmpty()) {
			int current = queue.remove();
			marked[current] = true;
			for (int v : graph.get(current)) {
				if (v == target) return true;
				if (!marked[v]) queue.add(v);
			}
		}
		return false;
	}
}

