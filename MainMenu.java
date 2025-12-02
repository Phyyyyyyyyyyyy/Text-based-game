// MainMenu.java
import java.util.Scanner;

public class MainMenu implements MethodsInterface {
    private Scanner sc;
    private int matchWins = 0;
    private int totalArcadeWins = 0;
    private boolean arcadeModeBeat = false;

    public MainMenu(Scanner scanner) {
        this.sc = scanner;
    }

    // ============= Implemented from MethodsInterface =============

    @Override
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    @Override
    public void typeWriter(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println();
    }

    @Override
    public void showStoryWithSkip(String heroName, String[] storyLines, int delay, Object hero) {
        for (String line : storyLines) {
            typeWriter(line, delay);
        }
    }

    @Override
    public void start(Scanner sc) {
        this.sc = sc;

        playSound("OpeningSound.wav");

        String[] colors = {
            "\u001B[31m", // Red
            "\u001B[33m", // Yellow
            "\u001B[32m", // Green
            "\u001B[36m", // Cyan
            "\u001B[34m", // Blue
            "\u001B[35m"  // Magenta
        };
        String RESET = "\u001B[0m";
        String CLEAR_SCREEN = "\033[H\033[2J";

        String[] logoLines = {
            "\t\t\t __    __     ______     ______     __   __   ______     __            ______     __         ______     ______     __  __    ",
            "\t\t\t/\\ \"-./  \\   /\\  __ \\   /\\  == \\   /\\ \\ / /  /\\  ___\\   /\\ \\          /\\  ___\\   /\\ \\       /\\  __ \\   /\\  ___\\   /\\ \\_\\ \\   ",
            "\t\t\t\\ \\ \\-/\\  \\  \\ \\  __ \\  \\ \\  __<   \\ \\ \\'/   \\ \\  __\\   \\ \\ \\____     \\ \\ \\____  \\ \\ \\____  \\ \\  __ \\  \\ \\___  \\  \\ \\  __ \\  ",
            "\t\t\t \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\__|    \\ \\_____\\  \\ \\_____\\     \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\/\\_____\\  \\ \\_\\ \\_\\ ",
            "\t\t\t  \\/_/  \\/_/   \\/_/\\/_/   \\/_/ /_/   \\/_/      \\/_____/   \\/_____/      \\/_____/   \\/_____/   \\/_/\\/_/   \\/_____/   \\/_/\\/_/ "
        };
        String subtitle = " \t\t\t\t\t\t\t\t\t>> A Turn-Queued Project <<";

        // --- SCROLLING EFFECT ---
        for (int i = 0; i <= logoLines.length; i++) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            for (int j = 0; j < logoLines.length - i; j++) {
                System.out.println();
            }

            for (int j = 0; j < i; j++) {
                System.out.println(logoLines[j]);
            }

            if (i == logoLines.length) {
                System.out.println();
                System.out.println(subtitle);
            }

            try {
                Thread.sleep(215);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // --- COLOR-CYCLING ANIMATION ---
        for (int i = 0; i < 10; i++) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            String color = colors[i % colors.length];

            System.out.println();
            System.out.println();
            System.out.println(color + logoLines[0] + RESET);
            System.out.println(color + logoLines[1] + RESET);
            System.out.println(color + logoLines[2] + RESET);
            System.out.println(color + logoLines[3] + RESET);
            System.out.println(color + logoLines[4] + RESET);
            System.out.println();
            System.out.println(subtitle);
            System.out.println();
            System.out.println();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Final static red logo
        clearScreen();
        System.out.println();
        System.out.println();
        for (String line : logoLines) {
            System.out.println("\u001B[31m" + line + RESET);
        }
        System.out.println();
        System.out.println(subtitle);
        System.out.println();
        System.out.println();

        // Main menu loop
        int choice = -1;
        while (choice != 0) {
            System.out.println("\t\t\t\t\t\t\t=========================================");
            System.out.println("\t\t\t\t\t\t\t|   Select Game Mode:                   |");
            System.out.println("\t\t\t\t\t\t\t|     1. Player vs Player               |");
            System.out.println("\t\t\t\t\t\t\t|     2. Player vs AI                   |");
            System.out.println("\t\t\t\t\t\t\t|     3. Arcade Mode                    |");
            System.out.println("\t\t\t\t\t\t\t|     0. Exit                           |");
            System.out.print("\t\t\t\t\t\t\t> ");

            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("                            \n\t\t\t\t>>> Input cannot be empty. Please enter a number (0-3).\n");
                    continue;
                }
                choice = Integer.parseInt(input);

                playSound("InputSound.wav");

                switch (choice) {
                    case 1:
                        playerVsPlayerMenu(sc);
                        break;
                    case 2:
                        playerVsAiMenu(sc);
                        break;
                    case 3:
                        arcadeModeMenu(sc);
                        break;
                    case 0:
                        System.out.println("                            \n\t\t\t\t>>> Exiting... Thank you for playing <3\n");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("                            \n\t\t\t\t>>> Invalid choice, please try again!\n");
                        choice = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("                            \n\t\t\t\t>>> Invalid input! Please enter a number (0-3).\n");
                choice = -1;
            }
        }
    }

    @Override
    public void playerVsPlayerMenu(Scanner sc) {
        clearScreen();
        System.out.println("\n\t>>> PvP Match Starting...\n");

        Character player1 = CharacterSelector.selectHero("Player 1 Choose Hero", true);
        if (player1 == null) return;
        player1.displayIntro();

        System.out.println("\n\t\t\t\tPress ENTER for Player 2 to choose...");
        sc.nextLine(); // Clear buffer
        sc.nextLine(); // Wait for Enter

        Character player2 = CharacterSelector.selectHero("Player 2 Choose Hero", true);
        if (player2 == null) return;
        player2.displayIntro();

        System.out.println("\n\t\t\t\tPress ENTER to begin the battle...");
        sc.nextLine();

        clearScreen();
        PlayerMechanics game = new PlayerMechanics(player1, player2);
        game.game();
    }

    @Override
    public void playerVsAiMenu(Scanner sc) {
        clearScreen();
        System.out.println("\n\t\t\t\t>>> Player vs AI Match Starting...\n");

        Character player = CharacterSelector.selectHero("PVSAI", true);
        if (player == null) return;
        player.displayIntro();

        Enemy enemy = Enemy.getRandomEnemy();
        enemy.displayIntro();

        System.out.println("\t\t\t\t\t\t\tPress ENTER to begin the battle...");
        sc.nextLine();
        sc.nextLine();

        clearScreen();
        GameMechanics game = new GameMechanics(player, enemy);
        game.startMatch();
    }

    @Override
    public void arcadeModeMenu(Scanner sc) {
        clearScreen();
        System.out.println("\n\t\t\t\t>>> Arcade Mode Starting...\n");

        Character player = CharacterSelector.selectHero("Arcade Select", true);
        if (player == null) return;
        player.displayIntro();

        System.out.println("\t\t\t\tPress ENTER to begin your arcade run...");
        sc.nextLine();
        sc.nextLine();

        clearScreen();
        ArcadeMode arcade = new ArcadeMode(player);
        arcade.startArcadeRun();
    }

    @Override
    public void startArcadeRun() {
        if (sc != null) {
            arcadeModeMenu(sc);
        }
    }

    // ============= Getters =============
    @Override
    public boolean isArcadeModeBeat() {
        return arcadeModeBeat;
    }

    @Override
    public int getMatchWins() {
        return matchWins;
    }

    @Override
    public int getTotalArcadeWins() {
        return totalArcadeWins;
    }
}
