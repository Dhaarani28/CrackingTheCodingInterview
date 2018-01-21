import static org.junit.Assert.assertEquals;

public class RotateMatrix {

    // Constraints
    // Input: NxN 2-dimensional integer array
    // Output: same structure as input with modified values


    // Extra space
    // Idea
    //  - All values of a given row end up in 4 different rows in a single column
    //      - Row i becomes column N-1-i
    //      - E.g. if N=5: r0->c4, r1->c3, r2->c2, r3->c1, r4->c0
    //  - The column indices of the values of a given row become the row indices
    //    of the values in the target column
    //      - E.g. if N=5, r=0, and c=4:
    //          - (r,0)->(0,c), (r,1)->(1,c), (r,2)->(2,c), (r,3)->(3,c), (r,4)->(4,c)
    //  - Move each element (i,j) to (j,N-1-i) in the target matrix
    // Procedure
    //  - Create empty integer matrix similar to input matrix
    //  - For each element (i,j) of the input matrix
    //      - Move it to (j,N-1-i) in the matrix
    //  - Return target matrix
    // Complexity
    //  - Time
    //      - O(N) (N is the number of cells/pixels in the matrix)
    //  - Space
    //      - O(N) (copying of the input matrix)
    private static int[][] rotateMatrix(int[][] a) {
        int n = a.length;
        int[][] target = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                target[j][n-1-i] = a[i][j];
        return target;
    }

    // In-place
    // Idea
    //  - Looking at corners, there are the following moves (90° right rotation):
    //      - NW->NE, NE->SE, SE->SW, SW->NW
    //  - We need to rotate elments in chains of 4
    //  - On row 0, we need to rotate all but the last element
    //      - Because the last element of row 0 (NE) has already the right value
    //        after rotating the first element (NW)
    //      - This results in all elements in row 0 and row N-1 and col 0 and
    //        col N-1 being in the right position (rows and cols at the edge)
    //  - On row 1, we look at the sub-matrix from row 1 to row N-2, and col 1
    //    to col N-2 (i.e. skip the row and column at the edges)
    //      - On this row 1, we need to rotate all but the last element
    //  - etc.
    //  - Can be done recursively, rotating a "shell" of the matrix at each step
    //  - Use method 'rotate' which takes as input a ref to the matrix and the
    //    index (i,j) of the element to rotate. This results in 4 data moves.
    //  - Use recursive method 'rotateMatrix' which takes as input a ref to the
    //    matrix, and an offset from the matrix edge, which defines which "shell"
    //    to rotate
    //      - Base case: if offset is N/2, return
    //          - This means, remaining matrix has size 0 or 1
    //      -  On row 'offset', call 'rotate' for elements on cols 'offset' to
    //         N - 'offset' - 1
    //      - Then make recursive call to 'rotateMatrix', with 'offset'+1
    // Complexity
    //  - Time
    //      - O(N) (if N is the number of elements in the matrix)
    //  - Space
    //      - O(1) (no additional space needed, except one tmp variable)
    private static int[][] rotateMatrix2(int[][] a) {
        rotateMatrixRecursive(a, 0);
        return a;
    }

    private static void rotateMatrixRecursive(int[][] a, int offset) {
        int n = a.length;
        if (offset >= n/2) return;
        for (int c = offset; c < n-offset-1; c++)
            rotate(a, offset, c);
        rotateMatrixRecursive(a, offset+1);
    }

    private static void rotate(int[][] a, int row, int col) {
        int n = a.length;
        int tmp = a[row][col];
        for (int i = 0; i < 3; i++) {
            int sourceRow = n-1-col;
            int sourceCol = row;
            a[row][col] = a[sourceRow][sourceCol];
            row = sourceRow;
            col = sourceCol;
        }
        a[row][col] = tmp;
    }

    // Test Cases
    public static class Test {
        public static void main(String[] args) {
            // N=5
            int[][] in = new int[][] {
                { 1, 2, 3, 4, 5},
                { 6, 7, 8, 9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
            };
            int[][] out = new int[][] {
                {21,16,11,6,1},
                {22,17,12,7,2},
                {23,18,13,8,3},
                {24,19,14,9,4},
                {25,20,15,10,5}
            };
            test(in, out);

            // N=0 (empty matrix)
            in = new int[0][0];
            out = new int[0][0];
            test(in, out);

            // N=1
            in = new int[][] {{1}};
            out = new int[][] {{1}};
            test(in, out);

            // N=2
            in = new int[][] {{1,2}, {3,4}};
            out = new int[][] {{3,1}, {4,2}};
            test(in, out);
        }

        private static void test(int[][] in, int[][] out) {
            int[][] res1 = rotateMatrix(in);
            int[][] res2 = rotateMatrix2(in);
            for (int i = 0; i < in.length; i++)
                for (int j = 0; j < in.length; j++) {
                    assertEquals("Element ("+i+","+j+")", out[i][j], res1[i][j]);
                    assertEquals("Element ("+i+","+j+")", out[i][j], res2[i][j]);
                }
        }

        private static void printMatrix(int[][] a) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++)
                    System.out.print(a[i][j] + " ");
                System.out.println();
            }
        }
    }
}
