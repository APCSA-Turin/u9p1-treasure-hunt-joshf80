package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    // Initializes saved size for grid, and calls for initialization and game loop
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
            clearScreen(); // Clear the screen at the beggining of the while loop

            // Display game state
            System.out.println("Lives: " + player.getLives());
            System.out.println("Treasures collected: " + player.getTreasureCount() + "/" + numTreasures);
            grid.display();

            // Check win/lose conditions
            if (player.getLives() <= 0) {
                grid.gameover();
                break;
            }
            if (player.getWin()) {
                grid.win();
                break;
            }

            // Get player input
            System.out.print("Enter direction (w,a,s,d): ");
            String direction = scanner.nextLine().toLowerCase();

            // Validate move
            if (!player.isValid(size, direction)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            // Get target position
            int targetX = player.getX();
            int targetY = player.getY();

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
            if (targetX >= 0 && targetX < size && targetY >= 0 && targetY < size) {
                int targetRow = size - 1 - targetY;
                int targetCol = targetX;
                Sprite target = grid.getGrid()[targetRow][targetCol];
                
                // Interact with target
                player.interact(size, direction, numTreasures, target);
                
                // If target was a Treasure or Trophy, replace it with a Dot
                if (target instanceof Treasure || target instanceof Trophy) {
                    grid.getGrid()[targetRow][targetCol] = new Dot(targetCol, size - 1 - targetRow);
                }
            }

            // Move player
            grid.placeSprite(player, direction);
        }
        scanner.close();
    }

    // Initialized game objects
    public void initialize() {
        // Create grid
        grid = new Grid(size);

        // Create player at (0,0)
        player = new Player(0, 0);
        grid.placeSprite(player);

        // Create enemies
        enemies = new Enemy[2];
        enemies[0] = new Enemy(7, 2);
        enemies[1] = new Enemy(5, 4);
        for (Enemy enemy : enemies) {
            grid.placeSprite(enemy);
        }

        // Create treasures
        treasures = new Treasure[2];
        treasures[0] = new Treasure(1, 2);
        treasures[1] = new Treasure(2, 7);
        for (Treasure treasure : treasures) {
            grid.placeSprite(treasure);
        }
        // Create trophy
        trophy = new Trophy(9, 0);
        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        new Game(10); // set size to 10
    }
}