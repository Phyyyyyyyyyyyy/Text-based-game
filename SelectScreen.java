import java.util.Scanner;

public class SelectScreen {
    static Scanner sc = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } 

    public static Character select() {
        Character player = null;
        int choice;

        while (player == null) {
            clearScreen();
            System.out.println("\t=========================================");
            System.out.println("\t====    MARVEL CLASH! TURN BASED    ====");
            System.out.println("\t=========================================");
            System.out.println("\t|   Choose your hero:                   |");
            System.out.println("\t|     1. Iron Man                       |");
            System.out.println("\t|     2. Captain America                |");
            System.out.println("\t|     3. Thor                           |");
            System.out.println("\t|     4. Spider-Man                     |");
            System.out.println("\t|     5. Hulk                           |");
            System.out.println("\t|     6. Black Widow                    |");
            System.out.println("\t|     7. Ant-Man                        |");
            System.out.println("\t|     8. The Falcon                     |");
            System.out.println("\t|     Enter choice:                     |");
            System.out.print("\t > ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                  clearScreen(); 
                switch (choice) {
                    case 1:
                        player = new Character("Iron Man", 110, 110, 20, "Repulsor blast - deals 30 damage ", "Unibeam- deals 20 damage ", "Rocket Barrage - deals 50 damage", 30, 20, 50 , 30, 20, 50, 100);
                        break;
                    case 2:
                        player = new Character("Captain America", 120, 120, 12, "Shield throw! - Deals 25 damage", "Shield Bash! - deals 12 damage", "Inspire - Heals 20 HP ", 25, 12, 15, 25, 12,15, 100);
                        break;
                    case 3:
                        player = new Character("Thor", 130, 130, 18, "Lightning Blast! - deals 30 damage", "Mjolnir throw! - deals 20 damage", "God of Thunder - doubles attack for 3 turns", 30, 20, 20, 30, 20, 20, 100);
                        break;
                    case 4:
                        player = new Character("Spider-Man", 90, 90, 14, "Spidey Swing! - avoids damage", "Web Shot! - deals 15 damage", "Spidey-sense - doubles attack damage", 20, 15, 20, 0, 15, 0,100);
                        break;
                    case 5:
                        player = new Character("Hulk", 150, 150, 20, "Hulk Smash! - Deals 30 damage", "Thunderclap - Deals 25 damage", "Hulk Rage - Doubles attack damage", 30, 25, 20, 30, 25, 0,100);              
                        break;                
                    case 6: 
                        player = new Character("Black Widow", 100, 100, 10, "Stealth - invisible for 1 turn", "Widow's Kick! - 20 damage", "Espionage - 50 damage", 30, 20, 50, 0, 20, 50, 100);
                        break;
                    case 7: 
                        player = new Character("Ant-Man", 100, 100, 20, "Pym Particle punch! - deals 20 damage", "Shrink - dodges next attack", "Giant-Man - deals double damage in the next 2 turns.", 20, 30, 25, 20, 0, 20, 100);
                        break;
                    case 8: 
                        player = new Character("The Falcon", 150, 150, 10, "Flight - avoids damage", "Redwing Strike! - deals 20 damage" ,"Tactical Barrage - deals 30 damage", 20, 20 , 30, 0, 20, 30, 100);
                        break;
                    case 69: 
                        player = new Character("Jan Clark", 150, 150, 20, "Lisora aning OOP uy! - deals 20 damage", "Eternal Drip! - deals 30 damage",  "Lisora aning DSA uy! - deals 40 damage", 20, 30 , 40, 20, 30, 40, 100);
                        break;
                    case 70:
                        player = new Character("John Micoh", 150, 150, 20, "CIT lang ya! - deals 20 damage", "Lahus ni ug Cambuntan ya? -  deals 40 damage", "Kapoyag tuon oy! - deals 50 damage", 20, 30, 35, 20, 40 , 50, 100);          
                        break;
                    case 7355608: 
                        player = new Character("Thanos", 500, 500, 200, "Power Stone Blast - deals 200 damage ", "Snap - Eliminates enemy in an instant", "Power Stone Punch! - deals 100 damage", 30, 50, 30, 200, 999, 100, 100);
                        break;
                    default:
                        
                        System.out.println("Invalid choice! Please select a valid hero number.\n");                ///characteuryghjasgfv hjgasasvghjfk
                        break;
                }
            } else {
                System.out.println("Invalid input! Please enter a number.\n");
                sc.next();
                
                 // clear invalid input
            }
        }
        return player;
    }
}