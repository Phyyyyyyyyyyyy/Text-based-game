import java.util.Scanner;

public class MainMenu {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void start(Scanner sc) {
        int choice;
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";

        System.out.println(RED + " __    __     ______     ______     __   __   ______     __            ______     __         ______     ______     __  __    " + RESET);
        System.out.println(RED + "/\\ \"-./  \\   /\\  __ \\   /\\  == \\   /\\ \\ / /  /\\  ___\\   /\\ \\          /\\  ___\\   /\\ \\       /\\  __ \\   /\\  ___\\   /\\ \\_\\ \\   " + RESET);
        System.out.println(RED + "\\ \\ \\-/\\  \\  \\ \\  __ \\  \\ \\  __<   \\ \\ \\'/   \\ \\  __\\   \\ \\ \\____     \\ \\ \\____  \\ \\ \\____  \\ \\  __ \\  \\ \\___  \\  \\ \\  __ \\  " + RESET);
        System.out.println(RED + " \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\__|    \\ \\_____\\  \\ \\_____\\     \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\/\\_____\\  \\ \\_\\ \\_\\ " + RESET);
        System.out.println(RED + "  \\/_/  \\/_/   \\/_/\\/_/   \\/_/ /_/   \\/_/      \\/_____/   \\/_____/      \\/_____/   \\/_____/   \\/_/\\/_/   \\/_____/   \\/_/\\/_/ " + RESET);                                                                                                                 
        System.out.println();
        System.out.println(); 
        System.out.println();
        System.out.println();
        System.out.println();
        

        do {
        
            System.out.println(                 "\t\t\t\t=========================================");
            System.out.println(                 "\t\t\t\t|   Select Game Mode:                   |");
            System.out.println(                 "\t\t\t\t|     1. Player vs Player               |");
            System.out.println(                 "\t\t\t\t|     2. Player vs AI                   |");
            System.out.println(                 "\t\t\t\t|     3. Exit                           |");
            System.out.print(                   "\t\t\t\t > ");
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
                    System.out.println("                            \n\t\t\t\t>>> Exiting... Goodbye!\n");
                    System.exit(1);
                default:
                    System.out.println("                            \n\t\t\t\t>>> Invalid choice, please try again!\n");
            }
        } while (choice != 3);
    }

    public static void playerVsPlayerMenu(Scanner sc) {
        System.out.println("\n\t>>> PvP Match Starting...\n");

        Character player1 = Player1.select();
        if (player1 == null) return; 

        System.out.println("\n\t\t\t\tPress ENTER for Player 2 to choose...");
        sc.nextLine();
        sc.nextLine();

        Character player2 = Player2.select();
        if (player2 == null) return; 

        System.out.println("\n\t\t\t\tPress ENTER to begin the battle...");
        sc.nextLine();

        clearScreen();
        PlayerMechanics game = new PlayerMechanics(player1, player2);
        game.game();
    }

    public static void playerVsAiMenu(Scanner sc) {
        System.out.println("\n\t\t\t\t>>> PlayerVsAI Match Starting...\n");

        Character player = SelectScreen.select();
        if (player == null) return; 
        player.displayIntro();

        Enemy enemy = Enemy.getRandomEnemy();   
        enemy.displayIntro();

        System.out.println("\t\t\t\tPress ENTER to begin the battle...");
        sc.nextLine();
        sc.nextLine();

        clearScreen();
        GameMechanics game = new GameMechanics(player, enemy);
        game.game();
    }
}
