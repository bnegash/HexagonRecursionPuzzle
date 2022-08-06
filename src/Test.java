/*
* Name: Bruck Negash
* Description: Purpose of this assignment is to apply what we know about recursion and backtracking to
* create and solve a hexagon puzzle
* Other Comments:
 */


import java.io.FileNotFoundException;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        // Creates TileBoard object
        TileBoard board = new TileBoard();

        // Input file name
        String fileName = "(somefile)";

        try {
            // Get tile set from input file
            Hexagon[] tiles;
            tiles = TileBoard.Tiles(fileName);

            // Get solutions
            Set<String> solution;
            solution = board.solve(tiles);

            // Displays how many solutions, if any
            if (solution.isEmpty()) {   // Check if there is no solution
                System.out.println("No Solution");
            }
            else {
                if (solution.size() == 1) { //Check if only 1 solution
                    System.out.println("1 Solution");
                }
                else {
                    System.out.println("More than 1 Solution");
                }

                // Displays solutions
                int i = 0;
                for (String ans : solution) {
                    System.out.println("Solution #" + ++i + "---------------------------------------\n"
                            + ans
                            + "--------------------------------------------------\n");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Invalid!");
        }

    }
}


