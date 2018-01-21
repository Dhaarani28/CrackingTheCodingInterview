import java.util.HashMap;

public class CheckPermutation {

    // Constraints
    // Length of the strings: as much as fits in memory
    // Character set of strings: 128 ASCII characters


    // Hash Table
    // Idea:
    //  - If the length of the strings differ, return false
    //  - Create a hash table of size 128 (types: key=char, value=int)
    //  - Read each character of first string, and for each character c:
    //      - Check if key c exists in hash table
    //      - If yes, get its value, increment it by one, and replace the old value
    //      - If no, add an entry with key=c and value=1 to the hash map
    //  - Read each character of the second string, and for each character c:
    //      - Check if key c exists in the hash table
    //      - If no, return false
    //      - If yes, get its value
    //          - If the value is 1, delete the entry
    //          - If the value is >1, decrement it by one, and replace the old value
    // - At the end of iterating through the second string, return true
    // Complexity:
    //  - Time
    //      - N+N = 2N = O(N) (number of hash table accesses, N = length of strings)
    //  - Space
    //      - O(1) (at most 128 elements in hash table)
    private static boolean isPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        HashMap<Character, Integer> ht = new HashMap<>();
        // Read first string
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (ht.containsKey(c)) {
                int val = ht.get(c);
                ht.replace(c, ++val);
            }
            else ht.put(c, 1);
        }
        // Read second string
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if (!ht.containsKey(c)) return false;
            int val = ht.get(c);
            if (val == 1) ht.remove(c);
            else ht.replace(c, --val);
        }
        return true;
    }

    // Sorting
    // Idea:
    //  - If the length of the strings differ, return false
    //  - Sort strings with an in-place worst-case N log N sort (e.g. heapsort)
    //  - Test if the strings are equal
    // Complexity:
    //  - Time
    //      - 2(N log N) + N = O(N log N)
    //      - 2(N log N) for sorting the two strings, and N for comparing them
    //  - Space
    //      - O(1) (no additional space needed if using an in-place sort and if
    //        we can sort the input strings directly)

    // Multiset
    // Idea:
    //  - If the length of the strings differ, return false
    //  - Read each character of first string, and add it to a multiset
    //  - Read each character of second string, and for each character c:
    //      - Test if it exists in the multiset
    //      - If no, return false
    //      - If yes, remove it from the multiset
    // - After reading all characters of the second string, return true
    // Complexity:
    //  - Time
    //      - N + N = O(N)
    //  - Space
    //      - N = O(N) (number of characters in multiset)


    // Test Cases
    //  - Return true
    //      - ("", ""), ("a", "a"), ("ab", "ab"), ("ab", "ba"), ("anagram", "nagaram")
    //  - Return false
    //      - ("", "a"), ("a", "b"), ("abb", "aba")
    public static class Test {
        public static void main(String[] args) {
            // Pairs that must return true
            String[] t1 = {"", "a", "ab", "ab", "anagram"};
            String[] t2 = {"", "a", "ab", "ba", "nagaram"};
            // Pairs that must return false
            String[] f1 = {"", "a", "abb", "anagram"};
            String[] f2 = {"a", "b", "aba", "nagaraa"};
            // Test
            for (int i = 0; i < t1.length; i++)
                assert isPermutation(t1[i], t2[i]) : msgT(t1[i], t2[i]);
            for (int i = 0; i < f1.length; i++)
                assert !isPermutation(f1[i], f2[i]) : msgF(f1[i], f2[i]);
        }
        // Helper methods
        private static String msgT(String s1, String s2) {
            return "Is a permutation: \"" + s1 + "\" and \"" + s2 + "\"";
        }
        private static String msgF(String s1, String s2) {
            return "Is not a permutation: \"" + s1 + "\" and \"" + s2 + "\"";
        }
    }
}
