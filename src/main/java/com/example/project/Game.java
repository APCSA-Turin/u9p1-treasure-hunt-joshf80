package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    // Stores size of grid, and calls for initialization and game loop
    public Game(int size) {
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main game loop that handles player input and game logic
    public void play() {
        Scanner scanner = new Scanner(System.in);
        int numTreasures = treasures.length;

        while (true) {
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beginning of the while loop

            // Display game info
            System.out.println("Lives: " + player.getLives());
            System.out.println("Treasures collected: " + player.getTreasureCount() + "/" + numTreasures);
            grid.display();

            // Check if win/lose
            if (player.getLives() <= 0) {
                grid.gameover();
                break;
            }
            if (player.getWin()) {
                grid.win();
                break;
            }

            // Get player input (wasd)
            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.print("Enter direction (w,a,s,d): ");
            String direction = scanner.nextLine().toLowerCase(); // disregard caps

            // Validate move (within bounds)
            if (!player.isValid(size, direction)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            // Get position of target (object in the direction player moving to)
            int targetX = player.getX();
            int targetY = player.getY();

            // get position by using positon of player
            if (direction.equals("w")) {
                targetY++;
            } else if(direction.equals("s")) {
                targetY--;
            } else if(direction.equals("a")) {
                targetX--;
            } else if(direction.equals("d")) {
                targetX++;
            }

            // Check for interactions
            if (targetX >= 0 && targetX < size && targetY >= 0 && targetY < size) { // if target sprite is within bounds

                // converting targets x, y coords to row col
                int targetRow = size - 1 - targetY;
                int targetCol = targetX;
                Sprite target = grid.getGrid()[targetRow][targetCol];

                if (target instanceof Trophy && treasures.length != player.getTreasureCount()) { // Checks if target sprite is trophy, and if not enough treasures collected
                    continue; // skip current iteration of game loop, (skips going into trophy)
                }

                // Player interacts with target sprite
                player.interact(size, direction, numTreasures, target);

                // Update player object to correct position
                player.move(direction);

                // Update grid with sprites in respective positions
                grid.placeSprite(player, direction);
            }
        }
        scanner.close();
    }

    // Initialized game objects
    public void initialize() {
        grid = new Grid(size);

        player = new Player(0, 0);
        grid.placeSprite(player);

        enemies = new Enemy[2];
        enemies[0] = new Enemy(5, 5);
        enemies[1] = new Enemy(7, 8);

        for (Enemy enemy : enemies) {
            grid.placeSprite(enemy);
        }

        treasures = new Treasure[2];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(1, 7);

        for (Treasure treasure : treasures) {
            grid.placeSprite(treasure);
        }

        trophy = new Trophy(9, 9);
        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        new Game(10); // init game
    }
}