public class SudokuSolver {

    public static boolean solveSudoku(int[][] board) {
        if (!isValidBoard(board)) {
            return false; // Return false if the board is invalid
        }
        
        return solve(board);
    }

    private static boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) { // Find an empty cell
                    for (int num = 1; num <= 9; num++) { // Try numbers 1-9
                        if (isValid(board, row, col, num)) { // Check if valid
                            board[row][col] = num; // Place the number

                            if (solve(board)) { // Recursively solve
                                return true;
                            }

                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No number works
                }
            }
        }
        return true; // Solved successfully
    }

    // Function to check if placing num at (row, col) is valid
    public static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Function to check if the initial Sudoku board is valid
    public static boolean isValidBoard(int[][] board) {
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        boolean[][] gridCheck = new boolean[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int num = board[row][col];
                if (num != 0) { // Ignore empty cells
                    int gridIndex = (row / 3) * 3 + (col / 3);
                    num--; // Adjust for 0-based indexing

                    if (rowCheck[row][num] || colCheck[col][num] || gridCheck[gridIndex][num]) {
                        return false; // Duplicate found
                    }

                    rowCheck[row][num] = true;
                    colCheck[col][num] = true;
                    gridCheck[gridIndex][num] = true;
                }
            }
        }
        return true; // No duplicates found
    }

    // Function to print the Sudoku board
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main function
    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9} // Correct Sudoku
            // {5, 3, 4, 6, 7, 8, 9, 1, 2},
            // {6, 7, 2, 1, 9, 5, 3, 4, 8},
            // {1, 9, 8, 3, 4, 2, 5, 6, 7},
            // {8, 5, 9, 7, 6, 1, 4, 2, 3},
            // {4, 2, 6, 8, 5, 3, 7, 9, 1},
            // {7, 1, 3, 9, 2, 4, 8, 5, 6},
            // {9, 6, 1, 5, 3, 7, 2, 8, 4},
            // {2, 8, 7, 4, 1, 9, 6, 3, 5},
            // {3, 4, 5, 2, 8, 6, 1, 7, 9}//should remain same
            // {5, 3, 3, 0, 7, 0, 0, 0, 0}, // '3' is duplicated in the first row
            // {6, 0, 0, 1, 9, 5, 0, 0, 0},
            // {0, 9, 8, 0, 0, 0, 0, 6, 0},
            // {8, 0, 0, 0, 6, 0, 0, 0, 3},
            // {4, 0, 0, 8, 0, 3, 0, 0, 1},
            // {7, 0, 0, 0, 2, 0, 0, 0, 6},
            // {0, 6, 0, 0, 0, 0, 2, 8, 0},
            // {0, 0, 0, 4, 1, 9, 0, 0, 5},
            // {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Checking if Sudoku board is valid...");
        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("Invalid Sudoku board. Solution not possible.");
        }
    }
}
