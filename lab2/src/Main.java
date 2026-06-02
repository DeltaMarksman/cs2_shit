import java.util.*;

public class Main {

    static int n;
    static int magic_sum;
    static int[][] board;
    static boolean[] used;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        // find the magic sum and create variables to keep track of the square
        magic_sum = n * (n * n + 1) / 2;
        board = new int[n][n];
        used = new boolean[n * n + 1];

        // print info
        System.out.println("N = " + n);

        // initial call to backtracking function
        backtrack(0, 0);
    }

    static void backtrack(int row, int column) {
        // if we are on the last row, check if the square is complete
        if (row == n) {
            if (full_validate()) {
                print_board();
                System.out.println("Done!!");
            }
            return;
        }

        // if we are at the end of a row or column, increment the row, or set the column back to 0
        // else, keep the row or column the same
        int nextR = (column == n - 1) ? row + 1 : row;
        int nextC = (column == n - 1) ? 0 : column + 1;

        // recursively call this function as partial valid continues to return true.
        // if false, the algorithm will backtrack to the last valid call
        for (int num = 1; num <= n * n; num++) {
            if (!used[num]) {
                board[row][column] = num;
                used[num] = true;

                if (partial_validate(row, column)) {
                    backtrack(nextR, nextC);
                }

                // if false, we are backtracking and we need to mark the number we tried as unused and reset the cell.
                used[num] = false;
                board[row][column] = 0;
            }
        }
    }

    static boolean partial_validate(int row, int column) {
        int sum;

        // if at the end of a row, sum up the current row
        if (column == n - 1) {
            sum = 0;
            for (int j = 0; j < n; j++) sum += board[row][j];
            if (sum != magic_sum) return false;
        }

        // if at bottom of a column, sum up the column
        if (row == n - 1) {
            sum = 0;
            for (int i = 0; i < n; i++) sum += board[i][column];
            if (sum != magic_sum) return false;
        }

        // if at the bottom right corner, sum up the diagonal
        if (row == column && row == n - 1) {
            sum = 0;
            for (int i = 0; i < n; i++) sum += board[i][i];
            if (sum != magic_sum) return false;
        }

        // if in the bottom left corner, sum up the diagonal
        if (column == 0 && row == n - 1) {
            sum = 0;
            // traverse the rows like normal, but start at the end for columns
            for (int i = 0; i < n; i++) sum += board[i][n - i - 1];
            if (sum != magic_sum) return false;
        }
        
        // if we reach this, all the checks have passed
        return true;
    }

    // same as validate_partial function except it runs all the checks
    static boolean full_validate() {
        int sum;
        
        // sum up 
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) sum += board[i][j];
            if (sum != magic_sum) return false;
        }

        for (int j = 0; j < n; j++) {
            sum = 0;
            for (int i = 0; i < n; i++) sum += board[i][j];
            if (sum != magic_sum) return false;
        }

        sum = 0;
        for (int i = 0; i < n; i++) sum += board[i][i];
        if (sum != magic_sum) return false;

        sum = 0;
        for (int i = 0; i < n; i++) sum += board[i][n - 1 - i];
        if (sum != magic_sum) return false;

        return true;
    }

    static void print_board() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
