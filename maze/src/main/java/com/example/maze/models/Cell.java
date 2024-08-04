package com.example.maze.models;

public class Cell {
    private int row;
    private int col;
    private boolean topWall;
    private boolean bottomWall;
    private boolean leftWall;
    private boolean rightWall;
    private boolean visited;

    // Constructors, Getters and Setters
    public Cell(int row, int column) {
        this.row = row;
        this.col = column;
        this.topWall = true;
        this.bottomWall = true;
        this.leftWall = true;
        this.rightWall = true;
    }

    // Getters and Setters
    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }
    public int getCol() { return col; }
    public void setCol(int column) { this.col = column; }
    public boolean isTopWall() { return topWall; }
    public void setTopWall(boolean topWall) { this.topWall = topWall; }
    public boolean isBottomWall() { return bottomWall; }
    public void setBottomWall(boolean bottomWall) { this.bottomWall = bottomWall; }
    public boolean isLeftWall() { return leftWall; }
    public void setLeftWall(boolean leftWall) { this.leftWall = leftWall; }
    public boolean isRightWall() { return rightWall; }
    public void setRightWall(boolean rightWall) { this.rightWall = rightWall; }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", col=" + col +
                ", topWall=" + topWall +
                ", bottomWall=" + bottomWall +
                ", leftWall=" + leftWall +
                ", rightWall=" + rightWall +
                ", visited=" + visited +
                '}';
    }
}
