public class URLify {

    // Constraints
    // Length of string: as much as fits in memory
    // Character set of string: ASCII
    // Input 1: character array including extra space
    // Input 2: length k of the string without the extra space
    // Extra space: located as empty array elements at the end of the array
    // Length of extra space: 2 times the number of 'space' chars in the array


    // In-place
    // Idea:
    //  - Initialise a pointer i to index k-1 of the array (last char of string)
    //  - Initialise a pointer j to the last element of the array
    //  - Decrement pointer i from k-1 to 0, and at each step:
    //      - If a[i] is a space, assign a[j] = 0, a[j-1] = 2, a[j-2] = %, and
    //        decrement j by 3
    //      - Else, write the char at a[i] to a[j], and decrement j by 1
    // Complexity:
    //  - Time
    //      - ~2N = O(N) (two pointers running throug almost entire string)
    //  - Space
    //      - O(1) (no extra space needed)
    private static String urlify(String s, int k) {
        char[] a = s.toCharArray();
        int j = a.length - 1;
        for (int i = k-1; i >= 0; i--) {
            if (a[i] == ' ') {
                a[j] = '0';
                a[j-1] = '2';
                a[j-2] = '%';
                j -= 3;
            }
            else {
                a[j] = a[i];
                j--;
            }
        }
        return new String(a);
    }


    // Test Cases
    //  - ("", 0)                   = > ""
    //  - ("Hello", 5)              = > "Hello"
    //  - ("Hello World  ", 11)     = > "Hello%20World"
    //  - ("Mr John Smith    ", 13) = > "Mr%20John%20Smith"
    //  - ("   ", 1)                = > "%20"
    //  - ("foo  bar    ", 8)       = > "foo%20%20bar"
    //  - (" hi     ", 4),          = > "%20hi%20"
    public static class Test {
        public static void main(String[] args) {
            String[] s = {"", "Hello", "Hello World  ", "Mr John Smith    ",
                          "   ", "foo  bar    ", " hi     "};
            int[] k = {0, 5, 11, 13, 1, 8, 4};
            String[] out = {"", "Hello", "Hello%20World", "Mr%20John%20Smith",
                            "%20", "foo%20%20bar", "%20hi%20"};
            for (int i = 0; i < s.length; i++) {
                String r = urlify(s[i], k[i]);
                assert r.equals(out[i]) : "Should be \"" + out[i] + "\", but is \"" + r + "\"";
            }
        }
    }
}
