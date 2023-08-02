// Names: Benjamin DeAcetis and Israel Fuller
// x500s: deace003 and fulle637
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


/*Make and solve maze */
//The dimensions of the maze will depend on the input given by the user. Both rows and cols
//should be between 5 and 20 inclusive. Sizes outside the bounds should be handled.
public class MyMaze {
    private Cell[][] maze;
    private int startRow;
    private int endRow;
    private int steps;

    // Constructor written by deace003, checked by fulle637
    public MyMaze(int rows, int cols, int startRow, int endRow) {
        this.maze = new Cell[rows][cols]; // Instantiate maze array
        for (int i = 0; i<rows;i++) { // Set each element in the maze array to an empty cell
            for (int j = 0; j< cols; j++) {
                this.maze[i][j] = new Cell();
            }
        }

        // Initialize startRow and endRow.
        this.startRow = startRow;
        this.endRow = endRow;
    }

    // Written by deace003, checked by fulle637
    // Description: Helper function that checks if a move is legal.
    // Note: Does not verify the maze!
    public static boolean isValidMove(Cell[][] maze, int nextRow,int nextCol) {
        return nextRow<maze.length && nextRow>=0 && nextCol<maze[0].length && nextCol>=0 // Out of bounds cells are not valid moves
                && !maze[nextRow][nextCol].getVisited(); // Already visited cells are not valid moves.
    }

    // Written by fulle637, checked by deace003
    // Description: Accessor method for endRow.
    public int getEndRow() {
        return endRow;
    }

    // Written by fulle637, checked by deace003
    // Description: Accessor method for startRow.
    public int getStartRow() {
        return startRow;
    }

    // Written by fulle637, checked by deace003
    // Description: Accessor method for the step count.
    public int getSteps() {
        return steps;
    }

    // Written by fulle637, checked by deace003
    // Description: Asks the user for an int answer between min and max inclusive.
    private static int getAns(String name, int min, int max) {
        return getAns(name, min, max, new Scanner(System.in));
    }

    // Written by fulle637, checked by deace003
    // Description: Asks the user for an int answer between min and max inclusive.
    private static int getAns(String name, int min, int max, Scanner s) {
        String ans;
        int num;
        while (true) { // Repeat until the user enters a valid input.
            System.out.print("Enter a "+name+" in between "+min+" and "+max+" (inclusive): ");

            ans = s.next();
            try { // Try to make the user's input an int.
                num = Integer.parseInt(ans);
            } catch (NumberFormatException e) { // Tell them to enter a number.
                System.out.println("Invalid input! "+ans+" is not a number.");
                continue; // Skip back to the top so that the user can try again.
            }

            if (num>=min && num<=max) return num; // Check if the user entered a number in the correct range.
            System.out.println("Invalid input! "+ans+" is not between "+min+" and "+max+" (inclusive).");
        }
    }

    // Written by deace003, checked by fulle637
    // Description: Generates a solvable maze of a size between 5x5 and 20x20 (inclusive). Asks the user for the size.
    public static MyMaze makeMaze() {
        Scanner s = new Scanner(System.in); // We will use this to get user input for row and column size (maze size) in the next line.
        return makeMaze(getAns("row size", 5, 20, s), getAns("column size", 5, 20, s)); // Ask the user for rows and colums, then make and return the maze.
    }

    // Written by fulle637/deace003, checked by deace003
    // Description: Generates a solvable maze of a size between 5x5 and 20x20 (inclusive).
    //      Uses the size provided by the rows and cols parameters.
    //      Providing sizes larger than 20 or less than 5 will lead to errors.
    public static MyMaze makeMaze(int rows, int cols) {
        Random rand = new Random(); // Start and end points are randomly generated in the next line using this object.
        MyMaze myMaze = new MyMaze(rows, cols, rand.nextInt(rows-1)+1, rand.nextInt(rows-1)+1);
        myMaze.verify(false); // The maze should have valid dimensions.
        Stack1Gen<int[]> stack = new Stack1Gen<>(); // Initialize a new stack.

        myMaze.maze[myMaze.startRow][0].setVisited(true); // first cell is visited
        myMaze.maze[myMaze.endRow][cols-1].setRight(false); // opening for the exit

        stack.push(new int[]{myMaze.startRow,0}); // pushing starting spot into stack

        while (!stack.isEmpty()) {
            int[] temp = stack.top(); // index of current cell
            if (!isValidMove(myMaze.maze, temp[0]-1,temp[1]) && !isValidMove(myMaze.maze, temp[0],temp[1]+1)&&
                    !isValidMove(myMaze.maze, temp[0]+1,temp[1]) && !isValidMove(myMaze.maze, temp[0],temp[1]-1)){
                stack.pop(); // Pop the element from the stack if this is a dead end.
            } else {
                boolean found = false;
                while(!found) { // Loop through to choose next coordinate. Note: This could be optimized by not rechecking locations.
                    switch (rand.nextInt(4)) { // Directions: 0=up, 1=right, 2=down, 3=left
                        case 0: // Go Up
                            if (isValidMove(myMaze.maze, temp[0]-1,temp[1])) { found = true; // We found it!
                                myMaze.maze[temp[0] - 1][temp[1]].setVisited(true);
                                myMaze.maze[temp[0] - 1][temp[1]].setBottom(false);
                                stack.push(new int[]{temp[0] - 1, temp[1]});
                            }
                            break;
                        case 1: // Go Right
                            if (isValidMove(myMaze.maze, temp[0],temp[1]+1)) { found = true; // We found it!
                                myMaze.maze[temp[0]][temp[1] + 1].setVisited(true);
                                myMaze.maze[temp[0]][temp[1]].setRight(false);
                                stack.push(new int[]{temp[0], temp[1] + 1});
                            }
                            break;
                        case 2: // Go Down
                            if (isValidMove(myMaze.maze, temp[0]+1,temp[1])) { found = true; // We found it!
                                myMaze.maze[temp[0] + 1][temp[1]].setVisited(true);
                                myMaze.maze[temp[0]][temp[1]].setBottom(false);
                                stack.push(new int[]{temp[0] + 1, temp[1]});
                            }
                            break;
                        case 3: // Go Left
                            if (isValidMove(myMaze.maze, temp[0],temp[1]-1)) { found = true; // We found it!
                                myMaze.maze[temp[0]][temp[1]-1].setVisited(true);
                                myMaze.maze[temp[0]][temp[1]-1].setRight(false);
                                stack.push(new int[] {temp[0],temp[1]-1});
                            }
                            break;
                    }
                }
            }
        }
        myMaze.verify(true); // Make sure that the maze was created correctly.
        myMaze.unvisit(); // Set the entire maze to be unvisted.
        return myMaze;
    }

    // Written by fulle637, checked by deace003
    // Description: Sets the entire maze to be unvisted.
    public void unvisit() {
        setAllVisited(false);
    }

    // Written by deace003 & fulle637
    // Description: Set the isVisited property of each cell in the maze to "visited".
    public void setAllVisited(boolean visited) {
        verify(false); // Make sure the maze is valid.
        for (Cell[] cells : maze) { // Loop iterate through every cell of the maze.
            for (Cell cell : cells) {
                cell.setVisited(visited); // Set each cell to be unvisited.
            }
        }
    }

    // Written by fulle637, chedked by deace003
    // Description: Verifies the maze before printing it.
    public void printMaze() {
        verify(false); // Make sure the maze is valid.
        unverifiedPrintMaze(); // Print the maze.
    }

    // Written by fulle637, checked by deace003
    // Description: Prints the maze without verifying it.
    public void unverifiedPrintMaze() {
        System.out.print("|");
        for (int i = 0; i < maze[0].length; i++) System.out.print("---|"); // Add a wall across the top.
        System.out.println();

        StringBuilder nextLine = new StringBuilder();
        for (int i = 0; i < maze.length; i++) {
            if (i==startRow) System.out.print(" ");
            else System.out.print("|"); // Add a wall across the left with a gap for the start row.

            for (Cell cell : maze[i]) {
                if (cell.getVisited()) System.out.print(" * ");
                else System.out.print("   ");  // Add a star if it's visited.
                if (cell.getRight()) System.out.print("|");
                else System.out.print(" "); // Add a right wall if there is one.
                if (cell.getBottom() || i==maze.length-1) nextLine.append("---|");
                else nextLine.append("   |"); // Add a bottom wall to the next line if there is a bottom wall.
            }
            System.out.println("\n|"+nextLine); // Terminate the line and print the next line, then terminate it.
            nextLine = new StringBuilder(); // Reset the next line for the next iteration of the loop.
        }
    }

    // Written by fulle637, checked by deace003
    // Description: Verifies the maze, then finds a solution to it.
    public void solveMaze() {
        verify(true); // Handle invalid maze sizes.
        unverifiedSolveMaze(0); // Solve the maze without printing anything.
    }

    // Written by fulle637, checked by deace003
    // Description: Solves the maze without verifying it.
    //      Prints the maze progress every printFrequency steps. If printFrequency is zero or less, doesn't show progress.
    public void unverifiedSolveMaze(int printFrequency) {
        Q1Gen<int[]> q = new Q1Gen<>(); // Initialize a new queue.
        q.add(new int[]{startRow,0});

        steps = 0;
        while (q.length() > 0) {
            int[] coords = q.remove(); // Grab the current coordinates.
            Cell cur = maze[coords[0]][coords[1]]; // Find the current cell from the coordinates.
            cur.setVisited(true); // We are visiting the current cell.
            if (coords[0]==endRow && coords[1]==maze[0].length-1) break; // Check to see if we've found the end!

            if (isValidMove(maze, coords[0]-1, coords[1]) && !maze[coords[0]-1][coords[1]].getBottom()) // Look Up
                q.add(new int[]{coords[0]-1, coords[1]}); // Add Up
            if (isValidMove(maze, coords[0], coords[1]+1) && !maze[coords[0]][coords[1]].getRight()) // Look Right
                q.add(new int[]{coords[0], coords[1]+1}); // Add Right
            if (isValidMove(maze, coords[0]+1, coords[1]) && !maze[coords[0]][coords[1]].getBottom()) // Look Down
                q.add(new int[]{coords[0]+1, coords[1]}); // Add Down
            if (isValidMove(maze, coords[0], coords[1]-1) && !maze[coords[0]][coords[1]-1].getRight()) // Look Left
                q.add(new int[]{coords[0], coords[1]-1}); // Add Left

            steps++; // We've just taken 1 step.

            // Print the step if requested.
            if (printFrequency > 0 && steps % printFrequency == 0) {
                System.out.println();
                unverifiedPrintMaze();
                System.out.println();
            }
        }
    }

    // Witten by fulle637, checked by deace003
    // Description: Checks if a maze has incorrect dimensions. If fullTest is true, also checks for conflicting rows, or null cells (slower).
    //      These checks are skipped if fullTest is false (faster).
    //      A custom exception should probably be used in this case. However, this requires an additional file
    //      which is not possible for this submission. Therefore, RuntimeException is used instead.
    public void verify(boolean fullTest) {
        // Check for incorrect sizes.
        if (maze.length > 20) throw new RuntimeException("Invalid Maze Size: Maze taller than 20 rows.");
        if (maze.length < 5)  throw new RuntimeException("Invalid Maze Size: Maze shorter than 5 rows.");
        if (maze[0].length > 20) throw new RuntimeException("Invalid Maze Size: Maze wider than 20 columns.");
        if (maze[0].length < 5)  throw new RuntimeException("Invalid Maze Size: Maze skinnier than 5 columns.");

        // Check for conflicting row lengths and null cells if requested.
        if (fullTest) {
            for (Cell[] cells : maze) {
                if (cells.length != maze[0].length) throw new RuntimeException("Invalid Maze: Conflicting row lengths.");
                for (Cell cell : cells) {
                    if (cell == null) throw new RuntimeException("Invalid Maze: No null cells allowed");
                }
            }
        }
    }

    // Witten by fulle637, checked by deace003
    // Description: Default verify() function, fullTest = true.
    public void verify() {
        verify(true);
    }

    // Written by fulle637, chedked by deace003
    // Description: Tests the functionality of MyMaze
    public static void main(String[] args){
        MyMaze test;
        System.out.println("(Enter zero to specify no extra print statements.)");
        int printFrequency = getAns("print frequency", 0, 100);

        // Big Maze Test
        System.out.println("\n==================================================[ 20x20 Maze ]==================================================\n");
        test = makeMaze(20, 20);
        test.unverifiedSolveMaze(printFrequency);
        test.unverifiedPrintMaze();
        System.out.println("Steps: "+test.getSteps());
        int maxSteps = test.getSteps();

        // Mass Maze Test
        for (int i = 5; i <= 20; i++) {
            System.out.println("\n\n\n==================================================[ "+i+"x"+(25-i)+" Maze ]==================================================\n");
            test = makeMaze(i, 25-i);
            test.unverifiedSolveMaze(printFrequency);
            test.unverifiedPrintMaze();
            System.out.println("Steps: "+test.getSteps());
            if (test.getSteps() > maxSteps) {
                maxSteps = test.getSteps();
            }
        }
        System.out.println("\n\nMax Steps: "+maxSteps);
    }
}
