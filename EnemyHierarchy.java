
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyHierarchy {

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private static final List<Enemy> easyEnemies = new ArrayList<>();
    private static final List<Enemy> mediumEnemies = new ArrayList<>();
    private static final List<Enemy> hardEnemies = new ArrayList<>();
    private static final Random rand = new Random();

    static {
        initializeEnemies();
    }

    private static void initializeEnemies() {
        // EASY ENEMIES (5) - Low HP and damage
        easyEnemies.add(new Enemy("Loki", 80, 80, 12,
                "Illusion Sneak Attack - 10 damage", "Scepter Strike - 15 damage", "Mind Control - 18 damage",
                10, 15, 18, 10, 15, 18, 80));

        easyEnemies.add(new Enemy("Mystique", 80, 80, 13,
                "Shape Shift Strike - 10 damage", "Mimic Attack - 15 damage", "Invisible Hit - 18 damage",
                10, 15, 18, 10, 15, 18, 80));

        easyEnemies.add(new Enemy("Green Goblin", 85, 85, 12,
                "Pumpkin Bomb - 10 damage", "Glider Attack - 15 damage", "Goblin's Rage - 18 damage",
                10, 15, 18, 10, 15, 18, 85));

        easyEnemies.add(new Enemy("Electro", 75, 75, 11,
                "Electric Shock - 10 damage", "Thunderbolt - 12 damage", "Overcharge - 15 damage",
                10, 12, 15, 10, 12, 15, 75));

        easyEnemies.add(new Enemy("Sandman", 90, 90, 11,
                "Sand Blast - 10 damage", "Morphing Strike - 12 damage", "Sand Smash - 15 damage",
                10, 12, 15, 10, 12, 15, 90));

        // MEDIUM ENEMIES (5) - Medium HP and damage
        mediumEnemies.add(new Enemy("Ultron", 110, 110, 16,
                "Laser Blast - 13 damage", "Metal Punch - 15 damage", "Flight Thrust Attack - 18 damage",
                13, 15, 18, 13, 15, 18, 100));

        mediumEnemies.add(new Enemy("Red Skull", 105, 105, 15,
                "Cosmic Blast - 13 damage", "Tactical Strike - 15 damage", "Rally Troops - 18 damage",
                13, 15, 18, 13, 15, 18, 100));

        mediumEnemies.add(new Enemy("Venom", 115, 115, 16,
                "Symbiote Strike - 13 damage", "Web Trap - 15 damage", "Rage - 18 damage",
                13, 15, 18, 13, 15, 18, 100));

        mediumEnemies.add(new Enemy("Doctor Octopus", 110, 110, 16,
                "Tentacle Slam - 13 damage", "Mechanical Grab - 15 damage", "Overload - 18 damage",
                13, 15, 18, 13, 15, 18, 100));

        mediumEnemies.add(new Enemy("Sabretooth", 108, 108, 17,
                "Ferocious Claw - 13 damage", "Savage Bite - 15 damage", "Howling Vengeance - 18 damage",
                13, 15, 18, 13, 15, 18, 100));

        // HARD ENEMIES (5) - High HP and damage
        hardEnemies.add(new Enemy("Magneto", 130, 130, 19,
                "Magnetic Pulse - 16 damage", "Metal Manipulation - 18 damage", "Force Field Attack - 20 damage",
                16, 18, 20, 16, 18, 20, 120));

        hardEnemies.add(new Enemy("Hela", 135, 135, 20,
                "Necrosword Slash - 16 damage", "Minions Attack - 18 damage", "Asgardian Fury - 20 damage",
                16, 18, 20, 16, 18, 20, 120));

        hardEnemies.add(new Enemy("Kingpin", 125, 125, 18,
                "Heavy Punch - 16 damage", "Ground Slam - 18 damage", "Intimidate - 20 damage",
                16, 18, 20, 16, 18, 20, 120));

        hardEnemies.add(new Enemy("Juggernaut", 140, 140, 21,
                "Unstoppable Charge - 16 damage", "Ground Pound - 18 damage", "Rage Mode - 20 damage",
                16, 18, 20, 16, 18, 20, 120));

        hardEnemies.add(new Enemy("Thanos", 150, 150, 22,
                "Power Stone Punch - 18 damage", "Space Stone Snap - 20 damage", "Reality Warp - 22 damage",
                18, 20, 22, 18, 20, 22, 130));
    }

    public static Enemy getEnemyByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return easyEnemies.get(rand.nextInt(easyEnemies.size()));
            case MEDIUM:
                return mediumEnemies.get(rand.nextInt(mediumEnemies.size()));
            case HARD:
                return hardEnemies.get(rand.nextInt(hardEnemies.size()));
            default:
                return easyEnemies.get(rand.nextInt(easyEnemies.size()));
        }
    }

    public static List<Enemy> getEnemiesByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return new ArrayList<>(easyEnemies);
            case MEDIUM:
                return new ArrayList<>(mediumEnemies);
            case HARD:
                return new ArrayList<>(hardEnemies);
            default:
                return new ArrayList<>(easyEnemies);
        }
    }

    public static Enemy getRandomEnemy() {
        int randomDifficulty = rand.nextInt(3);
        if (randomDifficulty == 0) {
            return getEnemyByDifficulty(Difficulty.EASY);
        }
        if (randomDifficulty == 1) {
            return getEnemyByDifficulty(Difficulty.MEDIUM);
        }
        return getEnemyByDifficulty(Difficulty.HARD);
    }

    public static void displayAllEnemies() {

        System.out.println();
    }
}
