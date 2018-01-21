import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

/*
 * Usage: java PowerSet 1 2 3
 */

class PowerSet {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
        for (String a : args) set.add(Integer.parseInt(a));

		List<Set<Integer>> subsets = getSubsets(set);

		for (Set<Integer> s : subsets) System.out.println(s);
	}

	private static List<Set<Integer>> getSubsets(Set<Integer> set) {
        // Base case
		if (set.isEmpty()) {
			List<Set<Integer>> list = new ArrayList<>();
			list.add(new HashSet<>());
			return list;
		}
        // Remove one element from the set
		Iterator<Integer> it = set.iterator();
		int element = it.next();
		set.remove(element);
        // Calculate the subset of the remaining elements in the set
		List<Set<Integer>> list = getSubsets(set);
        // Create a new list of sets with the returned subsets plus the element
		List<Set<Integer>> newList = new ArrayList<>();
		for (Set<Integer> s : list) {
			Set<Integer> newSet = new HashSet<>(s);
			newSet.add(element);
			newList.add(newSet);
		}
        // Merge the two lists of sets and return
		list.addAll(newList);
		return list;
	}
}

