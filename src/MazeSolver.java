/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */
// Sabrina Vohra
import java.sql.Array;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    // Returns solution for a maze using DFS or BFS
    public ArrayList<MazeCell> getSolution() {
        // Should be from start to end cells
        // Creates an ArrayList of the final MazeCells the method must return
        ArrayList<MazeCell> finalCells = new ArrayList<>();
        // Creates a new Stack to keep track of MazeCells
        Stack<MazeCell> s = new Stack<>();
        // Starts from the end of the maze to solve
        MazeCell current = maze.getEndCell();
        // Adds the last cell to the Stack
        s.push(current);
        // Continues while maze hasn't been solved
        while(current != maze.getStartCell()) {
            // Gets the parent of the currentCell to follow the path from the end of the maze to the beginning
            // This works because each MazeCell only has one parent cell
            current = current.getParent();
            // Adds parent cell to Stack
            s.push(current);
        }
        // After maze has been solved, uses Stack to add MazeCells to ArrayList in start to end order
        while(!s.isEmpty()) {
            // Pops MazeCell that is closest to top (most recently added)
            MazeCell toAdd = s.pop();
            // Adds this MazeCell to the ArrayList
            finalCells.add(toAdd);
        }
        // Returns ArrayList of MazeCells in start to end order
        return finalCells;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    // Solves maze using Depth-First Search
    public ArrayList<MazeCell> solveMazeDFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Creates Stack of MazeCells to keep track of which cells to visit
        Stack<MazeCell> s = new Stack<>();
        // Gets beginning of maze
        MazeCell start = maze.getStartCell();
        // Adds beginning of maze to the Stack
        s.push(start);
        // Sets the beginning of the maze as explored so the method cannot return back
        start.setExplored(true);
        // Sets start cell as the current cell
        MazeCell currentC = s.pop();
        // Runs while the maze isn't solved
        while(currentC != maze.getEndCell()) {
            // Finds all neighbors of the current MazeCell
            for(MazeCell e: findNeighbors(currentC)) {
                // Adds the neighbor to the Stack to be explored later
                s.push(e);
                // Sets parent of the neighbor as the current cell
                e.setParent(currentC);
                // Sets neighbor as explored, so it won't be visited again
                e.setExplored(true);
            }
            // Changes which cell is current
            currentC = s.pop();
        }
        // Returns the solution using the getSolution() method
        return getSolution();
    }

    // Method finds all neighbors of a given MazeCell
    public ArrayList<MazeCell> findNeighbors(MazeCell currentC) {
        // Creates an ArrayList to return neighbors of a MazeCell
        ArrayList<MazeCell> theNeighbors = new ArrayList<>();
        // Checks to make sure North-most MazeCell is valid
        if(maze.isValidCell(currentC.getRow() - 1, currentC.getCol())) {
            // Adds valid North MazeCell to ArrayList
            theNeighbors.add(maze.getCell(currentC.getRow() - 1, currentC.getCol()));
        }
        // Checks to make sure East-most MazeCell is valid
        if(maze.isValidCell(currentC.getRow(), currentC.getCol() + 1)) {
            // Adds valid East MazeCell to ArrayList
            theNeighbors.add(maze.getCell(currentC.getRow(), currentC.getCol() + 1));
        }
        // Checks to make sure South-most MazeCell is valid
        if(maze.isValidCell(currentC.getRow() + 1, currentC.getCol())) {
            // Adds valid South MazeCell to ArrayList
            theNeighbors.add(maze.getCell(currentC.getRow() + 1, currentC.getCol()));
        }
        // Checks to make sure West-most MazeCell is valid
        if(maze.isValidCell(currentC.getRow(), currentC.getCol() - 1)) {
            // Adds valid West MazeCell to ArrayList
            theNeighbors.add(maze.getCell(currentC.getRow(), currentC.getCol() - 1));
        }
        // Returns all neighbors that can be explored in BFS or DFS
        return theNeighbors;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    // Solves maze using Breadth-First Search
    public ArrayList<MazeCell> solveMazeBFS() {
        // Creates a Queue
        Queue<MazeCell> theQueue = new LinkedList<MazeCell>();
        MazeCell start = maze.getStartCell();
        theQueue.add(start);
        start.setExplored(true);
        MazeCell currentC = theQueue.remove();
        while(currentC != maze.getEndCell()) {
            for (MazeCell e: findNeighbors(currentC)) {
                theQueue.add(e);
                e.setParent(currentC);
                e.setExplored(true);
            }
            currentC = theQueue.remove();
        }
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
