/**
 * Class Life includes the logic which is given in the assignment
 * 
 */
import java.util.*;

public class Life
{
    public static final int ROWS = 20;
    public static final int COLS = 25;
    public static final int TIME_DELAY=200;

    //Main method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Instantiate objects of Board class
        Board board = new Board(ROWS, COLS);
        Board nextBoard = new Board(ROWS, COLS);

        System.out.println("(Press CTRL+Z to end the program):");
        System.out.println("Please enter number of generations to run the Applications For:");

        while (scanner.hasNext()) {
            //run the Application for the given input generations
            ticks(scanner.nextInt(), board, nextBoard);

            }
    }

    /**
     * The ticks static method will run for a specific amount of generations 
     * as provided in the user input.
     * parameters - 
     * generations - no of generations
     * board - the current board
     * nextB - A board with the new generation on it 
     */
    public static void ticks(int generations, Board board, Board nextBoard) {
        //call method to initialize the first board and nextBoard will be a blank board 
        initializeBoard(board);

        //run through a 100 generations
        for(int iterator = 0; iterator < generations; iterator++) {

            clearConsole();

            displayBoard(board);

            slow(TIME_DELAY);

            //calculate the next generation into the nextBoard
            calculateNextGeneration(board, nextBoard);
            
            //transfer the nextBoard to current board
            transfernextToCurrent(board, nextBoard);
        }
    }

    /**
     * The intializeBoard static method sets up the initial board with a 
     * random set of cells.
     * parameters - a Board, typically empty
     */
    public static void initializeBoard(Board b) {
        for(int rIterator = 0; rIterator < ROWS; rIterator++) {
            for(int cIterator = 0; cIterator < COLS; cIterator++) {
                int randVal = (int) (Math.random() * 3); //random number 0, 1 or 2
                if(randVal == 0) {                       // 1/3 chance of having a live cell
                    b.setValue(rIterator, cIterator, 1);
                }
            }
        }
    }
    
    /**
     * The static displayBoard method displays the board on screen. A Board
     * is a 2-dimensional int[][] array, so for the display to include other
     * characters--"." and "0", for example--characters will need to be printed
     * on the screen after checking the int value of that location.
     * parameters - board - the board to be displayed
     */
    public static void displayBoard(Board board) {
        for(int rIterator = 0; rIterator < ROWS; rIterator++) {
            for(int cIterator = 0; cIterator < COLS; cIterator++) {
                if(board.getValue(rIterator, cIterator) == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }
    

    /**
     * The static calculateNextGeneration method takes the current board and 
     * a new (empty) board and calculates the next generation for that second
     * board based on the transitions given
     * parameters - 
     * b - the current board
     * nextB - a board with the new generation on it
     */
    public static void calculateNextGeneration(Board board, Board nextB) {
        for(int rIterator = 0; rIterator < ROWS; rIterator++) {
            for(int cIterator = 0; cIterator < COLS; cIterator++) {

                //get the neighbors count for the cell
                int neighborCount = countNeighbors(rIterator, cIterator, board);

                //set the transition of the cell based on its neighbors

                if(board.getValue(rIterator, cIterator) == 1 && neighborCount < 2) {
                    nextB.setValue(rIterator, cIterator, 0);
                } 
                //check if < 4 neighbors because above case covers 2 Neighbors
                else if(board.getValue(rIterator, cIterator) == 1 && neighborCount < 4) {   
                    nextB.setValue(rIterator, cIterator, 1);
                } 
                else if(board.getValue(rIterator, cIterator) == 1 && neighborCount > 3) {
                    nextB.setValue(rIterator, cIterator, 0);
                } 
                else if(board.getValue(rIterator, cIterator) == 0 && neighborCount == 3) {
                    nextB.setValue(rIterator, cIterator, 1);
                } 
                // if no rules are matched then set it to dead.
                else {
                    nextB.setValue(rIterator, cIterator, 0); 
                }
            }
        }
    }
    
    

    /**
     * The static method countNeighbors counts the  eight neighbors around a given 
     * cell, making sure not to count outside of the bounds of the array and 
     * not to count the current cell itself!
     * parameters - 
     * row - the row of the current cell
     * col - the col of the current cell
     * b - the board we're investigating
     * 
     * return value -
     * the number of non-zero neighbors (minimum 0, maximum 8)
     */
    public static int countNeighbors(int row, int col, Board b) {
        int count = 0;
        //loop to check neighbors of the cell
        for(int rIterator = row - 1; rIterator <= row + 1; rIterator++) {
            for(int cIterator = col - 1; cIterator <= col + 1; cIterator++) {
                //check 1 - a valid row value must be there
                //check 2 - a valid column value must be there
                //check 3 - ignore the current cell location
                //check 4 - the current cell must be live
                if(rIterator >= 0 && rIterator < ROWS &&
                   cIterator >= 0 && cIterator < COLS &&
                   !(rIterator == row && cIterator == col) &&
                   b.getValue(rIterator, cIterator) == 1) 
                {
                    count++;
                }
            }
        }
        return count;
    }
    
    
    /**
     * The static method transferNextToCurrent takes the board with the 
     * next generation and copies it to the board for this generation so 
     * that we can continue displaying and analyzing generations.
     * parameters -
     * board - the current board that we will copy to
     * nextBoard - the next board containing a calculated new generation
     */
    public static void transfernextToCurrent(Board board, Board nextBoard) {
        for(int rIterator = 0; rIterator < ROWS; rIterator++) {
            for(int cIterator = 0; cIterator < COLS; cIterator++) {

                //transfer the next tick board to current board
                board.setValue(rIterator, cIterator, nextBoard.getValue(rIterator, cIterator));
            }
        }
    }
    
    

    /**
     * The clearConsole method clears the terminal so that
     * upcomming generations of the board can be displayed
     */
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // The slow method makes the program sleep for some amount of time to slow display down
    private static void slow(int TIME_DELAY) {
        try
        {
            Thread.sleep(TIME_DELAY);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


}