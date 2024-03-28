/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.sql.Array;
import java.util.ArrayList;
import java.util.Stack;

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
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> finalCells = new ArrayList<>();
        Stack<MazeCell> s = new Stack<>();
        MazeCell current = maze.getEndCell();
        while(current != null) {
            s.push(current);
            current = current.getParent();
        }
        for(int i = 0; i < s.size(); i++) {
            MazeCell toAdd = s.pop();
            finalCells.add(toAdd);
            i--;
        }
        return finalCells;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        //ArrayList<MazeCell> finalList = new ArrayList<MazeCell>();
        Stack<MazeCell> s = new Stack<MazeCell>();
        s.push(maze.getStartCell());
        //maze.getStartCell().setExplored(true);
        while(!s.empty()) {
            MazeCell currentCell = s.pop();
            for(MazeCell e: findDFS(currentCell)) {
                s.push(e);
                e.setExplored(true);
                e.setParent(currentCell);
            }
        }
        return getSolution();
    }

    public ArrayList<MazeCell> findDFS(MazeCell currentC) {
        ArrayList<MazeCell> theNeighbors = new ArrayList<>();
        if(maze.isValidCell(currentC.getRow() - 1, currentC.getCol())) {
            theNeighbors.add(maze.getCell(currentC.getRow() - 1, currentC.getCol()));
        }
        if(maze.isValidCell(currentC.getRow(), currentC.getCol() + 1)) {
            theNeighbors.add(maze.getCell(currentC.getRow(), currentC.getCol() + 1));
        }
        if(maze.isValidCell(currentC.getRow() + 1, currentC.getCol())) {
            theNeighbors.add(maze.getCell(currentC.getRow() + 1, currentC.getCol()));
        }
        if(maze.isValidCell(currentC.getRow(), currentC.getCol() - 1)) {
            theNeighbors.add(maze.getCell(currentC.getRow(), currentC.getCol() - 1));
        }
        return theNeighbors;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    public ArrayList<MazeCell> findBFS() {
        return null;
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
