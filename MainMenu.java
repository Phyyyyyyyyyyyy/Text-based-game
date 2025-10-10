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
                    break;
                default:
                    System.out.println("\n\t>>> Invalid choice, please try again!\n");
            }
        } while (choice != 3);
    }

    public static void playerVsPlayerMenu(Scanner sc) {
    System.out.println("\n\t>>> PvP Match Starting...\n");

    Character player1 = Player1.select();

    System.out.println("\nPress ENTER for Player 2 to choose...");
    sc.nextLine(); // clear newline
    sc.nextLine(); // wait for Enter 


    Character player2 = Player2.select();

    System.out.println("\nPress ENTER to begin the battle...");
    sc.nextLine();
   

    MarvelGame.clearScreen();
    PlayerMechanics game = new PlayerMechanics(player1, player2);
    game.game();
}

    public static void playerVsAiMenu(Scanner sc) {
        System.out.println("\n\t>>> PvAI Match Starting...\n");

        Character player = SelectScreen.select();
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
