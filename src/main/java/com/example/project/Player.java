package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    // Constructor for Player class - stores player info like lives left and treasures collected
    public Player(int x, int y) {
        super(x, y);
        this.treasureCount = 0;
        this.numLives = 2;
        this.win = false;
    }

    // returns treasures collected
    public int getTreasureCount() {
        return treasureCount;
    }

    // returns lives remaining
    public int getLives() {
        return numLives;
    }

    // Returns win status
    public boolean getWin() {
        return win;
    }

    // calls super method
    @Override
    public void move(String direction) {
        super.move(direction);
    }

    //Interacts with an object at the specified position
    public void interact(int size, String direction, int numTreasures, Object obj) {
        if (obj instanceof Trophy) { 
            if (treasureCount == numTreasures) {
                win = true;
            }
        } else if (obj instanceof Treasure && !(obj instanceof Trophy)) {
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

    // Returns coords with "Player:"
    @Override
    public String getCoords() {
        return "Player:" + super.getCoords();
    }

    // Returns row column formatted coords with "Player:"
    @Override
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }
}