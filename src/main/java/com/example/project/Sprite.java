package com.example.project;

//Base class for all game objects
//Manages x,y coordinates and provides basic movement functionality
public class Sprite {
    private int x, y;

    // Constructor - Initializes the sprite's position with given coordinates
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Returns the current x position
    public int getX() {
        return x;
    }

    //Returns the current y position
    public int getY() {
        return y;
    }

    //Updates the x position to the given value
    public void setX(int x) {
        this.x = x;
    }

    //Updates the y position to the given value
    public void setY(int y) {
        this.y = y;
    }

    //Converts the x,y values to a string representation
    public String getCoords() {
        return "(" + x + "," + y + ")";
    }

    //Converts Cartesian coordinates to grid row/column format
    public String getRowCol(int size) {
        int row = size - 1 - y;
        int col = x;
        return "[" + row + "][" + col + "]";
    }

    //Moves the sprite in the specified direction
    //Updates x,y coordinates based on movement direction
    public void move(String direction) {

        if (direction.toLowerCase() == "w") { // forwards
            y++;
        } else if(direction.toLowerCase() == "s") { // backwards
            y--;
        } else if(direction.toLowerCase() == "a") { // left
            x--;
        } else if(direction.toLowerCase() == "d") { // right
            x++;
        }
    }

    //Default interaction method
    //Can be overridden by subclasses for specific behavior
    public void interact() {
        // Default behavior (can be overridden by subclasses)
    }
}
