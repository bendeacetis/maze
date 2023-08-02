•Group member’s names and x500s: Benjamin DeAcetis (deace003), Israel Fuller (fulle637)

•How to compile and run your program
 1. To compile and run, open a command prompt in the directory with the .java files (or navigate there with cd commands).
 2. You can compile the code directly using "javac MyMaze.java", then run it with "java MyMaze" to see the output from the built in tests which we used to verify our code. However, it may be more helpful to use some test cases by including class with them in the same directory as MyMaze.java. Compile and run your test cases using "javac <TestClass>.java", then "java <TestClass>"

•Any assumptions:
I assume that you have a usable command line and a working java compiler and interpreter on your computer.
I also assume that the user is in the right directory when running the program.
I assume that the user can follow simple instructions such as "Enter a row size in between 5 and 20 (inclusive)".

•Contributions of each partner (if applicable)
 - Ben DeAcetis (deace003): Constructor, isValidMove(), makeMaze(), tested all of the code.
 - Israel Fuller(fulle637): printMaze(), solveMaze(), main(), Accessor methods, getAns(), unvist(), setAllVisited(), verify(), unverified function versions, tested all of the code.

•Additional features that your project had (if applicable)
 - isValidMove(Cell[][] maze, int nextRow,int nextCol) Helper function that checks if a move is legal.
 - Accessor methods that allow you to get the start, end, and the number of steps taken to solve the maze, each in O(1) time.
 - getAns(getAns(String name, int min, int max) & getAns(String name, int min, int max, Scanner s) ask the user for information and handle invalid inputs.
 - unvisit() & setAllVisited(boolean visited) which change the visit parameter on all cells.
 - verify() & verify(fullTest) check if a maze has incorrect dimensions. verify(true) also checks for conflicting row lengths and null cells.
 - printMaze() and solveMaze() first verify their mazes. This check is skipped if unverifiedPrintMaze() or unverifiedSolveMaze(int printFrequency) is used.
 - unverifiedSolveMaze(int printFrequency) allows periodic progress updates every printFrequency lines. Set printFrequency to 0 to turn off this feature.
 - main(String[] args) which tests the code on many mazes of different sizes.

•Any known bugs or defects in the program: makeMaze() could be optimized to avoid checking the same direction multiple times and other methods have slight inefficencies.

•Outside Sources: None

•Academic Integrity statement: “I certify that the information contained in this README file is complete and accurate. I have both read and followed the course policies in the ‘Academic Integrity - Course Policy’ section of the course syllabus.”
Signed by Benjamin DeAcetis and Israel Fuller on 12/02/2022