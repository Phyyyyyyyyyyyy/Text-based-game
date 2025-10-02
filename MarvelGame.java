import java.util.Scanner;

public class MarvelGame {

    // Utility method to clear the console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Start the main menu
        MainMenu.start(sc);

        sc.close();
    }
}
