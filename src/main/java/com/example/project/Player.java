package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    // Constructor for Player class
    public Player(int x, int y) {
        super(x, y);
        this.treasureCount = 0;
        this.numLives = 2;
        this.win = false;
    }

    // Gets the number of treasures collected
    public int getTreasureCount() {
        return treasureCount;
    }

    // Gets the number of lives remaining
    public int getLives() {
        return numLives;
    }

    // Returns win status
    public boolean getWin() {
        return win;
    }

    // Moves the player in the specified direction
    @Override
    public void move(String direction) {
        super.move(direction); // Call Sprite class move
    }

    //Interacts with an object at the specified position
    public void interact(int size, String direction, int numTreasures, Object obj) {
        if (obj instanceof Trophy) {
            // Only check win condition, don't modify treasure count
            if (treasureCount >= numTreasures) {
                win = true;
            }
        } else if (obj instanceof Treasure) {
            treasureCount++;
        } else if (obj instanceof Enemy) {
            numLives--;
        }
    }

    // Checks if a move is valid and within grid bounds
    public boolean isValid(int size, String direction) {
        int newX = getX();
        int newY = getY();

        if (direction.equals("w")) {
            newY++;
        } else if(direction.equals("s")) {
            newY--;
        } else if(direction.equals("a")) {
            newX--;
        } else if(direction.equals("d")) {
            newX++;
        }

        return newX >= 0 && newX < size && newY >= 0 && newY < size;
    }

    // Returns coordinates with "Player:" prefix
    @Override
    public String getCoords() {
        return "Player:" + super.getCoords();
    }

    // Returns row column formatted coordinates with "Player:" prefix
    @Override
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }
}



