package com.example.maze.models;

import java.util.*;

public class Maze {
    private int width, height;
    private Cell[][] grid;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }
        generateMaze();
    }
    private void generateMaze() {
        Stack<Cell> stack = new Stack<>();
        Cell current = grid[0][0];
        current.setVisited(true);
        int visitedCells = 1;

        while (visitedCells < width * height) {
            List<Cell> neighbors = getUnvisitedNeighbors(current);
            if (!neighbors.isEmpty()) {
                Cell next = neighbors.get(new Random().nextInt(neighbors.size()));
                removeWalls(current, next);
                stack.push(current);
                current = next;
                current.setVisited(true);
                visitedCells++;
            } else {
                current = stack.pop();
            }
        }

    }

    private List<Cell> getUnvisitedNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int row = cell.getRow();
        int col = cell.getCol();

        if (row > 0 && !grid[row - 1][col].isVisited()) neighbors.add(grid[row - 1][col]); // top
        if (row < height - 1 && !grid[row + 1][col].isVisited()) neighbors.add(grid[row + 1][col]); // bottom
        if (col > 0 && !grid[row][col - 1].isVisited()) neighbors.add(grid[row][col - 1]); // left
        if (col < width - 1 && !grid[row][col + 1].isVisited()) neighbors.add(grid[row][col + 1]); // right

        return neighbors;
    }

    private void removeWalls(Cell current, Cell next) {
        if (current.getCol() == next.getCol()) {
            if (current.getRow() < next.getRow()) {
                current.setBottomWall(false);
                next.setTopWall(false);
            } else {
                current.setTopWall(false);
                next.setBottomWall(false);
            }
        } else {
            if (current.getCol() < next.getCol()) {
                current.setRightWall(false);
                next.setLeftWall(false);
            } else {
                current.setLeftWall(false);
                next.setRightWall(false);
            }
        }
    }

    private List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int row = cell.getRow();
        int col = cell.getCol();
        
        // Check each direction and ensure the wall is not blocking the path
        if (row > 0 && !grid[row - 1][col].isVisited() && !cell.isTopWall()) neighbors.add(grid[row - 1][col]); // top
        if (row < height - 1 && !grid[row + 1][col].isVisited() && !cell.isBottomWall()) neighbors.add(grid[row + 1][col]); // bottom
        if (col > 0 && !grid[row][col - 1].isVisited() && !cell.isLeftWall()) neighbors.add(grid[row][col - 1]); // left
        if (col < width - 1 && !grid[row][col + 1].isVisited() && !cell.isRightWall()) neighbors.add(grid[row][col + 1]); // right

        return neighbors;
    }

    public List<Cell> solve() {
        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> path = new HashMap<>();
        Cell start = grid[0][0];
        Cell end = grid[height - 1][width - 1];
        queue.add(start);

        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                grid[i][j].setVisited(false);
            }
        }
        start.setVisited(true);
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current == end) break;

            for (Cell neighbor : getNeighbors(current)) {
                if (!neighbor.isVisited()) {
                    neighbor.setVisited(true);
                    path.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<Cell> solution = new ArrayList<>();
        for (Cell at = end; at != null; at = path.get(at)) {
            solution.add(at);
        }
        Collections.reverse(solution);
        return solution;
    }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Cell[][] getGrid() {
        return grid;
    }
}