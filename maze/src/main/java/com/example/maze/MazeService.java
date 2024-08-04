package com.example.maze;

import com.example.maze.models.Cell;
import com.example.maze.models.Maze;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MazeService {
    private Maze maze;

    public Maze generateMaze(int width, int height) {
        maze = new Maze(width, height);
        System.out.println("Maze generate with h:"+height+"and w:"+width);
        return maze;
    }

    public List<Cell> solveMaze() {
        if (maze == null) {
            throw new IllegalStateException("Maze not generated yet");
        }
        System.out.println("Sol");
        List<Cell> sol= maze.solve();
        for(Cell c: sol) {
            System.out.println(c.toString());
        }
        return sol;
    }
}
