
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
        clearScreen();
        while (player == null) {
            try {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("\t\t\t\t\t=========================================");
                System.out.println("\t\t\t\t\t====    Player 1 Choose Hero         ====");
                System.out.println("\t\t\t\t\t=========================================");
                System.out.println("\t\t\t\t\t|   Choose your hero:                   |");
                System.out.println("\t\t\t\t\t|     1. Iron Man                       |");
                System.out.println("\t\t\t\t\t|     2. Captain America                |");
                System.out.println("\t\t\t\t\t|     3. Thor                           |");
                System.out.println("\t\t\t\t\t|     4. Spider-Man                     |");
                System.out.println("\t\t\t\t\t|     5. Hulk                           |");
                System.out.println("\t\t\t\t\t|     6. Black Widow                    |");
                System.out.println("\t\t\t\t\t|     7. Ant-Man                        |");
                System.out.println("\t\t\t\t\t|     8. The Falcon                     |");
                System.out.println("\t\t\t\t\t|     9. Back                           |");
                System.out.print("\t\t\t\t\t > ");

                choice = sc.nextInt();
                clearScreen();

                switch (choice) {
                    case 1:
                        player = new Character("Iron Man", 110, 110, 20,
                                "Repulsor Blast - Deals 30 damage",
                                "Unibeam - Deals 20 damage",
                                "Rocket Barrage - Deals 50 damage",
                                30, 20, 50, 30, 20, 50, 100);
                        showStoryWithSkip("Iron Man", new String[]{
                            "\n\t\t\tIron Man: Genius billionaire Tony Stark built his armored suit after a near-death experience.",
                            "\t\t\t\tHe uses advanced technology to protect the world as Iron Man.",
                            "\t\t\t\tDespite his arrogance, his heart pushes him to fight for others.\n"
                        }, 40, player);
                        break;
                    case 2:
                        player = new Character("Captain America", 120, 120, 12,
                                "Shield Throw! - Deals 25 damage",
                                "Shield Bash! - Deals 12 damage",
                                "Inspire - Deals 20 damage",
                                25, 12, 20, 25, 12, 20, 100);
                        showStoryWithSkip("Captain America", new String[]{
                            "\n\t\t\tSteve Rogers - the super soldier from WWII.",
                            "\t\t\tArmed with his vibranium shield, he defends freedom and justice.\n"
                        }, 40, player);
                        break;
                    case 3:
                        player = new Character("Thor", 130, 130, 18,
                                "Lightning Blast! - Deals 30 damage",
                                "Mjolnir throw! - Deals 20 damage",
                                "God of Thunder - Deals 20 damage",
                                30, 20, 20, 30, 20, 20, 100);
                        showStoryWithSkip("Thor", new String[]{
                            "\n\t\t\tThor: The God of Thunder wields Mjolnir to protect the Nine Realms.",
                            "\t\t\ttHe commands storms and possesses incredible strength.\n"
                        }, 40, player);
                        break;
                    case 4:
                        player = new Character("Spider-Man", 90, 90, 14,
                                "Spidey Swing! - Avoids damage",
                                "Web Shot! - Deals 15 damage",
                                "Spidey-sense - Doubles attack damage",
                                20, 15, 20, 0, 15, 0, 100);
                        showStoryWithSkip("Spider-Man", new String[]{
                            "\n\t\t\tPeter Parker: Bitten by a radioactive spider, he gained amazing powers.",
                            "\t\t\tLives by 'with great power comes great responsibility.'\n"
                        }, 40, player);
                        break;
                    case 5:
                        player = new Character("Hulk", 150, 150, 20,
                                "Hulk Smash! - Deals 30 damage",
                                "Thunderclap - Deals 25 damage",
                                "Hulk Rage - Deals 25 damage",
                                30, 25, 20, 30, 25, 0, 100);
                        showStoryWithSkip("Hulk", new String[]{
                            "\n\t\t\tDr. Bruce Banner transforms into the Hulk when angered.",
                            "\t\t\tHis unstoppable strength makes him both feared and admired.\n"
                        }, 40, player);
                        break;
                    case 6:
                        player = new Character("Black Widow", 100, 100, 10,
                                "Stealth - Invisible for 1 turn",
                                "Widow's Kick! - Deals 20 damage",
                                "Espionage - Deals 50 damage",
                                30, 20, 50, 0, 20, 50, 100);
                        showStoryWithSkip("Black Widow", new String[]{
                            "\n\t\tNatasha Romanoff: Trained as a deadly assassin.",
                            "\t\t\tNow an Avenger, agile and cunning.\n"
                        }, 40, player);
                        break;
                    case 7:
                        player = new Character("Ant-Man", 100, 100, 20,
                                "Pym Particle Punch! - Deals 20 damage",
                                "Shrink - Dodges next attack",
                                "Giant-Man - Deals 30 damage",
                                20, 30, 25, 20, 0, 20, 100);
                        showStoryWithSkip("Ant-Man", new String[]{
                            "\n\t\t\tScott Lang uses Hank Pyms shrinking technology.",
                            "\t\t\tA thief turned hero protecting those in need.\n"
                        }, 40, player);
                        break;
                    case 8:
                        player = new Character("The Falcon", 150, 150, 10,
                                "Flight - avoids damage",
                                "Redwing Strike! - deals 20 damage",
                                "Tactical Barrage - deals 30 damage",
                                20, 20, 30, 0, 20, 30, 100);
                        showStoryWithSkip("The Falcon", new String[]{
                            "\n\t\t\tSam Wilson uses advanced wing technology to soar the skies.",
                            "\t\t\tA loyal soldier and hero with unmatched speed.\n"
                        }, 40, player);
                        break;
                    case 9:
                        MainMenu.start(sc);
                        return null;
                    case 69:
                        player = new Character("Jan Clark", 150, 150, 20,
                                "Lisora aning OOP uy! - deals 20 damage",
                                "Eternal Drip! - deals 30 damage",
                                "Lisora aning DSA uy! - deals 40 damage",
                                20, 30, 40, 20, 30, 40, 100);
                        showStoryWithSkip("Jan Clark", new String[]{
                            "\n\t\t\tJan Clark: Known for unstoppable drip and endless energy in class.",
                            "\t\t\tTurns tough coding battles into a stage for style.\n"
                        }, 40, player);
                        break;
                    case 70:
                        player = new Character("John Micoh", 150, 150, 20,
                                "CIT lang ya! - deals 20 damage",
                                "Lahus ni ug Cambuntan ya? - deals 40 damage",
                                "Kapoyag tuon oy! - deals 50 damage",
                                20, 30, 35, 20, 40, 50, 100);
                        showStoryWithSkip("John Micoh", new String[]{
                            "\n\t\t\tJohn Micoh: Laid-back warrior balancing jokes and determination.\n"
                        }, 40, player);
                        break;
                    case 8700:
                        player = new Character("Ethan Manto", 150, 150, 20,
                                "Hollaback Girl!",
                                "Soulja Boy Superman!",
                                "Bye Bye Bye!",
                                20, 30, 35, 20, 30, 35, 100);
                        showStoryWithSkip("Ethan Manto", new String[]{
                            "\n\t\t\tEthan Manto: A warrior fueled by rhythm and style.",
                            "\t\t\t\tTurns every battle into a stage with iconic moves.\n"
                        }, 40, player);
                        break;
                    case 7355608:
                        player = new Character("Thanos", 500, 500, 200,
                                "Power Stone Blast - Deals 200 damage",
                                "Snap - Eliminates enemy in an instant",
                                "Power Stone Punch! - Deals 100 damage",
                                30, 50, 30, 200, 999, 100, 100);
                        showStoryWithSkip("Thanos", new String[]{
                            "\n\t\t\tThanos: The Mad Titan seeking universal balance.",
                            "\t\t\tArmed with Infinity Stones, bends reality with a flick of his hand.\n"
                        }, 40, player);
                        break;
                    default:
                        System.out.println("\t\t\tInvalid choice! Please select a valid hero number.\n");
                        break;

                }
            } catch (Exception e) {
                clearScreen();
                System.out.println("\t\t\tUnexpected exception caught: " + e);
                sc.next();
            }
        }
        return player;
    }

    public static void showStoryWithSkip(String heroName, String[] storyLines, int delay, Character hero) {
        try {
            sc.nextLine();

            String input = "";
            boolean validInput = false;

            while (!validInput) {
                System.out.print("\t\t\tPress ENTER to view " + heroName + "'s story, or type '0' to skip: ");
                input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    for (String line : storyLines) {
                        MarvelGame.typeWriter(line, delay);
                    }
                    validInput = true;
                } else if (input.equalsIgnoreCase("0")) {
                    System.out.println("\n\t\t\tYou chose to skip " + heroName + "'s story.\n");
                    validInput = true;
                } else {
                    System.out.println("\t\t\tInvalid input. Please try again.");
                }
            }
            System.out.println("\t\t\t--- " + heroName + "s Stats ---");
            hero.displayIntro();

        } catch (Exception e) {
            System.out.println("\t\t\tUnexpected exception caught: " + e);
            sc.next();

            System.out.println("\t\t\t--- " + heroName + "s Stats ---");
            hero.displayIntro();
        }

    }
}
