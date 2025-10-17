import java.util.Scanner;

public class MainMenu {
    public static void start(Scanner sc) {
        int choice;

        do {
            System.out.println("\t=========================================");
            System.out.println("\t====       MARVEL CLASH! MENU       ====");
            System.out.println("\t=========================================");
            System.out.println("\t|   Select Game Mode:                  |");
            System.out.println("\t|     1. Player vs Player              |");
            System.out.println("\t|     2. Player vs AI                  |");
            System.out.println("\t|     3. Exit                          |");
            System.out.print("\t > ");
            while (!sc.hasNextInt()) {
                System.out.print("\n\t>>> Invalid input, enter a number: ");
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
                    System.out.println("\n\t>>> Exiting... Goodbye!\n");
                    System.exit(1);
                default:
                    System.out.println("\n\t>>> Invalid choice, please try again!\n");
            }
        } while (choice != 3);
    }

    public static void playerVsPlayerMenu(Scanner sc) {
        System.out.println("\n\t>>> PvP Match Starting...\n");

        Character player1 = Player1.select();
        if (player1 == null) return; // back to main menu

        System.out.println("\nPress ENTER for Player 2 to choose...");
        sc.nextLine();
        sc.nextLine();

        Character player2 = Player2.select();
        if (player2 == null) return; // back to main menu

        System.out.println("\nPress ENTER to begin the battle...");
        sc.nextLine();

        MarvelGame.clearScreen();
        PlayerMechanics game = new PlayerMechanics(player1, player2);
        game.game();
    }

    public static void playerVsAiMenu(Scanner sc) {
        System.out.println("\n\t>>> PlayerVsAI Match Starting...\n");

        Character player = SelectScreen.select();
        if (player == null) return; 
        player.displayIntro();

        Enemy enemy = Enemy.getRandomEnemy();   
        enemy.displayIntro();

        System.out.println("Press ENTER to begin the battle...");
        sc.nextLine();
        sc.nextLine();

        MarvelGame.clearScreen();
        GameMechanics game = new GameMechanics(player, enemy);
        game.game();
    }
}
