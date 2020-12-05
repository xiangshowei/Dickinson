package comp132.examples.arrays;

/**
 * This class contains several methods that demonstrate the creation
 * and access of elements in 2-Dimensional arrays.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 15, 2009
 */
public class TwoDimensionalArrays {

    /**
     * Demonstrates the creation and use of a two-dimensional array. This method
     * creates a 3x3 array of char, sets each entry to a '.' representing an
     * empty space and then sets a few elements to either 'X' or 'O' and finally
     * prints out the contents of the array.
     */
    public static void rectangularArray() {
        char[][] tttBoard = new char[3][3];
        
        // Fill the board with '.' representing empty spaces
        for (int row=0; row<tttBoard.length; row++) {
            for (int col=0; col<tttBoard[row].length; col++) {
                tttBoard[row][col] = '.';
            }
        }
        
        // Fill in a few elements.
        tttBoard[0][0] = 'X';
        tttBoard[1][1] = 'O';
        tttBoard[0][2] = 'X';
        
        // Print out the board.
        for (int row=0; row<tttBoard.length; row++) {
            for (int col=0; col<tttBoard[row].length; col++) {
                System.out.print(tttBoard[row][col] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Demonstrates the creation and use of a ragged two-dimensional array.  This method
     * creates a ragged array of int values, fills in each entry with the product of its
     * row and column indices, doubles the value of all even entries and finally prints out
     * the contents of the array.
     */
    public static void raggedArray() {
        // Create the array that holds the references to the rows.
        int[][] ragArray = new int[3][];
        
        // Create the array for each row.
        ragArray[0] = new int[2];
        ragArray[1] = new int[4];
        ragArray[2] = new int[3];
        
        // Set the value of each entry to the product of its row and column indices.
        for (int row=0; row<ragArray.length; row++) {
            for (int col=0; col<ragArray[row].length; col++) {
                ragArray[row][col] = row*col;
            }
        }

        // Double the value of all of the even entries.
        for (int row=0; row<ragArray.length; row++) {
            for (int col=0; col<ragArray[row].length; col++) {
                if (ragArray[row][col] % 2 == 0) {
                    ragArray[row][col] = ragArray[row][col] * 2;
                }
            }
        }
        
        // Print out the contents of the array.
        for (int[] row : ragArray) {
            for (int colVal : row) {
                System.out.print(colVal + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Run each of the above methods.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        System.out.println("rectangularArray()");
        rectangularArray();
        
        System.out.println();
        System.out.println("raggedArray()");
        raggedArray();
    }
}
