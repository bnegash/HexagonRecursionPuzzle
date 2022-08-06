import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;



public class TileBoard {

    /*
     * This method will get the text file (fileName)
     * Creates an array of a hexagon form from the data.

     */

    public static Hexagon[] Tiles(String fileName)
            throws FileNotFoundException {
        Scanner inFile = new Scanner(new File(fileName));

        //Creates a hexagon array
        Hexagon[] tiles = new Hexagon[7];

        //Fills the array and reads from the fileName
        int i = 0;
        while (((i < tiles.length) && (inFile.hasNextLine()))) {
            //Read a line
            String line;
            line = inFile.nextLine();

            //Using String split to line split
            String[] index;
            index = line.split("\\s");

            //Gets the tileNum
            int tileNum;
            tileNum = Integer.parseInt(String.valueOf(index[1].charAt(0)));

            //Create a hexagon object and assigns it to tiles at array position i
            // replaces the comma with a space when outputting
            tiles[i] = new Hexagon(tileNum,
                    index[2].replaceAll(",", ""));
            i++;
        }
        //Close file
        inFile.close();

        return tiles;

    }

    /*
        Method finds possible solutions for the hexagon
    */

    public Set<String> solve(Hexagon[] tiles) {
        //Create a solution set store solutions
        Set<String> solution = new HashSet<>();

        int i = 0;
        while (i < tiles.length) {
            Hexagon[] tileBoard = new Hexagon[7];

            tileBoard[0] = tiles[i];  //Sets array at position 0

            tiles[i].setPosition(0);

            //Will add solutions to (solution)
            solution.addAll(solve(tiles, tileBoard, 1));

            tiles[i].setPosition(-1);   //Reset position to -1
            i++;
        }

        return solution;
    }


    /*
        Helper method recursively finds solution for the given tiles. Uses Set<>
        to eliminate any duplicates
        * tiles: original tile set
        * boardPos: current position on the board
        * tileBoard: solution set containing adjacent tile segments that match in color
     */

    private Set<String> solve(Hexagon[] tiles, Hexagon[] tileBoard, int boardPos) {
        //Create a solution set to store solutions
        Set<String> solution = new HashSet<>();

        //Check if there are boardPos to fill
        if (boardPos < tileBoard.length) {

            //Find which Hexagon in tiles array is not yet assigned
            int i = 0;
            if (i < tiles.length) {
                do {

                    //Find out unused Hexagons
                    if (tiles[i].getPosition() == -1) {

                        //Place hexagon at [i] in boardPos
                        tileBoard[boardPos] = tiles[i];
                        tiles[i].setPosition(boardPos);   //Saves the position of Hexagon object in tiles array

                        if (isValid(tileBoard)) { //Checks if valid
                            solution.addAll(solve(tiles, tileBoard, boardPos + 1));
                        }

                        for (int j = 0; j < 5; j++) {
                            tiles[i].rotate();  //Rotates current hexagon
                            //Check if valid
                            if (isValid(tileBoard)) {
                                solution.addAll(solve(tiles, tileBoard, boardPos + 1));
                            }
                        }

                        //Reset back to initial position
                        tiles[i].rotate();

                        //Remove hexagon from position
                        tileBoard[boardPos] = null;
                        tiles[i].position = -1;
                    }
                    i++;
                } while (i < tiles.length);
            }
        } else {
            if (isValid(tileBoard)) {
                StringBuilder str = new StringBuilder();
                //Appends side labels
                str.append(String.format("%37s\n", "SA SB SC SD SE SF"));

                //Using for loop to iterate through tileBoard array
                for (Hexagon hex : tileBoard) {
                    str.append(hex.toString()).append("\n");
                }

                solution.add(str.toString());   //Saves solution to set
            }
        }

        return solution;
    }


    /*
        Method checks if every placement on the given solution board is valid
        Checks center hexagon and checks outer hexagons with each other

    */

    private boolean isValid(Hexagon[] tileBoard) {

        int i = 0;
        while (i <= 5) {
            if (tileBoard[i + 1] != null &&
                    tileBoard[0].tileSegs[i] != tileBoard[i + 1].tileSegs[(i + 3) % 6]) {
                return false;
            }

            if (tileBoard[i + 1] != null &&
                    tileBoard[((i + 1) % 6) + 1] != null
                    && tileBoard[i + 1].tileSegs[(i + 2) % 6] != tileBoard[((i + 1) % 6) + 1].tileSegs[(i + 5) % 6]) {
                return false;
            }
            i++;
        }
        return true;
    }


}


