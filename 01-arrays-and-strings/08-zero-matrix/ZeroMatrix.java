import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;

public class ZeroMatrix {

    // Constraints
    // Input: NxM integer matrix
    // Output: NxM integer matrix with changed values


    // Extra space
    // Idea
    //  - Iterate through all elements of the matrix, and for each element:
    //      - If it's a 0, add its row index to an integer set 'rows' and its
    //        column index to an integer set 'cols'
    //  - Iterate through the 'rows' set, and for each index, set the
    //    corresponding row to 0
    //  - Iterate through the 'cols' set, and for each index set the
    //    corresponding column to 0   
    // Complexity
    //  - Time
    //      - N*M + N*M + M*N = O(N*M)
    //          - N*M: iterate throug all elements of matrix
    //          - N*M: for each element of the 'rows' set, set M elts to 0
    //          - M*N: for each element of the 'cols' set, set N elts to 0
    //  - Space
    //      - O(N+M)
    //          - The two sets 'rows' and 'cols'
    private static void zeroMatrix(int[][] a) {
        int n = a.length;                 // Number of rows
        int m = n > 0 ? a[0].length : 0;  // Number of columns
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (a[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
        Iterator<Integer> it;
        it = rows.iterator();
        while (it.hasNext()) nullifyRow(a, it.next());
        it = cols.iterator();
        while (it.hasNext()) nullifyCol(a, it.next());
    }

    private static void nullifyRow(int[][] a, int row) {
        for (int i = 0; i < a[0].length; i++) a[row][i] = 0;
    }
    private static void nullifyCol(int[][] a, int col) {
        for (int i = 0; i < a.length; i++) a[i][col] = 0;
    }

    // In-place
    // Idea
    //  - Instead of storing the row and column indices in an external set, we:
    //      - Mark the max. N rows that contain a 0 in column 0, by setting the
    //        corresponding elements of column 0 to 0
    //      - Mark the max. M columns that contain a 0 in row 0, by setting the
    //        corresponding elements of row 0 to 0
    // - Before writing 0s to row 0 and column 0, we have to check whether row 0
    //   and column 0 itself contained any 0s before we wrote our 0s there
    // Procedure
    //  - Initialise booleans 'row0HasZero' and 'col0HasZero' to false
    //  - For each element of row 0:
    //      - If it is 0, set row0HasZero to true, and break
    //  - For each element of col 0:
    //      - If it is 0, set col0HasZero to true, and break
    //  - For each element of row i in [1..N-1] and col j in [1..M-1]
    //      - If it is a 0, set a[i][0] and a[0][j] to 0
    //  - For each position i of row 0
    //      - If its element is 0, set column i to 0
    //  - For each position i of column 0
    //      - If its element is 0, set row i to 0
    //  - If row0HasZero is true, set row 0 to 0
    //  - If col0HasZero is true, set column 0 to 0
    // Complexity
    //  - Time
    //      - N*M + M*N + N*M = O(N*M)
    //          - N*M: iterate through all elements of the matrix
    //          - M*N: iterate through row 0 and set columns of length N to 0
    //          - N*M: iterate through col 0 and set rows of length M to 0
    //  - Space
    //      - O(1) (only extra space is booleans row0HasZero and col0HasZero)
    private static void zeroMatrix2(int[][] a) {
        int n = a.length;                 // Number of rows
        int m = n > 0 ? a[0].length : 0;  // Number of columns
        boolean row0HasZero = checkRow0(a, m);
        boolean col0HasZero = checkCol0(a, n);
        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++)
                if (a[i][j] == 0) {
                    a[0][j] = 0;
                    a[i][0] = 0;
                }
        // Iterate through positions [1..M-1] of row 0
        for (int i = 1; i < m; i++)
            if (a[0][i] == 0) nullifyCol(a, i);
        // Iterate through positions [1..N-1] of column 0
        for (int i = 1; i < n; i++)
            if (a[i][0] == 0) nullifyRow(a, i);
        // Set row 0 and column 0 itself to zero, if necessary
        if (row0HasZero) nullifyRow(a, 0);
        if (col0HasZero) nullifyCol(a, 0);
    }

    private static boolean checkRow0(int[][] a, int ncols) {
        for (int i = 0; i < ncols; i++)
            if (a[0][i] == 0) return true;
        return false;
    }

    private static boolean checkCol0(int[][] a, int nrows) {
        for (int i = 0; i < nrows; i++)
            if (a[i][0] == 0) return true;
        return false;
    }


    // Test Cases
    public static class Test {
        public static void main(String[] args) {
            // Sparse 0s
            int[][] in = new int[][] {
                {1,1,1,1,1},
                {1,1,1,0,1},
                {0,1,1,1,1},
                {1,1,1,1,1}
            };
            int[][] out = new int[][] {
                {0,1,1,0,1},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,1,1,0,1}
            };
            test(in, out);

            // Sparse 0s
            in = new int[][] {
                {0,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1}
            };
            out = new int[][] {
                {0,0,0,0,0},
                {0,1,1,1,1},
                {0,1,1,1,1},
                {0,1,1,1,1}
            };
            test(in, out);

            // Many 0s
            in = new int[][] {
                {1,0,1,0,1},
                {0,1,0,0,1},
                {0,1,0,1,1},
                {1,0,1,0,0}
            };
            out = new int[][] {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
            };
            test(in, out);

            // Empty
            in = new int[0][0];
            out = new int[0][0];
            test(in, out);

            // Single 0
            in = new int[][] {{0}};
            out = new int[][] {{0}};
            test(in, out);
            
            // Single non-0
            in = new int[][] {{1}};
            out = new int[][] {{1}};
            test(in, out);
        }

        private static void test(int[][] in, int[][] out) {
            zeroMatrix2(in);
            for (int i = 0; i < in.length; i++)
                for (int j = 0; j < in[0].length; j++) {
                    assertEquals("Element ("+i+","+j+")", out[i][j], in[i][j]);
                }
        }

    }
}
