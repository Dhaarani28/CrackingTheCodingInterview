import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class OneAway {

    // Constraints
    //  - Input: two strings
    //  - Output: boolean
    //  - Length of strings: as much as fits in memory
    //  - Character set of strings: ASCII

    // Idea
    //  - Case 1: length of strings differs by 2 or more
    //      - Return false
    //  - Case 2: length of strings differs by 1
    //      - Check if all chars of shorter string are contained in longer string
    //  - Case 3: length of strings is equal
    //      - Check if at most 1 char of string 1 is not contained in string 2
    // Procedure
    //  - Check length difference of strings:
    //      - If > 1: return false
    //      - If 1
    //          - Initialise an integer array a of size 128 with 0s
    //          - For each character c of LONGER string
    //              - Increment corresponding array element a[c] by one
    //          - For each character c of SHORTER string
    //              - Check if corresponding array element a[c] is 0
    //                  - If yes, return false
    //                  - If no, decrement it by one
    //          - Return true
    //      - If 0
    //          - Initialise an integer array a of size 128 with 0s
    //          - For each character c of first string
    //              - Increment corresponding array element a[c] by one
    //          - Initialise a boolean 'replace' to false
    //          - For each character c of second string
    //              - Check if corresponding array element a[c] is 0
    //                  - If yes
    //                      - If 'replace' is false, set it to true
    //                      - If 'replace' is true, return false
    //                  - If no, decrement it by one
    //          - Return true
    // Complexity
    //  - Time
    //      - N + M = O(N+M) (N and M are the lengths of the two strings)
    //  - Space
    //      - 128 = O(1) (one array of length 128)
    private static boolean isOneAway(String s1, String s2) {
        int diff = Math.abs(s1.length() - s2.length());
        // Case 1
        if (diff > 1) return false;
        // Case 2
        if (diff == 1) {
            String longer = s1.length() > s2.length() ? s1 : s2;
            String shorter = longer == s1 ? s2 : s1;
            int[] a = buildTable(longer);
            for (int i = 0; i < shorter.length(); i++) {
                char c = shorter.charAt(i);
                if (a[c] <= 0) return false;
                else a[c]--;
            }
            return true;
        }
        // Case 3
        else {
            int[] a = buildTable(s1);
            boolean seenReplace = false;
            for (int i = 0; i < s2.length(); i++) {
                char c = s2.charAt(i);
                if (a[c] <= 0)
                    if (seenReplace) return false;
                    else seenReplace = true;
                else
                    a[c]--;
            }
        }
        return true;
    }

    // Build and return a character frequency table of the supplied string
    private static int[] buildTable(String s) {
        int[] a = new int[128];
        for (int i = 0; i < s.length(); i++)
            a[s.charAt(i)]++;
        return a;
    }


    // Test Cases
    //  - Return true:
    //      - ("", "a"), ("a", ""), ("pale", "ple"), ("", ""), ("a", "a")
    //        ("a", "b"), ("pale", "bale")
    //  - Return false:
    //      - ("", "ab"), ("abc", "aacd"), ("pale", "bake")
    public static class Test {
        public static void main(String[] args) {
            // Pairs that are at most "one away"
            String[] t1 = {"", "a", "pale", "", "a", "a", "pale"};
            String[] t2 = {"a", "", "ple", "", "a", "b", "bale"};
            // Pairs that are more then "one away"
            String[] f1 = {"", "abc", "pale"};
            String[] f2 = {"ab", "aacd", "bake"};
 
            for (int i = 0; i < t1.length; i++) {
                assertTrue("(\""+t1[i]+"\", "+t2[i]+"\") should be true", isOneAway(t1[i], t2[i]));
            }
            for (int i = 0; i < f1.length; i++) {
                assertFalse("(\""+f1[i]+"\", "+f2[i]+"\") should be false", isOneAway(f1[i], f2[i]));
            }
        }
    }
}
