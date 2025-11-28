
import java.util.Scanner;

public class MainMenu {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void start(Scanner sc) {
        String[] colors = {
            "\u001B[31m", // Red
            "\u001B[33m", // Yellow
            "\u001B[32m", // Green
            "\u001B[36m", // Cyan
            "\u001B[34m", // Blue
            "\u001B[35m" // Magenta
        };
        String RESET = "\u001B[0m";
        String CLEAR_SCREEN = "\033[H\033[2J";

        // ASCII Logo lines (without color codes yet)
        String[] logoLines = {
            "\t\t\t __    __     ______     ______     __   __   ______     __            ______     __         ______     ______     __  __    ",
            "\t\t\t/\\ \"-./  \\   /\\  __ \\   /\\  == \\   /\\ \\ / /  /\\  ___\\   /\\ \\          /\\  ___\\   /\\ \\       /\\  __ \\   /\\  ___\\   /\\ \\_\\ \\   ",
            "\t\t\t\\ \\ \\-/\\  \\  \\ \\  __ \\  \\ \\  __<   \\ \\ \\'/   \\ \\  __\\   \\ \\ \\____     \\ \\ \\____  \\ \\ \\____  \\ \\  __ \\  \\ \\___  \\  \\ \\  __ \\  ",
            "\t\t\t \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\__|    \\ \\_____\\  \\ \\_____\\     \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\/\\_____\\  \\ \\_\\ \\_\\ ",
            "\t\t\t  \\/_/  \\/_/   \\/_/\\/_/   \\/_/ /_/   \\/_/      \\/_____/   \\/_____/      \\/_____/   \\/_____/   \\/_/\\/_/   \\/_____/   \\/_/\\/_/ "
        };
        String subtitle = " \t\t\t\t\t\t\t\t\t>> A Turn-Queued Project <<";

        // --- SCROLLING EFFECT (reveal logo line by line) ---
        for (int i = 0; i <= logoLines.length; i++) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            // Print leading blank lines to simulate upward scroll
            for (int j = 0; j < logoLines.length - i; j++) {
                System.out.println();
            }

            // Print revealed lines so far
            for (int j = 0; j < i; j++) {
                System.out.println(logoLines[j]);
            }

            // Print subtitle only after full logo is revealed
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
        int choice;
        do {
            System.out.println("\t\t\t\t\t\t\t=========================================");
            System.out.println("\t\t\t\t\t\t\t|   Select Game Mode:                   |");
            System.out.println("\t\t\t\t\t\t\t|     1. Player vs Player               |");
            System.out.println("\t\t\t\t\t\t\t|     2. Player vs AI                   |");
            System.out.println("\t\t\t\t\t\t\t|     3. Arcade Mode                    |");
            System.out.println("\t\t\t\t\t\t\t|     0. Exit                           |");
            System.out.print("\t\t\t\t\t\t\t> ");
            while (!sc.hasNextInt()) {
                System.out.print("     \n\t\t\t\t>>> Invalid input, enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();

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
                    System.out.println("                            \n\t\t\t\t>>> Exiting... Goodbye!\n");
                    System.exit(0); // Use 0 for normal exit
                default:
                    System.out.println("                            \n\t\t\t\t>>> Invalid choice, please try again!\n");
            }
        } while (choice != 0);
    }

    public static void playerVsPlayerMenu(Scanner sc) {
        System.out.println("\n\t>>> PvP Match Starting...\n");

        Character player1 = Player1.select();
        if (player1 == null) {
            return;
        }
        player1.displayIntro();

        System.out.println("\n\t\t\t\tPress ENTER for Player 2 to choose...");
        System.out.print("\t\t\t\t");
        sc.nextLine();
        sc.nextLine();

        Character player2 = Player2.select();
        if (player2 == null) {
            return;
        }
        player2.displayIntro();

        System.out.println("\n\t\t\t\tPress ENTER to begin the battle...");
        System.out.print("\t\t\t\t");
        sc.nextLine();

        clearScreen();
        PlayerMechanics game = new PlayerMechanics(player1, player2);
        game.game();
    }

    public static void playerVsAiMenu(Scanner sc) {
        System.out.println("\n\t\t\t\t>>> PlayerVsAI Match Starting...\n");

        Character player = SelectScreen.select();
        if (player == null) {
            return;
        }
        player.displayIntro();

        Enemy enemy = Enemy.getRandomEnemy();
        enemy.displayIntro();

        System.out.println("\t\t\t\t\t\t\tPress ENTER to begin the battle...");
        System.out.print("\t\t\t\t\t\t\t");
        sc.nextLine();
        sc.nextLine();

        clearScreen();
        GameMechanics game = new GameMechanics(player, enemy);
        game.startMatch();
    }

    public static void arcadeModeMenu(Scanner sc) {
        System.out.println("\n\t\t\t\t>>> Arcade Mode Starting...\n");

        Character player = ArcadeSelect.select();
        if (player == null) {
            return;
        }
        player.displayIntro();

        System.out.println("\t\t\t\tPress ENTER to begin your arcade run...");
        sc.nextLine();

        clearScreen();
        ArcadeMode arcade = new ArcadeMode(player);
        arcade.startArcadeRun();
    }
}
