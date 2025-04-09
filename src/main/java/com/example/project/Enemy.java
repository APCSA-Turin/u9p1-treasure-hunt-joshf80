package com.example.project;
public class Enemy extends Sprite {
    // Constructor for Enemy class
    public Enemy(int x, int y) {
        super(x, y);
    }

    // Returns the coordinates with "Enemy:" prefix
    @Override
    public String getCoords() {
        return "Enemy:" + super.getCoords();
    }

    // Returns the row and column with "Enemy:" prefix
    @Override
    public String getRowCol(int size) {
        return "Enemy:" + super.getRowCol(size);
    }
}