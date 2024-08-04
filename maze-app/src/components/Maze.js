import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Grid } from '@mui/material';
import './Maze.css';

const Maze = () => {
    const [maze, setMaze] = useState([]);
    const [solution, setSolution] = useState([]);
    const [width, setWidth] = useState(0);
    const [height, setHeight] = useState(0);
    const [mazeGenerated, setMazeGenerated] = useState(false);

    const handleWidthChange = (e) => {
        setWidth(Number(e.target.value));
        resetMazeAndSolution();
    };

    const handleHeightChange = (e) => {
        setHeight(Number(e.target.value));
        resetMazeAndSolution();
    };

    const resetMazeAndSolution = () => {
        setMaze([]);
        setSolution([]);
        setMazeGenerated(false);
    };

    const resetAll = () => {
        setHeight(0);
        setWidth(0);
        setMaze([]);
        setSolution([]);
        setMazeGenerated(false);
    };

    const generateMaze = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/maze/generate', {
                params: { width, height}
            });
            setMaze(response.data.grid);
            setWidth(response.data.width);
            setHeight(response.data.height);
            setMazeGenerated(true);
            setSolution([]);
        } catch (error) {
            console.error('Error generating maze', error);
        }
    };

    const solveMaze = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/maze/solve');
            setSolution(response.data);

        } catch (error) {
            console.error('Error solving maze', error);
        }
    };

    const mazeStyle = {
        display: 'grid',
        gridTemplateColumns: `repeat(${width}, 20px)`,
        gridTemplateRows: `repeat(${height}, 20px)`
    };


    return (
        <div className="maze-container">
            <div>
                <TextField
                    label="Height"
                    type="number"
                    value={height}
                    onChange={handleHeightChange}
                    variant="standard"
                    InputProps={{ inputProps: { min: 1 } }}
                />
            </div>
            <div style={{marginTop: '15px'}}>
                <TextField
                    label="Width"
                    type="number"
                    value={width}
                    onChange={handleWidthChange}
                    variant="standard"
                    InputProps={{ inputProps: { min: 1 } }}
                    
                />
            </div>
            <div className="controls">
                <Button variant="contained" onClick={generateMaze}>Generate Maze</Button>
                <Button variant="outlined" onClick={solveMaze} disabled={!mazeGenerated} style={{marginLeft: '5px'}}>Solve Maze</Button>
                <Button variant="text" onClick={resetAll} style={{marginLeft: '20px'}}>Reset</Button>
            </div>
            <div className="maze" style={mazeStyle}>
                {maze.map((row, rowIndex) => (
                    <div className="row" key={rowIndex}>
                        {row.map((cell, colIndex) => (
                            <div
                                className={`cell 
                                    ${cell.topWall ? 'top-wall' : ''} 
                                    ${cell.bottomWall ? 'bottom-wall' : ''} 
                                    ${cell.leftWall ? 'left-wall' : ''} 
                                    ${cell.rightWall ? 'right-wall' : ''} 
                                    ${solution.some(s => s.row === cell.row && s.col === cell.col) ? 'solution' : ''}
                                `}
                                key={colIndex}
                            ></div>
                        ))}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Maze;


