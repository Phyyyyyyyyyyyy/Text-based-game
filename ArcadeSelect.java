
import java.util.Scanner;

public class ArcadeSelect extends SelectScreen {

    static Scanner sc = new Scanner(System.in);

    public static Character select() {
        Character player = null;
        int choice;

        while (player == null) {
            clearScreen();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t\t\t\t=========================================");
            System.out.println("\t\t\t\t\t\t====           ARCADE MODE           ====");
            System.out.println("\t\t\t\t\t\t=========================================");
            System.out.println("\t\t\t\t\t\t|   Choose your hero:                   |");
            System.out.println("\t\t\t\t\t\t|     1. Iron Man                       |");
            System.out.println("\t\t\t\t\t\t|     2. Captain America                |");
            System.out.println("\t\t\t\t\t\t|     3. Thor                           |");
            System.out.println("\t\t\t\t\t\t|     4. Spider-Man                     |");
            System.out.println("\t\t\t\t\t\t|     5. Hulk                           |");
            System.out.println("\t\t\t\t\t\t|     6. Black Widow                    |");
            System.out.println("\t\t\t\t\t\t|     7. Ant-Man                        |");
            System.out.println("\t\t\t\t\t\t|     8. The Falcon                     |");
            System.out.println("\t\t\t\t\t\t|     9. Back                           |");
            System.out.println("\t\t\t\t\t\t|     Enter choice:                     |");
            System.out.print("\t\t\t\t\t\t> ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                clearScreen();
                switch (choice) {
                    case 1:
                        player = new Character("Iron Man", 110, 110, 20, "Repulsor blast - deals 30 damage ", "Unibeam- deals 20 damage ", "Rocket Barrage - deals 50 damage", 30, 20, 50, 30, 20, 50, 100);
                        showStoryWithSkip("Iron Man", new String[]{
                            "\n\t\t\t\tIron Man: Genius billionaire Tony Stark built his armored suit after a near-death experience.",
                            "\t\t\t\tHe uses advanced technology to protect the world as Iron Man.",
                            "\t\t\t\tDespite his arrogance, his heart pushes him to fight for others.\n"
                        }, 40, player);
                        break;
                    case 2:
                        player = new Character("Captain America", 120, 120, 12, "Shield throw! - Deals 25 damage", "Shield Bash! - Deals 12 damage", "Inspire - Deals 20 HP ", 25, 12, 15, 25, 12, 15, 100);
                        showStoryWithSkip("Captain America", new String[]{
                            "\n\t\t\t\tCaptain America: Steve Rogers was enhanced to peak strength during WWII.",
                            "\t\t\t\tArmed with his vibranium shield, he defends freedom and justice.",
                            "\t\t\t\tHe is the living symbol of courage and hope.\n"
                        }, 40, player);
                        break;
                    case 3:
                        player = new Character("Thor", 130, 130, 18, "Lightning Blast! - Deals 30 damage", "Mjolnir throw! - Deals 20 damage", "God of Thunder - Deals 20 damage", 30, 20, 20, 30, 20, 20, 100);
                        showStoryWithSkip("Thor", new String[]{
                            "\n\t\t\t\tThor: The God of Thunder wields Mjolnir to protect the Nine Realms.",
                            "\t\t\t\tHe commands storms and possesses incredible strength.",
                            "\t\t\t\tA true warrior who fights for both Asgard and Earth.\n"
                        }, 40, player);
                        break;
                    case 4:
                        player = new Character("Spider-Man", 90, 90, 14, "Spidey Swing! - Deals 20 damage", "Web Shot! - Deals 15 damage", "Spidey-sense - Deals 20 damage", 20, 15, 20, 0, 15, 0, 100);
                        showStoryWithSkip("Spider-Man", new String[]{
                            "\n\t\t\t\tSpider-Man: Bitten by a radioactive spider, Peter Parker gained amazing powers.",
                            "\t\t\t\tHaunted by Uncle Ben's words, he lives by 'with great power comes great responsibility.'",
                            "\t\t\t\tHe balances life as a hero and a teenager.\n"
                        }, 40, player);
                        break;
                    case 5:
                        player = new Character("Hulk", 150, 150, 20, "Hulk Smash! - Deals 30 damage", "Thunderclap - Deals 25 damage", "Hulk Rage - Deals 25 damage", 30, 25, 20, 30, 25, 0, 100);
                        showStoryWithSkip("Hulk", new String[]{
                            "\n\t\t\t\tHulk: Dr. Bruce Banner transforms into the Hulk when angered.",
                            "\t\t\t\tHis unstoppable strength makes him both feared and admired.",
                            "\t\t\t\tHe struggles to control the monster within while protecting others.\n"
                        }, 40, player);
                        break;
                    case 6:
                        player = new Character("Black Widow", 100, 100, 10, "Stealth - Deals 30 damage", "Widow's Kick! - 20 damage", "Espionage - 50 damage", 30, 20, 50, 0, 20, 50, 100);
                        showStoryWithSkip("Black Widow", new String[]{
                            "\n\t\t\t\tBlack Widow: Natasha Romanoff was trained as a deadly assassin.",
                            "\t\t\t\tNow an Avenger, she seeks redemption for her past.",
                            "\t\t\t\tHer agility and cunning make her a powerful ally.\n"
                        }, 40, player);
                        break;
                    case 7:

                        player = new Character("Ant-Man", 100, 100, 20, "Pym Particle punch! - Deals 20 damage", "Shrink Punch - Deals 20 damage", "Giant-Man - deals double damage in the next 2 turns.", 20, 30, 25, 20, 0, 20, 100);
                        showStoryWithSkip("Ant-Man", new String[]{
                            "\n\t\t\t\tAnt-Man: Scott Lang uses Hank Pym's shrinking technology.",
                            "\t\t\t\tHe can shrink to the size of an ant or grow to a giant.",
                            "\t\t\t\tA thief turned hero, he protects those in need.\n"
                        }, 40, player);
                        break;
                    case 8:

                        player = new Character("The Falcon", 150, 150, 10, "Flight - avoids damage", "Redwing Strike! - deals 20 damage", "Tactical Barrage - deals 30 damage", 20, 20, 30, 0, 20, 30, 100);
                        showStoryWithSkip("\t\t\t\tThe Falcon", new String[]{
                            "\n\t\t\t\tThe Falcon: Sam Wilson uses advanced wing technology to soar the skies.",
                            "\t\t\t\tA loyal soldier and hero, he fights with unmatched speed.",
                            "\t\t\t\tHis bravery makes him one of the Avengers' most trusted allies.\n"
                        }, 40, player);
                        break;
                    case 9:
                        return null;

                    case 69:
                        player = new Character("Jan Clark", 150, 150, 20, "Lisora aning OOP uy! - deals 20 damage", "Eternal Drip! - deals 30 damage", "Lisora aning DSA uy! - deals 40 damage", 20, 30, 40, 20, 30, 40, 100);
                        typeWriter("\n\t\t\t\tJan Clark: Known for his unstoppable drip and endless energy in class.", 40);
                        typeWriter("\t\t\t\tHe turns even the toughest coding battles into a stage for style.", 40);
                        typeWriter("\t\t\t\tWith wit and humor, he inspires allies to keep fighting strong.\n", 40);
                        break;
                    case 70:
                        player = new Character("\t\t\t\tJohn Micoh", 150, 150, 20, "CIT lang ya! - deals 20 damage", "Lahus ni ug Cambuntan ya? -  deals 40 damage", "Kapoyag tuon oy! - deals 50 damage", 20, 30, 35, 20, 40, 50, 100);
                        typeWriter("\n\t\t\t\tJohn Micoh: A laid-back warrior who balances jokes with determination.", 40);
                        typeWriter("\t\t\t\tHe may complain about studying, but when the battle starts, he gives his all.", 40);
                        typeWriter("\t\t\t\tWith raw persistence and sharp comebacks, he pushes through any challenge.\n", 40);
                        break;
                    case 71:

                        player = new Character("\t\t\t\tEthan Manto", 150, 150, 20, "Hollaback Girl!", "Soulja Boy Superman!", "Bye Bye Bye!", 20, 30, 35, 20, 30, 35, 100);
                        typeWriter("\n\t\t\t\tEthan Manto: A warrior fueled by rhythm and style.", 40);
                        typeWriter("\t\t\t\tHe turns every battle into a stage with iconic moves.", 40);
                        typeWriter("\t\t\t\tBehind the flair, he fights with loyalty and heart.\n", 40);
                        break;
                    case 72:
                        player = new Character("\t\t\t\tReuben Navarrete", 150, 150, 20, "HE Grenade!", "Binary Tree Confusion!", "Tik-Tok of Doom(scroll)", 20, 30, 35, 20, 30, 35, 100);
                        typeWriter("\n\t\t\t\tReuben Navarrete: A modern warrior who codes by day and scrolls by night.", 40);
                        typeWriter("\t\t\t\tHe analyzes enemies like binary trees - searching for their weakest nodes with precision.", 40);
                        typeWriter("\t\t\t\tHis TikTok-scrolling fingers move at lightning speed, predicting patterns before they form.", 40);
                        typeWriter("\t\t\t\tWhen he's not optimizing algorithms, he's optimizing combat strategies with viral efficiency.\n", 40);
                        break;
                    case 7355608:
                        player = new Character("\t\t\t\tThanos", 500, 500, 200, "Power Stone Blast - deals 200 damage ", "Snap - Eliminates enemy in an instant", "Power Stone Punch! - deals 100 damage", 30, 50, 30, 200, 999, 100, 100);
                        typeWriter("\n\t\t\t\tThanos: The Mad Titan who believes balance is the key to the universe.", 40);
                        typeWriter("\t\t\t\tArmed with the Infinity Stones, he bends reality with a flick of his hand.", 40);
                        typeWriter("\t\t\t\tThough feared by many, deep down he just wants some peace and maybe a farm life.\n", 40);
                        break;
                    default:

                        System.out.println("\t\t\t\tInvalid choice! Please select a valid hero number.\n");
                        break;
                }
            } else {
                System.out.println("\t\t\t\tInvalid input! Please enter a number.\n");
                sc.next();

            }
        }
        return player;
    }

    public static void typeWriter(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void showStoryWithSkip(String heroName, String[] storyLines, int delay, Character hero) {
        try {
            sc.nextLine();

            String input;
            boolean validInput = false;

            while (!validInput) {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.print("\t\t\t\t\tPress ENTER to view " + heroName + "'s story, or type '0' to skip: ");
                System.out.print("> ");
                input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    for (String line : storyLines) {
                        typeWriter(line, delay);
                    }
                    validInput = true;
                } else if (input.equalsIgnoreCase("0")) {

                    System.out.println("\n\t\t\t\t\tYou chose to skip " + heroName + "'s story.\n");
                    validInput = true;
                } else {
                    System.out.println("\t\t\t\tInvalid input. Please try again.");
                }
            }
            System.out.println("\t\t\t\t\t\t--- " + heroName + "s Stats ---");

        } catch (Exception e) {
            System.out.println("\t\t\t\tUnexpected exception caught: " + e);
            sc.next();
        }

    }
}
