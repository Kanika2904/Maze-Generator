package com.example.maze;

import com.example.maze.models.Cell;
import com.example.maze.models.Maze;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maze")
@CrossOrigin(origins = "http://localhost:3000")
public class MazeController {

    @Autowired
    private MazeService mazeService;

    @GetMapping("/generate")
    public Maze generateMaze(@RequestParam int width, @RequestParam int height) {
        return mazeService.generateMaze(width, height);
    }

    @GetMapping("/solve")
    public List<Cell> solveMaze() {
        return mazeService.solveMaze();
    }
}