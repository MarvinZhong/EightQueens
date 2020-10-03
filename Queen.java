//姓名 : 鍾明智
//學號 : 107213067
import java.util.Scanner;
public class Queen {
    private static int k = 1; //solutions counter
    //A utility function to print solution
    private static void printSolution(int[][] board, int N) {
        System.out.printf("Solution Number %d :\n", k++);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf(" %d ", board[i][j]);
            System.out.print("\n");
        }
        System.out.println("--------------------");
    }
    /* A utility function to check if a queen can be placed on board[row][col].
    Note that this function is called when "col" queens are already placed in columns from 0 to col -1.
    So we need to check only left side for attacking queens */
    private static boolean CheckQueen(int[][] board, int row, int col, int N) {
        int i, j;
        for (i = 0; i < col; i++) //Check this row on left side
            if (board[row][i] == 1)
                return false;
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) //Check upper diagonal on left side
            if (board[i][j] == 1)
                return false;
        for (i = row, j = col; j >= 0 && i < N; i++, j--) //Check lower diagonal on left side
            if (board[i][j] == 1)
                return false;
        return true;
    }
    //A recursive utility function to solve N Queen problem
    private static boolean solvingQueen(int[][] board, int col, int N) {
	    //base case: If all queens are placed then return true
        if (col == N) {
            printSolution(board, N);
            return true;
        }
	    //Consider this column and try placing this queen in all rows one by one
        boolean res = false;
        for (int i = 0; i < N; i++) {
            //Check if queen can be placed on board[i][col]
            if (CheckQueen(board, i, col, N)) {
                //Place this queen in board[i][col]
                board[i][col] = 1;
                //Make result true if any placement is possible
                res = solvingQueen(board, col + 1, N) || res;
			    //If placing queen in board[i][col] doesn't lead to a solution, then remove queen from board[i][col]
                board[i][col] = 0; //BACKTRACK
            }
        }
	    //If queen can not be place in any row in this column col then return false
        return res;
    }
    /* This function solves the N Queen problem using Backtracking.
    It mainly uses solveNQUtil() to solve the problem.
    It returns false if queens cannot be placed, otherwise return true and prints placement of queens in the form of 1s.
    Please note that there may be more than one solutions, this function prints one of the feasible solutions.*/
    private static void Solving(int N) {
        int[][] board = new int[N][N];
        if (!solvingQueen(board, 0, N)) { //solveNQUtil(board, 0, N) == false
            System.out.print("Solution does not exist");
        }
    }
    //Main code
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        Solving(N);
    }
}