/**
 * Class Board is used to represent the 2D grid of cells
 * it is a POJO (plain old java object) class 
 * the object of Board is used to fill and display the cells.
 * 
 * In the Board dead cell is represented by -  "."
 *              alive cell is represented by - "0"
 * 
 * At each new generation the rules are applied to the entire Board 
 * and the new state of cells is calculated.
 * 
 */
public class Board
{
    // instance variable
    int[][] board;

    
    // constructor of Board
    // input parameters - no of rows, no of columns
    public Board(int rows, int cols) {
        // initialise instance variables
        board = new int[rows][cols];
    }

    /**
     * The get method returns the value stored at the specified 
     * row,col location.
     * input parameters -
     * row The row of the grid
     * col The column of the grid
     * 
     * returns - 
     * the int value stored at that row,col
     */
    public int getValue(int row, int col) {
        return board[row][col];
    }
    
    /**
     * The set method sets the specified row,col location to 
     * the specified value
     * input parameters - 
     * row The row of the grid
     * col The column of the grid
     * value The int value to be stored at row,col
     */
    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }
    
    /**
     * The getRows method returns the number of rows (the height) 
     * of the grid
     * returns - the rows (height) of the grid
     */
     public int getRows() {
        return board.length;
     }
    
    /**
     * The getCols method returns the number of columns (the width) 
     * of the grid
     * returns - the columns (width) of the grid
     */
    public int getCols() {
        return board[0].length;
    }
    
    /**
     * The toString method returns a String that can be printed to 
     * display the grid
     * returns - a String representing the grid
     */
    public String toString() {
        String result = "";
        for(int rIterator = 0; rIterator < getRows(); rIterator++) {
            for(int cIterator = 0; cIterator < getCols(); cIterator++) {
                result += board[rIterator][cIterator]; 
            }
            result += '\n';
        }

        return result;
    }
    
    
}