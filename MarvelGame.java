import java.util.Scanner;

class Character {
    String name;
    int hp;
    int attack;
    int special;

    Character(String name, int hp, int attack, int special) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.special = special;
    }

    void displayStats() {
        System.out.println("\nYou chose: " + name);
        System.out.println("Stats - HP: " + hp + " | Attack: " + attack + " | Special: " + special);
    }
}

public class MarvelGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\t=========================================");
        System.out.println("\t====    MARVEL CLASH! TURN BASED    ====");
        System.out.println("\t=========================================");
        System.out.println("\t| Choose your hero:                   |");
        System.out.println("\t|   1. Iron Man                       |");
        System.out.println("\t|   2. Captain America                |");
        System.out.println("\t|   3. Thor                           |");
        System.out.println("\t|   4. Spider-Man                     |");
        System.out.println("\t|   5. Hulk                           |");
        System.out.println("\t|                                     |");
        System.out.println("\t| Enter choice:                       |");
        System.out.print("\t| > ");
        int choice = sc.nextInt();
        System.out.println("\t=========================================");

        Character player;

        switch (choice) {
            case 1:
                player = new Character("Iron Man", 100, 15, 25);
                break;
            case 2:
                player = new Character("Captain America", 120, 12, 20);
                break;
            case 3:
                player = new Character("Thor", 130, 18, 30);
                break;
            case 4:
                player = new Character("Spider-Man", 90, 14, 22);
                break;
            case 5:
                player = new Character("Hulk", 150, 20, 28);
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Iron Man.");
                player = new Character("Iron Man", 100, 15, 25);
        }

        player.displayStats();

        sc.close();
    }
}
