import java.util.Scanner;
import java.util.HashMap;

public class IsUnique {

    // Constraints:
    //  - Valid characters: 128 ASCII characters
    //  - Length of string: as much as fits into memory

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (isUniqueBF(sc.next())) System.out.println("Yes");
            else System.out.println("No");
        }
    }

    // Brute Force
    // Idea:
    //  - Read every character of the string, and for each character:
    //      - Compare it with all the characters to the right of it
    //      - If it equals one of them, return false
    // - After the loop, return true
    // Complexity:
    //  - Time
    //      - N-1 + N-2 + ... + 1 = N(N-1)/2 = O(N^2) (number of compares)
    //  - Space
    //      - O(1) (no additional space needed)
    private static boolean isUniqueBF(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = i+1; j < s.length(); j++)
                if (c == s.charAt(j)) return false;
        }
        return true;
    }

    // Hash Table
    // Idea:
    //  - Create a hash table of capacity 128
    //  - Read every character of the string, and for each character c:
    //      - Test if the hash table contains an entry with key 'c'
    //      - If yes, return false
    //      - If no, put new entry in hash table with key 'c'
    //  - After the loop, return true
    // Complexity:
    //  - Time
    //      - O(n) (at most n lookups in the hash table)
    //  - Space
    //      - O(1) (hash table has at most 128 elements)
    private static boolean isUnique(String s) {
        HashMap<Character, Object> ht = new HashMap<>(128, 1);
        char[] a = s.toCharArray();  // This provokes O(N) space complexity
        for (char c : a) {
            if (ht.containsKey(c)) return false;
            ht.put(c, null);
        }
        return true;
    }

    // Test cases:
    //  - Return true
    //      - Empty string, single letter, punctuation, ASCII characters,..
    //  - Return false
    //      - Duplicated chracters at end of word/beginning of word
    public static class Test {
        public static void main(String[] args) {
            String[] t = {"", "a", "abcd", "aA", "123.!$~0"};
            String[] f = {"aa", "aabcd", "abcdd"};
            for (String s : t) {
                assert isUnique(s) : s + " should be true";
                assert isUniqueBF(s) : s + " should be true";
            }
            for (String s : f) {
                assert !isUnique(s) : s + " should be false";
                assert !isUniqueBF(s) : s + " should be false";
            }
        }
    }
}
