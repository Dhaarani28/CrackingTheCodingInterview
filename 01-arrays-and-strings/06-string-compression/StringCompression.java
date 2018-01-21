import static org.junit.Assert.assertEquals;

public class StringCompression {

    // Constraints
    // Input: string
    // Output: string
    // Length of input string: as much as fits in memory
    // Character set of input string: a-z, A-Z (52 characters)
    // Case sensitivity: case sensitive (a is different from A)
    // Sequences of one char: append the number (e.g. "a" -> "a1")

    // Idea
    //  - Construct the output string during the pass through the input string
    // Procedure
    //  - Create char var 'currentChar' initialised to something not in a-z, A-Z
    //  - Create integer variable 'currentCount' initialised to 0
    //  - Create a string builder containing the empty string
    //  - For each character c of input string
    //      - Test if it equals 'currentChar'
    //          - If yes, increment 'currentCount' by 1
    //          - If no
    //              - If 'currentCount' is larger than 0
    //                  - Write 'currentChar''currentCount' to string builder
    //              - Set 'currentChar' to c, and 'currentCount' to 1
    //  - Test if string from string builder is shorter than input string
    //      - If yes, return string from string builder
    //      - If no, retur input string
    // Complexity
    //  - Time
    //      - O(N)
    //  - Space
    //      - O(N) (building a new string)
    private static String compress(String s) {
        char currentChar = '?';
        int currentCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == currentChar) currentCount++;
            else {
                append(sb, currentChar, currentCount);
                currentChar = c;
                currentCount = 1;
            }
        }
        append(sb, currentChar, currentCount);
        return sb.length() < s.length() ? sb.toString() : s;
    }

    private static void append(StringBuilder sb, char ch, int co) {
        if (co > 0) sb.append(String.valueOf(ch) + co);
    }


    // Test Cases
    //  - "" -> ""
    //  - "a" -> "a"
    //  - "aa" -> "aa"
    //  - "aaa" -> "a3"
    //  - "aaab" -> "aaab"
    //  - "aaaab" -> "a4b1"
    //  - "aabcccccaaa" -> "a2b1c5a3"
    public static class Test {
        public static void main(String[] args) {
            String[] i = {"", "a", "aa", "aaa", "aaab", "aaaab", "aabcccccaaa"};
            String[] o = {"", "a", "aa", "a3", "aaab", "a4b1", "a2b1c5a3"};
            for (int j = 0; j < i.length; j++)
                assertEquals(o[j], compress(i[j]));
        }
    }
}
