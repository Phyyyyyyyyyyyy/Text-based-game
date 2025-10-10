import java.util.Scanner;

public class Player1 {
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
            System.out.println("\t====       Player 1 Choose Hero     ====");
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
            System.out.print("\t > ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                clearScreen();
                switch (choice) {
                    case 1:
                        player = new Character("Iron Man", 110, 110, 20,
                                "Repulsor Blast - deals 30 damage",
                                "Unibeam - deals 20 damage",
                                "Rocket Barrage - deals 50 damage",
                                30, 20, 50, 30, 20, 50, 100);
                        showStoryWithSkip("Iron Man", new String[]{
                                 "\nIron Man: Genius billionaire Tony Stark built his armored suit after a near-death experience.",
                            "He uses advanced technology to protect the world as Iron Man.",
                            "Despite his arrogance, his heart pushes him to fight for others.\n"
                        }, 40, player);
                        break;
                    case 2:
                        player = new Character("Captain America", 120, 120, 12,
                                "Shield Throw! - Deals 25 damage",
                                "Shield Bash! - deals 12 damage",
                                "Inspire - Heals 20 HP",
                                25, 12, 15, 25, 12, 15, 100);
                        showStoryWithSkip("Captain America", new String[]{
                                "\nSteve Rogers — the super soldier from WWII.",
                                "Armed with his vibranium shield, he defends freedom and justice.\n"
                        }, 40, player);
                        break;
                    case 3:
                        player = new Character("Thor", 130, 130, 18,
                                "Lightning Blast! - deals 30 damage",
                                "Mjolnir throw! - deals 20 damage",
                                "God of Thunder - doubles attack for 3 turns",
                                30, 20, 20, 30, 20, 20, 100);
                        showStoryWithSkip("Thor", new String[]{
                                "\nThor: The God of Thunder wields Mjolnir to protect the Nine Realms.",
                                "He commands storms and possesses incredible strength.\n"
                        }, 40, player);
                        break;
                    case 4:
                        player = new Character("Spider-Man", 90, 90, 14,
                                "Spidey Swing! - avoids damage",
                                "Web Shot! - deals 15 damage",
                                "Spidey-sense - doubles attack damage",
                                20, 15, 20, 0, 15, 0, 100);
                        showStoryWithSkip("Spider-Man", new String[]{
                                "\nPeter Parker: Bitten by a radioactive spider, he gained amazing powers.",
                                "Lives by 'with great power comes great responsibility.'\n"
                        }, 40, player);
                        break;
                    case 5:
                        player = new Character("Hulk", 150, 150, 20,
                                "Hulk Smash! - Deals 30 damage",
                                "Thunderclap - Deals 25 damage",
                                "Hulk Rage - Doubles attack damage",
                                30, 25, 20, 30, 25, 0, 100);
                        showStoryWithSkip("Hulk", new String[]{
                                "\nDr. Bruce Banner transforms into the Hulk when angered.",
                                "His unstoppable strength makes him both feared and admired.\n"
                        }, 40, player);
                        break;
                    case 6:
                        player = new Character("Black Widow", 100, 100, 10,
                                "Stealth - invisible for 1 turn",
                                "Widow's Kick! - 20 damage",
                                "Espionage - 50 damage",
                                30, 20, 50, 0, 20, 50, 100);
                        showStoryWithSkip("Black Widow", new String[]{
                                "\nNatasha Romanoff: Trained as a deadly assassin.",
                                "Now an Avenger, agile and cunning.\n"
                        }, 40, player);
                        break;
                    case 7:
                        player = new Character("Ant-Man", 100, 100, 20,
                                "Pym Particle Punch! - deals 20 damage",
                                "Shrink - dodges next attack",
                                "Giant-Man - deals double damage next 2 turns",
                                20, 30, 25, 20, 0, 20, 100);
                        showStoryWithSkip("Ant-Man", new String[]{
                                "\nScott Lang uses Hank Pym’s shrinking technology.",
                                "A thief turned hero protecting those in need.\n"
                        }, 40, player);
                        break;
                    case 8:
                        player = new Character("The Falcon", 150, 150, 10,
                                "Flight - avoids damage",
                                "Redwing Strike! - deals 20 damage",
                                "Tactical Barrage - deals 30 damage",
                                20, 20, 30, 0, 20, 30, 100);
                        showStoryWithSkip("The Falcon", new String[]{
                                "\nSam Wilson uses advanced wing technology to soar the skies.",
                                "A loyal soldier and hero with unmatched speed.\n"
                        }, 40, player);
                        break;
                    case 9:
                        MainMenu.start(sc);
                        break;
                    case 69:
                        player = new Character("Jan Clark", 150, 150, 20,
                                "Lisora aning OOP uy! - deals 20 damage",
                                "Eternal Drip! - deals 30 damage",
                                "Lisora aning DSA uy! - deals 40 damage",
                                20, 30, 40, 20, 30, 40, 100);
                        showStoryWithSkip("Jan Clark", new String[]{
                                "\nJan Clark: Known for unstoppable drip and endless energy in class.",
                                "Turns tough coding battles into a stage for style.\n"
                        }, 40, player);
                        break;
                    case 70:
                        player = new Character("John Micoh", 150, 150, 20,
                                "CIT lang ya! - deals 20 damage",
                                "Lahus ni ug Cambuntan ya? - deals 40 damage",
                                "Kapoyag tuon oy! - deals 50 damage",
                                20, 30, 35, 20, 40, 50, 100);
                        showStoryWithSkip("John Micoh", new String[]{
                                "\nJohn Micoh: Laid-back warrior balancing jokes and determination.\n"
                        }, 40, player);
                        break;
                    case 8700:
                        player = new Character("Ethan Manto", 150, 150, 20,
                                "Hollaback Girl!",
                                "Soulja Boy Superman!",
                                "Bye Bye Bye!",
                                20, 30, 35, 20, 30, 35, 100);
                        showStoryWithSkip("Ethan Manto", new String[]{
                                "\nEthan Manto: A warrior fueled by rhythm and style.",
                                "Turns every battle into a stage with iconic moves.\n"
                        }, 40, player);
                        break;
                    case 7355608:
                        player = new Character("Thanos", 500, 500, 200,
                                "Power Stone Blast - deals 200 damage",
                                "Snap - Eliminates enemy in an instant",
                                "Power Stone Punch! - deals 100 damage",
                                30, 50, 30, 200, 999, 100, 100);
                        showStoryWithSkip("Thanos", new String[]{
                                "\nThanos: The Mad Titan seeking universal balance.",
                                "Armed with Infinity Stones, bends reality with a flick of his hand.\n"
                        }, 40, player);
                        break;
                    default:
                        System.out.println("Invalid choice! Please select a valid hero number.\n");
                        break;
                }

            } else {
                System.out.println("Invalid input! Please enter a number.\n");
                sc.next();
            }
        }

        return player;
    }

    public static void showStoryWithSkip(String heroName, String[] storyLines, int delay, Character hero) {
        sc.nextLine(); // consume leftover newline
        System.out.print("Press ENTER to view " + heroName + "'s story, or type 'skip' to skip: ");
        String input = sc.nextLine();

        if (!input.trim().equalsIgnoreCase("skip")) {
            for (String line : storyLines) {
                MarvelGame.typeWriter(line, delay);
            }
        } else {
            System.out.println("\nYou skipped the story.\n");
        }

        // ✅ Show stats no matter what
        System.out.println("--- " + heroName + "’s Stats ---");
        hero.displayIntro();
    }
}
