In this project, I was assigned the task of creating a maze, and then optimizing the way in which the maze was solved. We were given a maximum number of "moves" allowed to traverse in the series of mazes, which was 500. Through the use of algorithms, stacks, and array traversal, I was able to consistently score below that benchmark. 

How to compile and run the program:
 1. To compile and run, open a command prompt in the directory with the .java files (or navigate there with cd commands).
 2. You can compile the code directly using "javac MyMaze.java", then run it with "java MyMaze" to see the output from the built-in tests which we used to verify our code. However, it may be more helpful to use some test cases by including a class with them in the same directory as MyMaze.java. Compile and run your test cases using "javac <TestClass>.java", then "java <TestClass>".

Assumptions:
I assume that you have a usable command line and a working Java compiler and interpreter on your computer.
I also assume that the user is in the right directory when running the program.
I assume that the user can follow simple instructions such as "Enter a row size between 5 and 20 (inclusive)".

Additional features:
 - isValidMove(Cell[][] maze, int nextRow,int nextCol) Helper function that checks if a move is legal.
 - Accessor methods that allow you to get the start, end, and number of steps taken to solve the maze, each in O(1) time.
 - getAns(getAns(String name, int min, int max) & getAns(String name, int min, int max, Scanner s) ask the user for information and handle invalid inputs.
 - unvisit() & setAllVisited(boolean visited) which change the visit parameter on all cells.
 - verify() & verify(fullTest) check if a maze has incorrect dimensions. verify(true) also checks for conflicting row lengths and null cells.
 - printMaze() and solveMaze() first verify their mazes. This check is skipped if unverifiedPrintMaze() or unverifiedSolveMaze(int printFrequency) is used.
 - unverifiedSolveMaze(int printFrequency) allows periodic progress updates every printFrequency lines. Set printFrequency to 0 to turn off this feature.
 - main(String[] args) which tests the code on many mazes of different sizes.

Any known bugs or defects in the program: 
- makeMaze() could be optimized to avoid checking the same direction multiple times and other methods have slight inefficencies.
