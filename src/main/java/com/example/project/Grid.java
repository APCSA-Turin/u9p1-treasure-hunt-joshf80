package com.example.project;

/**
 * Represents the game grid that contains all sprites and manages positions
 */
public class Grid {
    private Sprite[][] grid;
    private int size;

    // Constructor for Grid class
    public Grid(int size) {
        this.size = size;
        this.grid = new Sprite[size][size];
        
        // Initialize grid with all Dot objects
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Dot(j, size - 1 - i);
            }
        }
    }

    // returns grid
    public Sprite[][] getGrid() { return grid; }

    // Places a sprite at its current position
    public void placeSprite(Sprite s) {
        int row = size - 1 - s.getY(); //Conversion Cartesian plane -> Row Col form
        int col = s.getX();
        if (row >= 0 && row < size && col >= 0 && col < size) {
            grid[row][col] = s;
        }
    }

    // Places a sprite in a new position based on direction
    public void placeSprite(Sprite s, String direction) {
        // Remove sprite from current position
        int currentRow = size - 1 - s.getY();
        int currentCol = s.getX();
        if (currentRow >= 0 && currentRow < size && currentCol >= 0 && currentCol < size) {
            grid[currentRow][currentCol] = new Dot(currentCol, size - 1 - currentRow);
        }

        // Place sprite in new position
        s.move(direction);
        int newRow = size - 1 - s.getY();
        int newCol = s.getX();
        if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
            grid[newRow][newCol] = s;
        }
    }

    // Displays the grid
    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Identify each sprite in the Grid, and print accordingly
                if (grid[i][j] instanceof Player) { 
                    System.out.print("🦄");
                } else if (grid[i][j] instanceof Enemy) {
                    System.out.print("🦂");
                } else if (grid[i][j] instanceof Treasure) {
                    System.out.print("🌈");
                } else if (grid[i][j] instanceof Trophy) {
                    System.out.print("🏆");
                } else {
                    System.out.print("⬜");
                }
            }
            System.out.println();
        }
    }

    // Displays the game over message
    public void gameover() {
        System.out.println("Game Over! You ran out of lives.");
    }

    // Displays win message
    public void win() {
        System.out.println("Congratulations! You won the game!");
    }
}