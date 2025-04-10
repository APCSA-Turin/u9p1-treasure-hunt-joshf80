package com.example.project;

// Represents the game grid
public class Grid {
    private Sprite[][] grid;
    private int size;

    // constructor for grid class
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
        // Get current position before move
        int oldRow = size - 1 - getOldY(s, direction);
        int oldCol = getOldX(s, direction);
        
        // Replaces old pos with Dot
        grid[oldRow][oldCol] = new Dot(oldCol, size - 1 - oldRow);
        
        // Place sprite at new pos
        int newRow = size - 1 - s.getY();
        int newCol = s.getX();
        grid[newRow][newCol] = s;
    }

    // helper methods for the placeSprite method, used to convert previous position to Dot objects
    private int getOldX(Sprite s, String direction) {
        switch(direction) {
            case "a": return s.getX() + 1;
            case "d": return s.getX() - 1;
            default: return s.getX();
        }
    }

    private int getOldY(Sprite s, String direction) {
        switch(direction) {
            case "w": return s.getY() - 1;
            case "s": return s.getY() + 1;
            default: return s.getY();
        }
    }
        
    // Displays the 2d grid array
    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Identify each sprite in the Grid, and print accordingly
                if (grid[i][j] instanceof Player) { 
                    System.out.print("🦄");
                } else if (grid[i][j] instanceof Enemy) {
                    System.out.print("🦂");
                } else if (grid[i][j] instanceof Trophy) { // trophy must come before treasure, since it is a child of treasure and would be true
                    System.out.print("🏆");
                } else if (grid[i][j] instanceof Treasure) {
                    System.out.print("💰");
                } else if (grid[i][j] instanceof Dot) {
                    System.out.print("⬜");
                }
            }
            System.out.println(); // Move to next row after printing full row
        }
    }

    // Displays game over message
    public void gameover() {
        System.out.println("Game Over! You ran out of lives.");
    }

    // Displays win
    public void win() {
        System.out.println("Congratulations! You won the game!");
    }
}