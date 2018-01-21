import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class PalindromePermutation {

    // Constraints
    // Length of string: as much as fits in memory
    // Character set: ASCII characters
    // Whitespace: all chars other than a-z and A-Z are ignored
    // Case sensitivity: case-insensitive (a = A)
    // Input: string
    // Output: boolean

    // Array
    // Idea
    //  - A string is a permutation of a palindrome if:
    //      - If its length is even: the char count of each char is even
    //      - If its length is odd: the char count of each but one char is even
    //  - In general, it's permutation of palindrome if the character count of
    //    0 or 1 characters is odd, and the rest is even
    // Procedure
    //  - Initialise an integer array a of size 26 with 0s
    //  - Read through the chars of the input string, and for each char c:
    //      - If it's anything else than a-z or A-Z, ignore it
    //      - If it's in A-Z, add 32 to it to make it lower case
    //      - Map the lower-case char c to an array index by k = c - 'a'
    //      - Increment the corresponding array element by 1: a[k]++
    //  - Initialse a boolean 'odd' to false
    //  - Iterate through all elements of array a, and for each element:
    //      - If it's even, do nothing
    //      - If it's odd:
    //          - If 'odd' is false, set it to true and continue
    //          - If 'odd' is true, return false
    //  - After iterating through array, return true
    // Complexity
    //  - Time
    //      - N + 26 = O(N)
    //          - Iterate through all input chars and through char count array
    //  - Space
    //      - 26 * 2 bytes = O(1) (array has fixed size of 26)
    private static boolean isPalindromePermutation(String s) {
        int[] a = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isLetterLC(c) && !isLetterUC(c)) continue;
            if (isLetterUC(c)) c += 32;  // Make it lower case
            a[c-'a']++;
        }
        boolean seenOdd = false;
        for (int i : a) {
            if (i % 2 != 0)
                if (seenOdd) return false;
                else  seenOdd = true;
        }
        return true;
    }

    // Bit vector
    // Procedure
    //  - Initialise an int with 32 bits, to be used as a bit vector, to 0
    //  - Read through the chars of the input string, and for each char c:
    //      - If it's anything else than a-z or A-Z, ignore it
    //      - If it's in A-Z, add 32 to it to make it lower case
    //      - Map the lower-case char c to a bit vector index k = c - 'a'
    //      - Set the bit vector field k to 0 if it was 1, and to 1 if it was 0
    //  - Test if bit vector has exactly 0 or 1 bits set to 1
    //      - If yes, return true
    //      - If no, return false
    // Complexity
    //  - Time
    //      - N + 1 = O(N)
    //          - Iterate through input string and set bit vector field
    //  - Space
    //      - 4 bytes = O(1) (we only need a 32-bit bit vector)
    private static boolean isPalindromePermutation2(String s) {
        int v = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isLetterLC(c) && !isLetterUC(c)) continue;
            if (isLetterUC(c)) c += 32;  // Make it lower case
            // Switch the bit at this letter's position
            int mask = 1 << c - 'a';
            v = v ^ mask;  // Bitwise XOR (00->0, 01->1, 10->1, 11->0)
        }
        // Test if only 0 or 1 bits set in bit vector
        // Example:
        // 1 bit set  2 bits set  0 bits set
        //   001000      010100     000000
        //   000111      010011     111111
        // & ------      ------     ------
        //   000000      010000     000000
        return (v & v-1) == 0;
    }



    private static boolean isLetterLC(char c) {
        return c >= 'a' && c <= 'z';
    }
    private static boolean isLetterUC(char c) {
        return c >= 'A' && c <= 'Z';
    }


    // Test Cases
    //  - Return true:
    //      - "", "a" " .", "aa", "Tact Coa", " Tact, Coa ", "An Na", "Annna"
    //  - Return false:
    //      - "abcd", "abbb"
    public static class Test {
        public static void main(String[] args) {
            String[] t = {"", "a", " .", "aa", "Tact Coa", " Tact, Coa ", "An Na", "Annna"};
            String[] f = {"abcd", "abbb"};
            for (String s : t) {
                assertTrue("\"" + s + "\" should be true", isPalindromePermutation(s));
                assertTrue("\"" + s + "\" should be true", isPalindromePermutation2(s));
            }
            for (String s : f) {
                assertFalse("\"" + s + "\" should be false", isPalindromePermutation(s));
                assertFalse("\"" + s + "\" should be false", isPalindromePermutation2(s));
            }
        }
    }
}
