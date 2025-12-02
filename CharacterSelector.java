// CharacterSelector.java

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CharacterSelector {

    private static final Map<Integer, CharacterData> HEROES = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    static {
        initializeHeroes();
    }

    private static class CharacterData {

        String name;
        int hp;
        int maxHp;
        int attack;
        String skill1;
        String skill2;
        String skill3;
        int sk1Cost;
        int sk2Cost;
        int sk3Cost;
        int sk1Damage;
        int sk2Damage;
        int sk3Damage;
        int mana;
        String[] storyLines;

        CharacterData(String name, int hp, int maxHp, int attack,
                String skill1, String skill2, String skill3,
                int sk1Cost, int sk2Cost, int sk3Cost,
                int sk1Damage, int sk2Damage, int sk3Damage,
                int mana, String[] storyLines) {
            this.name = name;
            this.hp = hp;
            this.maxHp = maxHp;
            this.attack = attack;
            this.skill1 = skill1;
            this.skill2 = skill2;
            this.skill3 = skill3;
            this.sk1Cost = sk1Cost;
            this.sk2Cost = sk2Cost;
            this.sk3Cost = sk3Cost;
            this.sk1Damage = sk1Damage;
            this.sk2Damage = sk2Damage;
            this.sk3Damage = sk3Damage;
            this.mana = mana;
            this.storyLines = storyLines;
        }
    }

private static void initializeHeroes() {
    // Main Marvel Heroes
    HEROES.put(1, new CharacterData("Iron Man", 110, 110, 15,
        "Repulsor Blast - Deals 13 damage",
        "Unibeam Strike - Deals 15 damage",
        "Rocket Barrage - Deals 22 damage",
        13, 15, 22, 13, 15, 22, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tIron Man: Genius billionaire Tony Stark built his armored suit after a near-death experience.",
            "\t\t\t\t\t\t\tHe uses advanced technology to protect the world as Iron Man.",
            "\t\t\t\t\t\t\tDespite his arrogance, his heart pushes him to fight for others.\n"
        }));

    HEROES.put(2, new CharacterData("Captain America", 110, 110, 15,
        "Shield Throw - Deals 12 damage",
        "Shield Bash - Deals 14 damage",
        "Shield Combo - Deals 24 damage",
        12, 14, 24, 12, 14, 24, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tSteve Rogers - the super soldier from WWII.",
            "\t\t\t\t\t\t\tArmed with his vibranium shield, he defends freedom and justice.\n"
        }));

    HEROES.put(3, new CharacterData("Thor", 110, 110, 15,
        "Lightning Strike - Deals 11 damage",
        "Mjolnir Impact - Deals 13 damage",
        "Thunder Crash - Deals 26 damage",
        11, 13, 26, 11, 13, 26, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tThor: The God of Thunder wields Mjolnir to protect the Nine Realms.",
            "\t\t\t\t\t\t\tHe commands storms and possesses incredible strength.\n"
        }));

    HEROES.put(4, new CharacterData("Spider-Man", 110, 110, 15,
        "Web Strike - Deals 12 damage",
        "Venom Blast - Deals 18 damage",
        "Spider Fury - Deals 20 damage",
        12, 18, 20, 12, 18, 20, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tPeter Parker: Bitten by a radioactive spider, he gained amazing powers.",
            "\t\t\t\t\t\t\tLives by 'with great power comes great responsibility.'\n"
        }));

    HEROES.put(5, new CharacterData("Hulk", 110, 110, 15,
        "Hulk Smash - Deals 14 damage",
        "Thunder Clap - Deals 15 damage",
        "Gamma Crush - Deals 21 damage",
        14, 15, 21, 14, 15, 21, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tDr. Bruce Banner transforms into the Hulk when angered.",
            "\t\t\t\t\t\t\tHis unstoppable strength makes him both feared and admired.\n"
        }));

    HEROES.put(6, new CharacterData("Black Widow", 100, 100, 15,
        "Widow's Bite - Deals 16 damage",
        "Combat Kick - Deals 18 damage",
        "Assassin Strike - Deals 26 damage",
        16, 18, 26, 16, 18, 26, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tNatasha Romanoff: Trained as a deadly assassin.",
            "\t\t\t\t\t\t\tNow an Avenger, agile and cunning.\n"
        }));

    HEROES.put(7, new CharacterData("Ant-Man", 100, 100, 15,
        "Pym Particle Blast - Deals 18 damage",
        "Quantum Strike - Deals 20 damage",
        "Giant Slam - Deals 22 damage",
        18, 20, 22, 18, 20, 22, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tScott Lang uses Hank Pym's shrinking technology.",
            "\t\t\t\t\t\t\tA thief turned hero protecting those in need.\n"
        }));

    HEROES.put(8, new CharacterData("The Falcon", 100, 100, 15,
        "Aerial Strike - Deals 14 damage",
        "Redwing Assault - Deals 22 damage",
        "Tactical Barrage - Deals 24 damage",
        14, 22, 24, 14, 22, 24, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tSam Wilson uses advanced wing technology to soar the skies.",
            "\t\t\t\t\t\t\tA loyal soldier and hero with unmatched speed.\n"
        }));

    // Special / Secret Characters
    HEROES.put(69, new CharacterData("Jan Clark", 120, 120, 15,
        "Code Crash - Deals 10 damage",
        "Debug Strike - Deals 12 damage",
        "System Overload - Deals 18 damage",
        10, 12, 18, 10, 12, 18, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tJan Clark: Known for unstoppable drip and endless energy in class.",
            "\t\t\t\t\t\t\tTurns tough coding battles into a stage for style.\n"
        }));

    HEROES.put(70, new CharacterData("John Micoh", 120, 120, 15,
        "CIT Crash - Deals 8 damage",
        "Bug Blast - Deals 12 damage",
        "Code Fury - Deals 20 damage",
        8, 12, 20, 8, 12, 20, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tJohn Micoh: Laid-back warrior balancing jokes and determination.\n"
        }));

    HEROES.put(71, new CharacterData("Ethan Manto", 120, 120, 15,
        "Rhythm Strike - Deals 12 damage",
        "Dance Blast - Deals 13 damage",
        "Beat Drop - Deals 15 damage",
        12, 13, 15, 12, 13, 15, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tEthan Manto: A warrior fueled by rhythm and style.",
            "\t\t\t\t\t\t\tTurns every battle into a stage with iconic moves.\n"
        }));

    HEROES.put(72, new CharacterData("Reuben Navarrete", 120, 120, 15,
        "HE Grenade! - Deals 10 damage",
        "Binary Tree Confusion! - Deals 10 damage",
        "Tik-Tok of Doom(scroll) - Deals 20 damage",
        10, 10, 20, 10, 10, 20, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tReuben Navarrete: A modern warrior who codes by day and scrolls by night.",
            "\t\t\t\t\t\t\tHe analyzes enemies like binary trees - searching for their weakest nodes with precision.",
            "\t\t\t\t\t\t\tHis TikTok-scrolling fingers move at lightning speed, predicting patterns before they form.",
            "\t\t\t\t\t\t\tWhen he's not optimizing algorithms, he's optimizing combat strategies with viral efficiency.\n"
        }));

    // Ultimate Boss
    HEROES.put(7355608, new CharacterData("Thanos", 500, 500, 200,
        "Power Stone Blast - Deals 200 damage",
        "Infinity Snap - Deals 999 damage",
        "Titan Punch - Deals 100 damage",
        30, 50, 30, 200, 999, 100, 100,
        new String[]{
            "\n\t\t\t\t\t\t\tThanos: The Mad Titan seeking universal balance.",
            "\t\t\t\t\t\t\tArmed with Infinity Stones, bends reality with a flick of his hand.\n"
        }));
}

    public static Character selectHero(String title, boolean showBackOption) {
        Character player = null;

        while (player == null) {
            clearScreen();
            displaySelectionMenu(title, showBackOption);

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (showBackOption && choice == 9) {
                    return null; // Back to main menu
                }

                if (HEROES.containsKey(choice)) {
                    CharacterData data = HEROES.get(choice);
                    player = createHero(data);
                    showStoryWithSkip(data.name, data.storyLines, 40, player);
                } else {
                    System.out.println("\t\t\t\t\t\t\tInvalid choice! Please select a valid hero number.\n");
                    System.out.println("\t\t\t\t\t\t\tPress ENTER to continue...");
                    sc.nextLine();
                }
            } catch (Exception e) {
                System.out.println("\t\t\t\t\t\t\tInvalid input! Please enter a number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        return player;
    }

    private static Character createHero(CharacterData data) {
        return new Hero(data.name, data.hp, data.maxHp, data.attack,
                data.skill1, data.skill2, data.skill3,
                data.sk1Cost, data.sk2Cost, data.sk3Cost,
                data.sk1Damage, data.sk2Damage, data.sk3Damage,
                data.mana);
    }

    private static void displaySelectionMenu(String title, boolean showBackOption) {
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("\t\t\t\t\t\t\t=========================================");
        System.out.println("\t\t\t\t\t\t\t====    " + title + "         ====");
        System.out.println("\t\t\t\t\t\t\t=========================================");
        System.out.println("\t\t\t\t\t\t\t|   Choose your hero:                   |");
        System.out.println("\t\t\t\t\t\t\t|     1. Iron Man                       |");
        System.out.println("\t\t\t\t\t\t\t|     2. Captain America                |");
        System.out.println("\t\t\t\t\t\t\t|     3. Thor                           |");
        System.out.println("\t\t\t\t\t\t\t|     4. Spider-Man                     |");
        System.out.println("\t\t\t\t\t\t\t|     5. Hulk                           |");
        System.out.println("\t\t\t\t\t\t\t|     6. Black Widow                    |");
        System.out.println("\t\t\t\t\t\t\t|     7. Ant-Man                        |");
        System.out.println("\t\t\t\t\t\t\t|     8. The Falcon                     |");

        if (showBackOption) {
            System.out.println("\t\t\t\t\t\t\t|     9. Back                           |");
        }

        System.out.print("\t\t\t\t\t\t\t > ");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void typeWriter(String text, int delay) {
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

    private static void showStoryWithSkip(String heroName, String[] storyLines, int delay, Character hero) {
        try {
            System.out.println("\n\n\n\n\n\n\n");
            System.out.print("\t\t\t\t\t\t\tPress ENTER to view " + heroName + "'s story, or type '0' to skip: ");
            System.out.print("> ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                for (String line : storyLines) {
                    typeWriter(line, delay);
                }
            } else if (input.equalsIgnoreCase("0")) {
                System.out.println("\n\t\t\t\t\t\t\tYou chose to skip " + heroName + "'s story.\n");
            }

            System.out.println("\t\t\t\t\t\t\t--- " + heroName + "'s Stats ---");
            hero.displayIntro();

            System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
            sc.nextLine();

        } catch (Exception e) {
            System.out.println("\t\t\t\t\t\t\tError showing story: " + e.getMessage());
            sc.nextLine();
        }
    }
}
