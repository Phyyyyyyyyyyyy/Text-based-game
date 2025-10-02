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
            System.out.println("\t|     9. Back                           |");
            System.out.println("\t|     Enter choice:                     |");
            System.out.print("\t > ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                  clearScreen(); 
                switch (choice) {
                    case 1:
                        player = new Character("Iron Man", 110, 110, 20, "Repulsor blast - deals 30 damage ", "Unibeam- deals 20 damage ", "Rocket Barrage - deals 50 damage", 30, 20, 50 , 30, 20, 50, 100);
                        System.out.println("\nIron Man: Genius billionaire Tony Stark built his armored suit after a near-death experience.");
                        System.out.println("He uses advanced technology to protect the world as Iron Man.");
                        System.out.println("Despite his arrogance, his heart pushes him to fight for others.\n");
                        break;
                    case 2:
                        player = new Character("Captain America", 120, 120, 12, "Shield throw! - Deals 25 damage", "Shield Bash! - deals 12 damage", "Inspire - Heals 20 HP ", 25, 12, 15, 25, 12,15, 100);
                        System.out.println("\nCaptain America: Steve Rogers was enhanced to peak strength during WWII.");
                        System.out.println("Armed with his vibranium shield, he defends freedom and justice.");
                        System.out.println("He is the living symbol of courage and hope.\n");

                        break;
                    case 3:
                        player = new Character("Thor", 130, 130, 18, "Lightning Blast! - deals 30 damage", "Mjolnir throw! - deals 20 damage", "God of Thunder - doubles attack for 3 turns", 30, 20, 20, 30, 20, 20, 100);
                        System.out.println("\nThor: The God of Thunder wields Mjolnir to protect the Nine Realms.");
                        System.out.println("He commands storms and possesses incredible strength.");
                        System.out.println("A true warrior who fights for both Asgard and Earth.\n");
                        break;
                    case 4:
                        player = new Character("Spider-Man", 90, 90, 14, "Spidey Swing! - avoids damage", "Web Shot! - deals 15 damage", "Spidey-sense - doubles attack damage", 20, 15, 20, 0, 15, 0,100);
                        System.out.println("\nSpider-Man: Bitten by a radioactive spider, Peter Parker gained amazing powers.");
                        System.out.println("Haunted by Uncle Ben’s words, he lives by 'with great power comes great responsibility.'");
                        System.out.println("He balances life as a hero and a teenager.\n");
                        break;
                    case 5:
                        player = new Character("Hulk", 150, 150, 20, "Hulk Smash! - Deals 30 damage", "Thunderclap - Deals 25 damage", "Hulk Rage - Doubles attack damage", 30, 25, 20, 30, 25, 0,100);        
                        System.out.println("\nHulk: Dr. Bruce Banner transforms into the Hulk when angered.");
                        System.out.println("His unstoppable strength makes him both feared and admired.");
                        System.out.println("He struggles to control the monster within while protecting others.\n");
                        break;                
                    case 6: 
                        player = new Character("Black Widow", 100, 100, 10, "Stealth - invisible for 1 turn", "Widow's Kick! - 20 damage", "Espionage - 50 damage", 30, 20, 50, 0, 20, 50, 100);
                        System.out.println("\nBlack Widow: Natasha Romanoff was trained as a deadly assassin.");
                        System.out.println("Now an Avenger, she seeks redemption for her past.");
                        System.out.println("Her agility and cunning make her a powerful ally.\n");
                        break;
                    case 7: 
                        player = new Character("Ant-Man", 100, 100, 20, "Pym Particle punch! - deals 20 damage", "Shrink - dodges next attack", "Giant-Man - deals double damage in the next 2 turns.", 20, 30, 25, 20, 0, 20, 100);
                        System.out.println("\nAnt-Man: Scott Lang uses Hank Pym’s shrinking technology.");
                        System.out.println("He can shrink to the size of an ant or grow to a giant.");
                        System.out.println("A thief turned hero, he protects those in need.\n");
                        break;
                    case 8: 
                        player = new Character("The Falcon", 150, 150, 10, "Flight - avoids damage", "Redwing Strike! - deals 20 damage" ,"Tactical Barrage - deals 30 damage", 20, 20 , 30, 0, 20, 30, 100);
                        System.out.println("\nThe Falcon: Sam Wilson uses advanced wing technology to soar the skies.");
                        System.out.println("A loyal soldier and hero, he fights with unmatched speed.");
                        System.out.println("His bravery makes him one of the Avengers’ most trusted allies.\n");
                        break;
                    case 9: 
                           MainMenu.start(sc);
                        break;
                    case 69: 
                        player = new Character("Jan Clark", 150, 150, 20, "Lisora aning OOP uy! - deals 20 damage", "Eternal Drip! - deals 30 damage",  "Lisora aning DSA uy! - deals 40 damage", 20, 30 , 40, 20, 30, 40, 100);
                        System.out.println("\nJan Clark: Known for his unstoppable drip and endless energy in class.");
                        System.out.println("He turns even the toughest coding battles into a stage for style.");
                        System.out.println("With wit and humor, he inspires allies to keep fighting strong.\n");
                        break;
                    case 70:
                        player = new Character("John Micoh", 150, 150, 20, "CIT lang ya! - deals 20 damage", "Lahus ni ug Cambuntan ya? -  deals 40 damage", "Kapoyag tuon oy! - deals 50 damage", 20, 30, 35, 20, 40 , 50, 100);          
                        System.out.println("\nJohn Micoh: A laid-back warrior who balances jokes with determination.");
                        System.out.println("He may complain about studying, but when the battle starts, he gives his all.");
                        System.out.println("With raw persistence and sharp comebacks, he pushes through any challenge.\n");
                        break;
                    case 8700:
                         player = new Character("Ethan Manto", 150, 150, 20, "Hollaback Girl!", "Soulja Boy Superman!", "Bye Bye Bye!", 20, 30, 35, 20, 30, 35, 100);
                         System.out.println("\nEthan Manto: A warrior fueled by rhythm and style.");
                         System.out.println("He turns every battle into a stage with iconic moves.");
                         System.out.println("Behind the flair, he fights with loyalty and heart.\n");
                         break;
                    case 7355608: 
                        player = new Character("Thanos", 500, 500, 200, "Power Stone Blast - deals 200 damage ", "Snap - Eliminates enemy in an instant", "Power Stone Punch! - deals 100 damage", 30, 50, 30, 200, 999, 100, 100);
                        System.out.println("\nThanos: The Mad Titan who believes balance is the key to the universe.");
                        System.out.println("Armed with the Infinity Stones, he bends reality with a flick of his hand.");
                        System.out.println("Though feared by many, deep down he just wants some peace and maybe a farm life.\n");
                        break;
                    default:
                        
                        System.out.println("Invalid choice! Please select a valid hero number.\n");                ///characteuryghjasgfv hjgasasvghjfk we love joanna
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
