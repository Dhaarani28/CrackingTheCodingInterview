import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class StringRotation {
    // Constraints
    //  - Input: string s1, string s2
    //  - Output: boolean (true iff s2 is a rotation of s1)
    //  - Length of strings: as much as fits in memory
    //  - Character set of strings: ASCII

    // Brute force
    // Idea
    //  - If s2 is a rotation of s1, then s1 is a rotation of s2
    //      - E.g. if s2 is s1 - 3, then s1 is s2 + 3 (-3 means rotated 3 to the
    //        left, and +3 means rotated 3 to the right)
    //  - To rotate string s by x (where x is a positive/negative/zero integer),
    //    the character at index i is moved to index
    //                      i' = (i+x) % s.length
    //      - E.g. rotate "hello" by x=-2 (2 to the left):
    //          - h: 0 -> (0-2) % 5 = -2 % 5 = 3
    //          - e: 1 -> (1-2) % 5 = -1 % 5 = 4
    //          - l: 2 -> (2-2) % 5 =  0 % 5 = 0
    //          - l: 3 -> (3-2) % 5 =  1 % 5 = 1
    //          - o: 4 -> (4-2) % 5 =  2 % 5 = 2
    //          - Result: "llohe"
    //  - If the strings have unequal length, they cannot be rotations
    //  - Brute force: of one of the two strings, create all the possible
    //    rotations (which number s.length), and compare the result with
    //    the other string
    // Procedure
    //  - Test if s1 and s2 have unequal length, if yes, return false
    //  - Test if s1 equals s2, if yes, return true
    //  - Initialise an empty string builder with length s1.length
    //  - Iterate i from 1 to s1.length-1
    //      - Append the i last characters of s1 to the string builder
    //      - Append the first s1.length-i characters to the string builder
    //      - Compare the string in the string builder with s2
    //          - If equal, return true
    //  - Return false
    // Complexity
    //  - Time
    //      - (N-1)*N + N*N = O(N^2)
    //          - N-1 rotations, each one involving building a N-length string
    //          - Compare N pairs of N-length strings
    //  - Space
    //      - N = O(N): a string holding the rotation of one of the input strings
    private static boolean isRotationBF(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        for (int i = 1; i < s1.length(); i++) {
            StringBuilder sb = new StringBuilder(s1.length());
            sb.append(s1.substring(s1.length()-i));
            sb.append(s1.substring(0, s1.length()-i));
            if (sb.toString().equals(s2)) return true;
        }
        return false;
    }

    // Iteration and comparison
    // Idea
    //  - A rotation of a string is the original string split in two parts and
    //    the order of the parts exchanged
    //      - E.g. wat|erbottle --> erbottle|wat
    //  - The left part in s1 starts at beginning of string and ends somewhere
    //    in the middle, and this is the right part of s2, which starts some-
    //    where in the middle of s2 and ends at the end of the string
    //  - If we can identify this part in both strings, we can check whether the
    //    remaining parts (i.e. right part of s1 and left part of s2) are equal
    //  - To identify this part, we start iterating through both strings with
    //    two separate pointers i (s1) and j (s2), both starting at index 0
    //      - Note: in the following we assume that s1 and s2 are not equal
    //      - If the characters at position i and j are different
    //          - If i and j are still equal (0), increment j, don't change i
    //          - If i and j are already different, return false
    //      - If the characters are equal
    //          - If i and j are still equal (0), increment j, don't change i
    //            (we're at the start of the string, and this can't be the start
    //            of the part we are looking for, because s1 != s2)
    //          - If i and j are already different, increment both i and j
    //  - Eventually, j will hit the end of the string. If we went through until
    //    here (without returning false), then we found the part we were looking
    //    for, and i points to the last character of this part in s1 (this is
    //    the left part in s1 and the right part in s2)
    //  - The length of this part is i+1. Consequently, the length of the
    //    complementary part is s.length - i - 1
    //  - Thus, the complementary right part of s1 is from i+1 to the end of
    //    the string, and the corresponding left part of s2 is from 0 to
    //    s.length - i - 1 - 1
    //  - Now we can check if these two complementary parts are equal
    //      - If yes, it is a rotation
    //      - If no, it is not a rotation
    // Procedure
    //  - If length of s1 and s2 differ, return false
    //  - If s1 equals s2, return true
    //  - Initialise pointers i (for s1) to 0 and j (for s2) to 1
    //  - While j < s2.length
    //      - If s1[i] == s2[j]
    //          - Increment i and j
    //      - If s1[i] != s2[j]
    //          - Reset i to 0 and increment j
    //  - If it is a rotation, then i now points to the first character of
    //    the right part of s2
    //  - Compare s1[i..end], and s2[0..s2.length-i] (the complementary parts)
    //      - If they are equal, return true
    //      - Else, return false
    // Complexity
    //  - Time
    //      - N + N/2 = O(N)
    //          - N: iterating one time through the entire string with j
    //          - N/2: comparing the complementary parts (avg length N/2)
    //  - Space
    //      - O(1): no additional memory needed
    private static boolean isRotation1(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        int i = 0;
        int j = 1;
        while (j < s2.length())
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                i = 0;
                j++;
            }
        String rightPartOfS1 = s1.substring(i);
        String leftPartOfS2 = s2.substring(0, s2.length()-i);
        return rightPartOfS1.equals(leftPartOfS2);
    }

    // Concatenation and substring
    // Idea
    //  - If we concatenate any of the two strings to itself, then it contains
    //    the two parts of the rotation in the opposite order (if it's a rotation)
    //  - Thus, if we concatenate one of the two strings and it is a rotation
    //    of the other string, then the concatenation must contain the other
    //    string as a substring
    //  - Strings are rotations --implies--> Find substring
    //    Find substring --implies--> Strings are rotations (not sure why...)
    // Complexity
    //  - Time
    //      - 2N + 2N = O(N)
    //          - 2N: concatenate s1
    //          - 2N: find s2 in concatenation of s1
    //  - Space
    //      - 2N = O(N): concatenation of s1
    private static boolean isRotation2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        String s1s2 = s1 + s1;
        return s1s2.contains(s2);
    }

    // Test Cases
    //  - Return true
    //      - ("", ""), ("a", "a"), ("waterbottle", "erbottlewat"),
    //        ("wawawat", "twawawa"), ("erbottlewat", "ewaterbottl")
    //  - Return false
    //      - ("", "a"), ("a", "b"), ("eaterbottlee", "erbottleeate")
    public static class Test {
        public static void main(String[] args) {
            String[] t1 = {"", "a", "waterbottle", "wawawat", "erbottlewat"};
            String[] t2 = {"", "a", "erbottlewat", "twawawa", "ewaterbottl"};
            String[] f1 = {"", "a", "eaterbottlee"};
            String[] f2 = {"a", "b", "erbottleeate"};
            for (int i = 0; i < t1.length; i++) {
                assertTrue("(\""+t1[i]+"\",\""+t2[i]+"\") should be true", isRotation2(t1[i], t2[i]));
            }
            for (int i = 0; i < f1.length; i++) {
                assertFalse("(\""+f1[i]+"\",\""+f2[i]+"\") should be false", isRotation2(f1[i], f2[i]));
            }
        }
    }
}
